import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator {

	private final Pattern pattern = Pattern.compile("[^0123456789/*\\-+^%.()]");
	private ArrayList<String> infix;
	private Stack<String> stack;
	private Stack<String> pStack;

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

		// Stack to check for the correct number of parentheseses
		pStack = new Stack<String>();

		// Stringbuilder for building the numbers to be added to infix
		StringBuilder sb = new StringBuilder();

		// Loop throu String expression token for token
		for (int i = 0; i < expression.length(); i++) {
			// Save token for easy access
			String token = String.valueOf(expression.charAt(i));

			// Parenthesese check
			if (token.equals("(") || token.equals(")")) {
				// If not empty and is a )
				if (!pStack.isEmpty() && token.equals(")")) {
					// And the top isnt a ( throw an error
					if (!pStack.pop().equals("("))
						throw new ParseException("Wrong number of parentheseses", 0);
				} else if (token.equals(")"))
					// else if trying to add ) to empty stack throw error
					throw new ParseException("Wrong number of parentheseses", 0);
				else {
					// Else push to stack
					pStack.push(token);
				}
			}

			// If the first token in the expression is an operator
			if (i == 0 && !IntChecker.check(token)) {
				// And is a minus
				if (token.equals("-"))
					// Push to stack
					stack.push(token);
				else if (token.equals("("))
					// And if it is an ( add it to infix
					infix.add(token);
				else
					// Throw exception is token at begining is any other character
					throw new ParseException("Expression cant begin with an operator", 0);
			} else if (!IntChecker.check(token)) {
				// If the token is an operator

				// And we got more than 2 characters in infix
				if (infix.size() > 2) {
					// Check if there are two operators in a row
					// with the exception of if ) isnt at the second last and the last isnt a (
					boolean last = IntChecker.check(infix.get(infix.size() - 1));
					boolean nextToLast = IntChecker.check(infix.get(infix.size() - 2));
					// If there are throw an exception
					if (!last && !nextToLast && !infix.get(infix.size() - 2).equals(")")
							&& !infix.get(infix.size() - 1).equals("("))
						throw new ParseException("The two last in output is op - Cant have multiple operators in a row",
								0);
				}
				// If the stack contains characters
				if (!stack.empty()) {
					// If top of stack is an operator and it isnt a - or a (
					// Throw an error
					if (!IntChecker.check(stack.peek()) && !(stack.peek().equals("-") && token.equals("(")))
						throw new ParseException("Top of stack error - Cant have multiple operators in a row", 0);

					// While there are characters in the stack, add them to SB
					while (!stack.empty())
						sb.insert(0, stack.pop());

					// Then add that string to infix expression and empty the SB
					infix.add(sb.toString());
					sb = new StringBuilder();
					// Add operator to infix
					infix.add(token);
				} else if (token.equals("-"))
					// If the token is a -

					// Push minus to stack to create negative numbers
					// Only if the last character in infix isnt a )
					// If so add it to infix instead
					if (!infix.get(infix.size() - 1).equals(")"))
						stack.push(token);
					else
						infix.add(token);
				else if (token.equals("(")) {
					// If the token is a (, add it to infix
					infix.add(token);
				} else {
					// Else if the last character isnt a )
					// Add it to infix otherwise throw an exception
					if (!infix.get(infix.size() - 1).equals(")"))
						throw new ParseException("Empty stack - Cant have multiple operators in a row", 0);
					else
						infix.add(token);
				}
			} else {
				// If token is a number
				if (!stack.empty()) {
					// And stack contains characters and it is an (
					if (stack.peek().equals("(")) {
						while (!stack.empty())
							sb.insert(0, stack.pop());
						// Write stack to infix
						infix.add(sb.toString());
						sb = new StringBuilder();
					}
				}
				// Else push number to stack
				stack.push(token);
			}
		}

		// If the parenthesese stack still contains characters throw an error
		if (!pStack.empty())
			throw new ParseException("Wrong number of parentheseses", 0);

		// Pop the last tokens from the stack and add to the infix
		while (!stack.empty())
			sb.insert(0, stack.pop());

		// If SB contains characters add them to infix
		if (sb.toString().length() > 0)
			infix.add(sb.toString());

		// Return an infix expression in an arrayList
		return infix;
	}
}
