import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ShuntingYardTests {

	static Stream<Arguments> smallExpressionArgumentProvider() {
		return Stream.of(
				Arguments.of((Object) new ArrayList<String>(Arrays.asList("1", "+", "2")),
						(Object) new ArrayList<String>(Arrays.asList("1", "2", "+"))),
				Arguments.of((Object) new ArrayList<String>(Arrays.asList("2", "*", "3")),
						(Object) new ArrayList<String>(Arrays.asList("2", "3", "*"))),
				Arguments.of((Object) new ArrayList<String>(Arrays.asList("4", "/", "5", "^", "6")),
						(Object) new ArrayList<String>(Arrays.asList("4", "5", "6", "^", "/"))));
	}

	@ParameterizedTest
	@MethodSource("smallExpressionArgumentProvider")
	void smallExpressionTest(ArrayList<String> expression, ArrayList<String> expected) {
		ShuntingYard sy = new ShuntingYard();
		assertEquals(expected, sy.convert(expression));
	}

	static Stream<Arguments> longExpressionArgumentProvider() {
		return Stream.of(Arguments.of(
				(Object) new ArrayList<String>(
						Arrays.asList("1", "+", "2", "-", "3", "/", "4", "*", "5", "^", "6", "%", "7")),
				(Object) new ArrayList<String>(
						Arrays.asList("1", "2", "+", "3", "4", "/", "5", "6", "^", "*", "7", "%", "-"))),
				Arguments.of(
						(Object) new ArrayList<String>(
								Arrays.asList("123", "-", "456789", "/", "123456", "%", "789", "*", "1", "/", "23", "+",
										"456", "+", "7", "+", "8", "+", "90", "+", "1", "+", "2", "-", "1004")),
						(Object) new ArrayList<String>(
								Arrays.asList("123", "456789", "123456", "/", "789", "%", "1", "*", "23", "/", "-",
										"456", "+", "7", "+", "8", "+", "90", "+", "1", "+", "2", "+", "1004", "-"))),
				Arguments.of(
						(Object) new ArrayList<String>(Arrays.asList("100", "*", "100000", "/", "10", "%", "10000")),
						(Object) new ArrayList<String>(Arrays.asList("100", "100000", "*", "10", "/", "10000", "%"))));
	}

	@ParameterizedTest
	@MethodSource("longExpressionArgumentProvider")
	void longExpressionTest(ArrayList<String> expression, ArrayList<String> expected) {
		ShuntingYard sy = new ShuntingYard();
		assertEquals(expected, sy.convert(expression));
	}

	@Test
	void parenthesesTest() {
		fail("Not yet implemented");
	}

}
