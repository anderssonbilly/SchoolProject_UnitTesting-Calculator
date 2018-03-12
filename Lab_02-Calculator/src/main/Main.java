import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class Main {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Evaluator eval = new Evaluator();
		ShuntingYard sy = new ShuntingYard();
		
		String expression = "(2+5)*(2+5)/49";
		
		System.out.println();
		try {
			System.out.println(sy.convert(eval.evaluateExpression(expression)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(calc.calculate(expression));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
