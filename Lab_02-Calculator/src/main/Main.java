import java.text.ParseException;

public class Main {

	public static void main(String[] args) {
		Evaluator eval = new Evaluator();
		try {
			System.out.println(eval.evaluateExpression("-(5+5)"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
