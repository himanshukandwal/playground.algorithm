package dev.research.himanshu.algorithm;

import dev.research.himanshu.algorithm.ShortestPalindrome;
import junit.framework.TestCase;

public class ShortestPalindromeTest extends TestCase {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	protected void setUp() throws Exception {
		super.setUp();
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public void testCase1() {
		ShortestPalindrome palindromeMaker = new ShortestPalindrome();

		timer();
		String output = palindromeMaker.shortestPalindromeAnother("babbbabbaba");
		timer();

		assertEquals("ababbabbbabbaba", output);
	}
	
	public void testCase2() {
		ShortestPalindrome palindromeMaker = new ShortestPalindrome();

		timer();
		String output = palindromeMaker.shortestPalindromeAnother("ba");
		timer();

		assertEquals("aba", output);
	}
	
	public void testCase3() {
		ShortestPalindrome palindromeMaker = new ShortestPalindrome();

		timer();
		String output = palindromeMaker.shortestPalindromeAnother("babb");
		timer();

		assertEquals("bbabb", output);
	}
	
	public void testCase4() {
		ShortestPalindrome palindromeMaker = new ShortestPalindrome();

		timer();
		String output = palindromeMaker.shortestPalindromeAnother("bacaab");
		timer();

		assertEquals("baacabacaab", output);
	}
	
	public void testCase5() {
		ShortestPalindrome palindromeMaker = new ShortestPalindrome();

		timer();
		String output = palindromeMaker.shortestPalindromeAnother("bacaabbbbbbb");
		timer();

		assertEquals("bbbbbbbaacabacaabbbbbbb", output);
	}
	
	public void testCase6() {
		ShortestPalindrome palindromeMaker = new ShortestPalindrome();

		timer();
		String output = palindromeMaker.shortestPalindromeAnother("abb");
		timer();

		assertEquals("bbabb", output);
	}
}
