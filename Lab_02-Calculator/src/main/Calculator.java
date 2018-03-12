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

		// Reverse polish notation evaluation
		return rpnEvaluation(sy.convert(eval.evaluateExpression(expression)));
	}

	private double rpnEvaluation(ArrayList<String> expression) {

		stack = new Stack<Double>();
		// Loop all tokens in expression
		for (String token : expression) {
			// If token is only one char
			// and not a number
			// And the stack contains numbers
			if (token.length() == 1 && !IntChecker.check(String.valueOf(token.charAt(0))) && !stack.empty()) {
				// Pop first number
				double operand2 = stack.pop();
				if (!stack.empty()) {
					// If stack isnt empty pop second number
					double operand1 = stack.pop();

					// Make proper calculation depending on wich token being used
					if (token.equals("-"))
						stack.push(operand1 - operand2);
					else if (token.equals("+"))
						stack.push(operand1 + operand2);
					else if (token.equals("/")) {
						// If operand2 is a 0 throw an exception, we cant divide by zero
						if (operand2 != 0)
							stack.push(operand1 / operand2);
						else
							throw new ArithmeticException("Division by zero exception");
					} else if (token.equals("*"))
						stack.push(operand1 * operand2);
					else if (token.equals("%"))
						stack.push(operand1 % operand2);
					else if (token.equals("^"))
						stack.push(Math.pow(operand1, operand2));
					else
						// If token cant be found throw an exception
						throw new ArithmeticException(
								"General arimethic exception, cant calculate " + operand1 + token + operand2);

				} else if (token.equals("-"))
					// If there isnt an second number in the stack
					// and the token is a -
					// Then create a negative number
					stack.push(convertToDouble(token + operand2));
			} else
				// Push number to stack
				stack.push(convertToDouble(token));

		}
		// return number from stack, this is out result
		// If there isnt any number on the stack throw an exception
		if (stack.empty())
			throw new ArithmeticException("Cant calculate one number only exception");
		return stack.pop();
	}

	private double convertToDouble(String i) throws ArithmeticException {
		// Convert string to double
		return Double.valueOf(i);
	}
}
