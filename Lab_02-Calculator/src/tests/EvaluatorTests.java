import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class EvaluatorTests {

	static Stream<Arguments> smallExpressionArgumentProvider() {
		return Stream.of(Arguments.of("1+2", (Object) new ArrayList<String>(Arrays.asList("1", "+", "2"))),
				Arguments.of("11-5", (Object) new ArrayList<String>(Arrays.asList("11", "-", "5"))),
				Arguments.of("100*47", (Object) new ArrayList<String>(Arrays.asList("100", "*", "47"))));
	}

	@ParameterizedTest
	@MethodSource("smallExpressionArgumentProvider")
	void smallExpressionTest(String expression, ArrayList<String> expected) throws ParseException {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	static Stream<Arguments> longExpressionArgumentProvider() {
		return Stream.of(
				Arguments.of("1+2-3/4*5^6%7",
						(Object) new ArrayList<String>(
								Arrays.asList("1", "+", "2", "-", "3", "/", "4", "*", "5", "^", "6", "%", "7"))),
				Arguments
						.of("123-456789/123456%789*1/23+456+7+8+90+1+2-1004",
								(Object) new ArrayList<String>(Arrays.asList("123", "-", "456789", "/", "123456", "%",
										"789", "*", "1", "/", "23", "+", "456", "+", "7", "+", "8", "+", "90", "+", "1",
										"+", "2", "-", "1004"))),
				Arguments.of("100*100000/10%10000",
						(Object) new ArrayList<String>(Arrays.asList("100", "*", "100000", "/", "10", "%", "10000"))));
	}

	@ParameterizedTest
	@MethodSource("longExpressionArgumentProvider")
	void longExpressionTest(String expression, ArrayList<String> expected) throws ParseException {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	static Stream<Arguments> negativeNumbersArgumentProvider() {
		return Stream.of(Arguments.of("-1+2", (Object) new ArrayList<String>(Arrays.asList("-1", "+", "2"))),
				Arguments.of("11--5", (Object) new ArrayList<String>(Arrays.asList("11", "-", "-5"))),
				Arguments.of("100*-47", (Object) new ArrayList<String>(Arrays.asList("100", "*", "-47"))),
				Arguments.of("-1--2--3", (Object) new ArrayList<String>(Arrays.asList("-1", "-", "-2", "-", "-3"))),
				Arguments.of("10+-5+-5", (Object) new ArrayList<String>(Arrays.asList("10", "+", "-5", "+", "-5"))));
	}

	@ParameterizedTest
	@MethodSource("negativeNumbersArgumentProvider")
	void negativeNumbersTest(String expression, ArrayList<String> expected) throws ParseException {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	@ParameterizedTest
	@ValueSource(strings = { "/1+2", "*3-4", "+5/6", "^7*8", "%9/1", "+(1+1)", "/(1+1)"  })
	void opAtStartExceptionTest(String expression) {
		Evaluator eval = new Evaluator();
		assertThrows(ParseException.class, () -> eval.evaluateExpression(expression));
	}

	static Stream<Arguments> negativeAtStartArgumentProvider() {
		return Stream.of(Arguments.of("-1+2", (Object) new ArrayList<String>(Arrays.asList("-1", "+", "2"))),
				Arguments.of("-3-4", (Object) new ArrayList<String>(Arrays.asList("-3", "-", "4"))),
				Arguments.of("-5/6", (Object) new ArrayList<String>(Arrays.asList("-5", "/", "6"))));
	}

	@ParameterizedTest
	@MethodSource("negativeAtStartArgumentProvider")
	void negativeAtStartTest(String expression, ArrayList<String> expected) throws ParseException {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	@ParameterizedTest
	@ValueSource(strings = { "--1+2", "3---4", "5//6", "7**8", "9+/10", "100%%5" })
	void multipleOpsInRowTest(String expression) {
		Evaluator eval = new Evaluator();
		assertThrows(ParseException.class, () -> eval.evaluateExpression(expression));
	}

	static Stream<Arguments> parenthesesArgumentProvider() {
		return Stream.of(Arguments.of("(1+2)", (Object) new ArrayList<String>(Arrays.asList("(", "1", "+", "2", ")"))),
				Arguments.of("(-1+2)", (Object) new ArrayList<String>(Arrays.asList("(", "-1", "+", "2", ")"))),
				Arguments.of("(-1+-2)--2",
						(Object) new ArrayList<String>(Arrays.asList("(", "-1", "+", "-2", ")", "-", "-2"))),
				Arguments.of("(100*2)+10",
						(Object) new ArrayList<String>(Arrays.asList("(", "100", "*", "2", ")", "+", "10"))),
				Arguments.of("(1+(25-5))",
						(Object) new ArrayList<String>(Arrays.asList("(", "1", "+", "(", "25", "-", "5", ")", ")"))),
				Arguments.of("((1+5)*2)^4+(2-1)",
						(Object) new ArrayList<String>(Arrays.asList("(", "(", "1", "+", "5", ")", "*", "2", ")", "^",
								"4", "+", "(", "2", "-", "1", ")"))),
				Arguments.of("-(5+5)", (Object) new ArrayList<String>(Arrays.asList("-","(", "5", "+", "5", ")"))));
	}

	@ParameterizedTest
	@MethodSource("parenthesesArgumentProvider")
	void parenthesesTest(String expression, ArrayList<String> expected) throws ParseException {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	static Stream<Arguments> wrongNumberOfParenthesesesArgumentProvider() {
		return Stream.of(Arguments.of("1+2)", (Object) new ArrayList<String>(Arrays.asList("1", "+", "2", ")"))),
				Arguments.of("1+2))", (Object) new ArrayList<String>(Arrays.asList("1", "+", "2", ")", ")"))),
				Arguments.of("(1-5", (Object) new ArrayList<String>(Arrays.asList("(", "1", "-", "5"))),
				Arguments.of("((1-5", (Object) new ArrayList<String>(Arrays.asList("(", "(", "1", "-", "5"))),
				Arguments.of("(1+(2-1)",
						(Object) new ArrayList<String>(Arrays.asList("(", "1", "+", "(", "2", "-", "1", ")"))),
				Arguments.of("(1+2+(3+1))*2)", (Object) new ArrayList<String>(
						Arrays.asList("(", "1", "+", "2", "+", "(", "3", "+", "1", ")", ")", "*", "2", ")"))));
	}

	@ParameterizedTest
	@MethodSource("wrongNumberOfParenthesesesArgumentProvider")
	void wrongNumberOfParenthesesTest(String expression, ArrayList<String> expected) throws ParseException {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	@ParameterizedTest
	@ValueSource(strings = { "1+2a", "!1+2", "1+\2", "1&1+2", "{1+2}", "\n1+2", " ", "^", "(?!)", "[^abc]ab",
			"System.out.println('test')" })
	void illegalCharacterTest(String expression) throws ParseException {
		Evaluator eval = new Evaluator();
		assertThrows(ParseException.class, () -> eval.evaluateExpression(expression));
	}

}