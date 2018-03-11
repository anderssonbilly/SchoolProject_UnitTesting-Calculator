import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			System.out.println("iteration: " + i);
			String token = String.valueOf(expression.charAt(i));

			// If the token at the beginning is an operator
			if (i == 0 && !IntChecker.check(token)) {
				// And is a minus
				if (token.equals("-"))
					// Push to stack
					stack.push(token);
				else
					// Throw exception is token at begining aint a -
					throw new ParseException("Expression cant begin with an operator", 0);
			} else if (!IntChecker.check(token)) {
				if(i>3) {
					// Check if there are two operators in a row
					boolean last = IntChecker.check(infix.get(infix.size()-1));
					boolean nextToLast = IntChecker.check(infix.get(infix.size()-2));
					// If there are throw an exception
					if(!last && !nextToLast)
							throw new ParseException("not last and next Cant have multiple operators in a row", 0);
				}
				// Check if last in infix is an operator and top of stack
				if(!stack.empty()) 
					// If there are throw an exception
					if(!IntChecker.check(stack.peek()))
							throw new ParseException("Top of stack is operator Cant have multiple operators in a row", 0);
			
				// If token isnt an number and the top token on the stack
				// pop tokens from the stack into a string
				// else throw exception
				if (!stack.empty()) {
					while (!stack.empty())
						sb.insert(0, stack.pop());

					// Then add that string to infix expression
					infix.add(sb.toString());
					sb = new StringBuilder();
					// Add operator to infix after number
					infix.add(token);
				} else if (token.equals("-")) 
					// Push minus to stack to create negative numbers
						stack.push(token);				
				 else
					throw new ParseException("Cant have multiple operators in a row", 0);
			} else
				stack.push(token);
		}

		// Pop the last tokens from the stack and add to the expression
		while (!stack.empty())
			sb.insert(0, stack.pop());
		infix.add(sb.toString());

		// Return an infix expression in an arrayList
		return infix;
	}
}
