import java.util.ArrayList;
import java.util.Stack;

public class ShuntingYard {

	private ArrayList<String> postfix;
	private Stack<Integer> stack;

	// The Shunting Yard algorithm.
	// It converts regular infix expressions 1+1 to post fix 1 1 +
	// With postfix we dont have to think about operator priority or parentheseses
	public ArrayList<String> convert(ArrayList<String> expression) {
		// Create postfix array
		postfix = new ArrayList<String>();
		stack = new Stack<Integer>();

		// Setup operators and their priority
		// Operator priority corresponds to its index in the array
		final String operators = "-+/*%^";
		final int[] priority = { 0, 0, 1, 1, 1, 2 };

		// Loop all tokens in expression
		for (String token : expression) {
			// Get first char in token to check if it's an operator
			char c = token.charAt(0);
			// Check if char exsists in operators string if > -1 then it exsists
			int index = operators.indexOf(c);

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
				// If token is ) loop the stack and pop operators to postfix until you find an (
				while (stack.peek() != -2)
					postfix.add(String.valueOf(operators.charAt(stack.pop())));
				// Then pop the (
				stack.pop();
			} else
				// Else add the token to postfix
				postfix.add(token);
		}
		// If there are any operators left in stack
		// Pop them to the end of postfix
		while (!stack.empty())
			postfix.add(String.valueOf(operators.charAt(stack.pop())));

		return postfix;
	}
}
