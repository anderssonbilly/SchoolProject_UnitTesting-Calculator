import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

public class ShuntingYard {

	private ArrayList<String> postfix;
	private Stack<Integer> stack;

	// The Shunting Yard algorithm.
	// It converts regular infix expressions 1+1 to post fix 1 1 +
	// With postfix we dont have to think about operator priority or parentheseses
	public ArrayList<String> convert(ArrayList<String> expression) throws ParseException {
		// Create postfix array
		postfix = new ArrayList<String>();
		stack = new Stack<Integer>();

		// Setup operators and their priority
		// Operator priority corresponds to its index in the array
		final String operators = "-+/*%^";
		final int[] priority = { 0, 0, 1, 1, 1, 2 };

		// Loop all tokens in expression
		for (String token : expression) {

			// First char in token, used to check operator index
			char c = token.charAt(0);
			int index;

			// Check if token is an operator
			if(token.length() == 1) 
				// If it is get operator index
				index = operators.indexOf(c);
			else
				// Else marks it as an non operator
				index = -1;
			
			// If token is an operator
			if (index != -1) {
				// If stack is empty push index of operator to stack
				if (stack.isEmpty())
					stack.push(index);
				else {
					// Else if stack contains operators
					// Loop throu the stack
					while (!stack.empty()) {
						// Stack precedence
						int prec2;
						// Get precedence from stack, if stack index is -2 that means it's an
						// parenthesese
						if (stack.peek() != -2)
							prec2 = priority[stack.peek()];
						else
							prec2 = -2;
						// Compare stack precedence with index precedence
						// If stack index got higher then pop it to postfix output
						// or if they got same precedence and topen aint a ^ becous it got other
						// associativity
						if (prec2 > priority[index] || (prec2 == priority[index] && c != '^'))
							postfix.add(String.valueOf(operators.charAt(stack.pop())));
						else
							// Else break loop
							break;
					}
					// Push operator index to stack
					stack.push(index);
				}
			} else if (c == '(')
				// If token is an (, add it's index as -2 to stack to give it low precedence
				stack.push(-2);
			else if (c == ')') {
				// If token is ) and stack isnt empty loop the stack and pop operators to
				// postfix until you find an (
				if (!stack.empty()) {
					while (stack.peek() != -2)
						postfix.add(String.valueOf(operators.charAt(stack.pop())));
					// Then pop the (
					stack.pop();
				} else
					// Else throw an exception
					throw new ParseException("Wrong number of parentheseses", 0);

			} else
				// Else add the token to postfix
				postfix.add(token);
		}
		// If there are any operators left in stack
		// Pop them to the end of postfix
		// But if there are any parentheseses on the stack throw an exception
		while (!stack.empty()) {
			if (stack.peek() != -2)
				postfix.add(String.valueOf(operators.charAt(stack.pop())));
			else
				throw new ParseException("Wrong number of parentheseses when converting to postfix", 0);
		}

		return postfix;
	}
}
