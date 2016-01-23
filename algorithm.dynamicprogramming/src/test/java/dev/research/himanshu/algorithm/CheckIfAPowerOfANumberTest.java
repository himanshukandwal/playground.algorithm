package dev.research.himanshu.algorithm;

import static dev.research.himanshu.algorithm.CheckIfAPowerOfANumber.isPowerOfThree;

import junit.framework.TestCase;

public class CheckIfAPowerOfANumberTest extends TestCase {
	
	public void testCheckPowerCase1() throws Exception {
		assertEquals(false, isPowerOfThree(2));
		assertEquals(false, isPowerOfThree(0));
		assertEquals(false, isPowerOfThree(82));
		assertEquals(true, isPowerOfThree(81));
		assertEquals(false, isPowerOfThree(80));
		assertEquals(true, isPowerOfThree(243));
		assertEquals(true, isPowerOfThree(59049));
	}
}
