import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EvaluatorTests {

	static Stream<Arguments> stringArrayProvider() {
		return Stream.of(
				Arguments.of("1+2", (Object) new ArrayList<String>(Arrays.asList("1","+","2"))),
				Arguments.of("11-5", (Object) new ArrayList<String>(Arrays.asList("11","-","5"))),
				Arguments.of("100*47", (Object) new ArrayList<String>(Arrays.asList("100","*","47"))));
	}

	@ParameterizedTest
	@MethodSource("stringArrayProvider")
	void smallExpressionTest(String expression, ArrayList<String> expected) {
		Evaluator eval = new Evaluator();
		assertEquals(expected, eval.evaluateExpression(expression));
	}

	@Test
	void longExpressionTest() {
		fail("Not yet implemented");
	}

	@Test
	void multipleOpsTest() {
		fail("Not yet implemented");
	}

	@Test
	void multipleOpsInRowTest() {
		fail("Not yet implemented");
	}

	@Test
	void priorityTest() {
		fail("Not yet implemented");
	}

	@Test
	void parenthesesTest() {
		fail("Not yet implemented");
	}

	@Test
	void wrongNumberOfParenthesesTest() {
		fail("Not yet implemented");
	}
}