package dev.research.himanshu.algorithm;

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
}
