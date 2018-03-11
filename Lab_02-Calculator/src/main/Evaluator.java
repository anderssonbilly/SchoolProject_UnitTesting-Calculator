import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Evaluator {

	private final Pattern pattern = Pattern.compile("[^0123456789/*\\-+^%.()]");
	private ArrayList<String> infix;
	private Stack<String> stack;

	// Check if the String is a valid expression and sets it up to be
	// converted to Reverse Polish Notation with the Shunting Yard algorithm
	public ArrayList<String> evaluateExpression(String expression) throws ParseException {

		// First check expression for illegal characters
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find())
			// If found throw exception
			throw new ParseException("Expression contains illegal characters", 0);

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
				else if (token.equals("("))
					infix.add(token);
				else
					// Throw exception is token at begining aint a -
					throw new ParseException("Expression cant begin with an operator", 0);
			} else if (!IntChecker.check(token)) {
//				System.out.println();
//				System.out.println("At op check");
//				System.out.println("token: " + token);
//				System.out.println("Stack: " + stack.toString());
//				System.out.println("infix: " + infix.toString());

				if (infix.size() > 2) {
					// Check if there are two operators in a row
					boolean last = IntChecker.check(infix.get(infix.size() - 1));
					boolean nextToLast = IntChecker.check(infix.get(infix.size() - 2));

					// If there are throw an exception
					if (!last && !nextToLast && !infix.get(infix.size() - 2).equals(")")
							&& !infix.get(infix.size() - 1).equals("("))
						throw new ParseException("The two last in output is op - Cant have multiple operators in a row",
								0);
				}
				// Check if last in infix is an operator and top of stack
				if (!stack.empty()) {
					// If token isnt an number and the top token on the stack
					// pop tokens from the stack into a string
					// If there are throw an exception
					if (!IntChecker.check(stack.peek()) && !(stack.peek().equals("-") && token.equals("(")))
						throw new ParseException("Top of stack op - Cant have multiple operators in a row", 0);

//					if (!(stack.peek().equals("-") && token.equals("("))) {
						while (!stack.empty())
							sb.insert(0, stack.pop());

						// Then add that string to infix expression
						infix.add(sb.toString());
						sb = new StringBuilder();
						// Add operator to infix after number
						infix.add(token);
//					} else
//						stack.push(token);
				} else if (token.equals("-"))
					// Push minus to stack to create negative numbers
					if (!infix.get(infix.size() - 1).equals(")"))
						stack.push(token);
					else
						infix.add(token);
				else if (token.equals("(")) {
					infix.add(token);
				} else {
					if (!infix.get(infix.size() - 1).equals(")"))
						throw new ParseException("Empty stack - Cant have multiple operators in a row", 0);
					else
						infix.add(token);
				}
			} else {
				if (!stack.empty()) {
					if (stack.peek().equals("(")) {
						while (!stack.empty())
							sb.insert(0, stack.pop());
						infix.add(sb.toString());
						sb = new StringBuilder();
					}
				}
				stack.push(token);
			}

//			System.out.println();
//			System.out.println("At end");
//			System.out.println("token: " + token);
//			System.out.println("Stack: " + stack.toString());
//			System.out.println("infix: " + infix.toString());
		}

		// Pop the last tokens from the stack and add to the expression
		while (!stack.empty())
			sb.insert(0, stack.pop());

		if (sb.toString().length() > 0)
			infix.add(sb.toString());

		// Return an infix expression in an arrayList
		return infix;
	}
}
