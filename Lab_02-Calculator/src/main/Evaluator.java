import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator {

	private final Pattern pattern = Pattern.compile("[^0123456789/*\\-+^%.]");
	private ArrayList<String> infix;
	private Stack<String> stack;

	// Check if the String is a valid expression and sets it up to be
	// converted to Reverse Polish Notation with the Shunting Yard algorithm
	public ArrayList<String> evaluateExpression(String expression) {

		// First check expression for illegal characters
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find())
			// TODO If found throw exception
			return null;

		// Set up an array for the infix expression
		infix = new ArrayList<String>();

		// Stack to keep track of number
		stack = new Stack<String>();

		// Stringbuilder for builder number to be added to expression
		StringBuilder sb = new StringBuilder();

		// Loop throu String expression token for token
		for (int i = 0; i < expression.length(); i++) {
			String token = String.valueOf(expression.charAt(i));

			// If the token at the beginning is an operator
			if (i == 0 && !IntChecker.check(token)) {
				// And is a minus
				if (token.equals("-"))
					// Push to stack
					stack.push(token);
				else
					// TODO Throw exception
					return null;
			} else if (IntChecker.check(token)) {
				// If token is a number, push to stack
				stack.push(token);
			} else {
				// If token is an operator and the top token on the stack aint
				// pop tokens from the stack into a string
				// else throw exception
				System.out.println("Stack: " + stack.toString());
				if (IntChecker.check(stack.peek())) {
					while (!stack.empty())
						sb.insert(0, stack.pop());
				} else
					// TODO Throw exception
					return null;

				// Then add that string to infix expression
				infix.add(sb.toString());
				sb = new StringBuilder();
				// Add operator to infix after number
				infix.add(token);
			}
		}

		// Pop the last tokens from the stack and add to the expression
		while (!stack.empty())
			sb.insert(0, stack.pop());
		infix.add(sb.toString());

		// Return an infix expression in an arrayList
		return infix;
	}
}
