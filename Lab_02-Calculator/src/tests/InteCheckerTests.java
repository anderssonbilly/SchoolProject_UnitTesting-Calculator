import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InteCheckerTests {

	@Test
	void maxNumberTest() {
		new IntChecker();
		assertTrue(IntChecker.check(String.valueOf(Double.MAX_VALUE)));
	}
	
	@Test
	void minNumberTest() {
		new IntChecker();
		assertTrue(IntChecker.check(String.valueOf(Double.MIN_VALUE)));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"0","1","-123456","-1","4.2131654","-46512498.13215648641","NaN"})
	void smallNumbersTest(String testValue) {
		new IntChecker();
		assertTrue(IntChecker.check(testValue));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"error","","^","\n","   "," ","a1","12a","1,a","a,1", "1,1"})
	void stringTest(String testValue) {
		new IntChecker();
		assertFalse(IntChecker.check(testValue));
	}
}
