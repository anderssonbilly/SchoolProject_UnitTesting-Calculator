import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

	private Evaluator eval;
	private ShuntingYard sy;
	private Stack<Double> stack;

	public double calculate(String expression) throws ParseException {
		eval = new Evaluator();
		ShuntingYard sy = new ShuntingYard();
		return rpnEvaluation(sy.convert(eval.evaluateExpression(expression)));
	}

	private double rpnEvaluation(ArrayList<String> expression) {

		stack = new Stack<Double>();

		for (String token : expression) {
			if (token.length() == 1 && !IntChecker.check(String.valueOf(token.charAt(0)))) {
					double operand2 = stack.pop();
					double operand1 = stack.pop();

					if (token.equals("-")) {
						stack.push(operand1 - operand2);
					} else if (token.equals("+")) {
						stack.push(operand1 + operand2);
					} else if (token.equals("/")) {
						if (operand2 != 0)
							stack.push(operand1 / operand2);
						else
							throw new ArithmeticException("Division by 0 exception");
					} else if (token.equals("*")) {
						stack.push(operand1 * operand2);
					} else if (token.equals("%")) {
						stack.push(operand1 % operand2);
					} else if (token.equals("^")) {
						stack.push(Math.pow(operand1, operand2));
					} else {
						throw new ArithmeticException(
								"General arimethic exception, cant calculate " + operand1 + token + operand2);
					}
				
			} else {
				stack.push(convertToDouble(token));
			}
		}
		return stack.pop();
	}

	private double convertToDouble(String i) throws ArithmeticException {
		return Double.valueOf(i);
	}
}
