import java.text.ParseException;

public class Main {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Evaluator eval = new Evaluator();
		ShuntingYard sy = new ShuntingYard();
		
		String expression = "1.5+0.5";
		
		System.out.println();
		try {
			System.out.println(sy.convert(eval.evaluateExpression(expression)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(eval.evaluateExpression(expression));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	
	}

}
