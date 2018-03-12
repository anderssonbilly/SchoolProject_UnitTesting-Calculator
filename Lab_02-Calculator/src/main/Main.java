import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class Main {

	public static void main(String[] args) throws ParseException {
		Calculator calc = new Calculator();
		Evaluator eval = new Evaluator();
		ShuntingYard sy = new ShuntingYard();
		String expression = "-2+1";
		
		System.out.println(calc.calculate(expression));
//		System.out.println(-2d + 1d);
//		System.out.println(Double.valueOf("-1"));
//		return Stream.of(Arguments.of("-2+1", -1.0),
//				Arguments.of("2+-1", 1.0),
//				Arguments.of("2--2",4.0));
	}

}
