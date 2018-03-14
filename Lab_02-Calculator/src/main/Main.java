import java.util.Scanner;

public class Main {

	public static Calculator calc;
	public static Evaluator eval;
	public static ShuntingYard sy;
	public static Scanner in;

	public static boolean run;
	public static boolean shunting;

	public static void main(String[] args) {

		calc = new Calculator();
		eval = new Evaluator();
		sy = new ShuntingYard();
		in = new Scanner(System.in);

		run = true;
		shunting = false;
		
		String cmd;

		printInfo();
		while (run) {
			System.out.println("");
			System.out.println("------");
			System.out.println("");
			System.out.println("Input:");
			cmd = in.nextLine();

			if (cmd.toLowerCase().equals("exit") || cmd.toLowerCase().equals("quit"))
				run = false;
			else if (cmd.toLowerCase().equals("cmd") || cmd.toLowerCase().equals("commands")) {
				printCommands();
			} else if (cmd.toLowerCase().equals("info")) {
				printInfo();
			} else if (cmd.toLowerCase().equals("rpn")) {
				shunting = (shunting) ? false: true;
			} else {
				System.out.println("");
				try {
					if(shunting) {
						String postfix = sy.convert(eval.evaluateExpression(cmd)).toString();
						System.out.println("Postfix:");
						System.out.println(postfix);
					}
					double result = calc.calculate(cmd);
					System.out.println("Result: ");
					System.out.println(result);
				} catch (Exception e) {
					System.err.println(e.getClass().getSimpleName() + ": " + e.getMessage());
				}
			}
		}
		System.out.println("Program terminated...");
	}

	public static void printInfo() {
		System.out.println("");
		System.out.println("------");
		System.out.println("");
		System.out.println("Laboration 2 - Calcylator");
		System.out.println("Author: Billy Andersson - anderssonbilly@hotmail.com");
		System.out.println("");
		System.out.println("Can handle: + - * / % ^");
		System.out.println("It also supports negative numbers, decimals and parentheseses");
		System.out.println("");
		System.out.println("Write expression and press enter to get result");
		System.out.println("The expression can't contain any spaces or other illegal characters");
		System.out.println("");
		System.out.println("Type 'cmd' to see extra commands");
		System.out.println("Type 'exit' to termniate the program");
	}

	public static void printCommands() {
		System.out.println("");
		System.out.println("------");
		System.out.println("");
		System.out.println("Commands:");
		System.out.println("");
		System.out.println("info - Shows startup info");
		System.out.println("cmd  - Shows this info");
		System.out.println("rpn - Toggles viewing of expression in postfix");
		System.out.println("exit - Exits the program");
	}
}
