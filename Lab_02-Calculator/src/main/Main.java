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
		String expression = "-(10+5)+20";
		
//		System.out.println(sy.convert(eval.evaluateExpression(expression)));
		
		System.out.println(calc.calculate(expression));

	
	}

}
