import java.util.ArrayList;

public class Evaluator {
	// Check if the String is a valid expression and sets it up to be 
	// converted to Reverse Polish Notation with the Shunting Yard algorithm 
	public ArrayList<String> evaluateExpression(String expression) {
		// Set up an array for the infix expression
		ArrayList<String> infix = new ArrayList<String>();
		
		// TODO not yet implemented
		for (int i=0;i<expression.length();i++) 
			infix.add(String.valueOf(expression.charAt(i)));
		
		// Return an infix expression in an arrayList
		return infix;
	}
}
