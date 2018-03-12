import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Evaluator eval = new Evaluator();
//		try {
//			System.out.println(eval.evaluateExpression("(1+(25-5))"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		ShuntingYard sy = new ShuntingYard();
//		try {
//			System.out.println(sy.convert(new ArrayList<String>(Arrays.asList("(","1","+","(","4", "+", "-5",")","+","-1",")", "^", "2"))));
//		} catch (ParseException e) {
//			// TODO: handle exception
//		}
//		try {
//			System.out.println(sy.convert(eval.evaluateExpression("1+2/5+(8*7)")));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
