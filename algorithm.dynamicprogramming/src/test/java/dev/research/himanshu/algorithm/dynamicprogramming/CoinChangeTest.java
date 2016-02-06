package dev.research.himanshu.algorithm.dynamicprogramming;

import junit.framework.TestCase;

public class CoinChangeTest extends TestCase {

	public void testcase1() throws Exception {
		assertEquals(3, CoinChange.coinChange(new int[] {1, 2, 5},  11));
	}
	
	public void testcase2() throws Exception {
		assertEquals(4, CoinChange.coinChange(new int[] {2, 5, 10, 1},  27));
	}
	
	public void testcase3() throws Exception {
		assertEquals(20, CoinChange.coinChange(new int[] {186, 419, 83, 408},  6249));
	}
	
	public void testcase4() throws Exception {
		assertEquals(-1, CoinChange.coinChange(new int[] {1},  0));
	}
	
	public void testcase5() throws Exception {
		assertEquals(26, CoinChange.coinChange(new int[] {336, 288, 378, 16, 319, 146},  9212));
	}
	
	public void testcase6() throws Exception {
		assertEquals(21, CoinChange.coinChange(new int[] {58, 92, 387, 421, 194, 208, 231},  7798));
	}
}
