import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTests {

	static Stream<Arguments> addArgumentProvider() {
		return Stream.of(Arguments.of("1+2", 3.0),
				Arguments.of("3+4", 7.0),
				Arguments.of("10+100",110.0),
				Arguments.of("999+1",1000.0));
	}

	@ParameterizedTest
	@MethodSource("addArgumentProvider")
	void addTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));
	}

	@Test
	void overflowTest() {
		String expression = "1+" + String.valueOf(Double.MAX_VALUE);
		Calculator calc = new Calculator();
		assertThrows(ParseException.class, () -> calc.calculate(expression));
	}

	static Stream<Arguments> substractArgumentProvider() {
		return Stream.of(Arguments.of("2-1", 1.0),
				Arguments.of("3-3", 0.0),
				Arguments.of("1000-1",999.0),
				Arguments.of("555-15",540.0));
	}

	@ParameterizedTest
	@MethodSource("substractArgumentProvider")
	void substractTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}

	@Test
	void underflowTest() {
		String expression = "1-" + String.valueOf(Double.MIN_VALUE);
		Calculator calc = new Calculator();
		assertThrows(ParseException.class, () -> calc.calculate(expression));
	}

	static Stream<Arguments> multiplyArgumentProvider() {
		return Stream.of(Arguments.of("1*1", 1.0),
				Arguments.of("3*3", 9.0),
				Arguments.of("25*25",625.0),
				Arguments.of("99*0",0.0));
	}

	@ParameterizedTest
	@MethodSource("multiplyArgumentProvider")
	void multiplyTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}

	static Stream<Arguments> divideArgumentProvider() {
		return Stream.of(Arguments.of("5/2", 2.5),
				Arguments.of("3/3", 1.0),
				Arguments.of("75/25",3.0),
				Arguments.of("10/3",3.3333333333333335));
	}

	@ParameterizedTest
	@MethodSource("divideArgumentProvider")
	void divideTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}

	@Test
	void divideByZeroTest() {
		Calculator calc = new Calculator();
		assertThrows(ArithmeticException.class, () -> calc.calculate("2/0"));
	}

	@Test
	void oneNumberandOpTest() {
		Calculator calc = new Calculator();
		assertThrows(ArithmeticException.class, () -> calc.calculate("1+"));
	}
	
	static Stream<Arguments> modulusArgumentProvider() {
		return Stream.of(Arguments.of("25%10", 5.0),
				Arguments.of("7%4", 3.0),
				Arguments.of("100%21",16.0));
	}

	@ParameterizedTest
	@MethodSource("modulusArgumentProvider")
	void modulusTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}

	static Stream<Arguments> powerOfArgumentProvider() {
		return Stream.of(Arguments.of("2^4", 16.0),
				Arguments.of("10^2", 100.0),
				Arguments.of("30^100",5.1537752073201134E147));
	}

	@ParameterizedTest
	@MethodSource("powerOfArgumentProvider")
	void powerOfTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}
	
	static Stream<Arguments> decimalArgumentProvider() {
		return Stream.of(Arguments.of("0.5+1.5", 2.0),
				Arguments.of("2*0.2", 0.4),
				Arguments.of("20*0.7",14.0));
	}

	@ParameterizedTest
	@MethodSource("decimalArgumentProvider")
	void decimalTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}
	
	static Stream<Arguments> negativeArgumentProvider() {
		return Stream.of(Arguments.of("-2+1", -1.0),
				Arguments.of("2+-1", 1.0),
				Arguments.of("2--2",4.0));
	}

	@ParameterizedTest
	@MethodSource("negativeArgumentProvider")
	void negativeTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}
	
	static Stream<Arguments> multipleOpsArgumentProvider() {
		return Stream.of(Arguments.of("2+1*25/2-(13*7)", -76.5),
				Arguments.of("1-1-5-25*10", -255.0),
				Arguments.of("(2+5)*(2+5)/49",1.0));
	}

	@ParameterizedTest
	@MethodSource("multipleOpsArgumentProvider")
	void multipleOpsTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}
	
	static Stream<Arguments> parenthesesesArgumentProvider() {
		return Stream.of(Arguments.of("(1+2)*(3+4)", 21.0),
				Arguments.of("10/(2+3)", 2.0),
				Arguments.of("-(10+5)+20",5.0),
				Arguments.of("(1+2)*(-1--6)",15.0),
				Arguments.of("(1+2)*-(-1--6)",-5.0));
	}

	@ParameterizedTest
	@MethodSource("parenthesesesArgumentProvider")
	void parenthesesesTest(String expression, double expected) throws ParseException {
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}
	
	@Test
	void sevenPrecentOfThirtyTest() throws ParseException {
		String expression = "30*0.07";
		double expected = 2.1;
		Calculator calc = new Calculator();
		assertEquals(expected, calc.calculate(expression));;
	}
}