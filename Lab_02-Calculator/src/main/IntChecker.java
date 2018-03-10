public class IntChecker {
	// Method that will check if the input string is a double
	// Will not accept , only .
	public static boolean check(String s) throws NumberFormatException{
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
