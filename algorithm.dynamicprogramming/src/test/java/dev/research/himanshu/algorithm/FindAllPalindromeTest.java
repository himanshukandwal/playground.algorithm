package dev.research.himanshu.algorithm;

import junit.framework.TestCase;

public class FindAllPalindromeTest extends TestCase {
	
	public void testFindPalindrome1() throws Exception {
		assertEquals(5, FindAllPalindrome.palindrome("aabaa"));
	}
	
	public void testFindPalindrome2() throws Exception {
		assertEquals(2, FindAllPalindrome.palindrome("aa"));
	}
	
	public void testFindPalindrome3() throws Exception {
		assertEquals(3, FindAllPalindrome.palindrome("aba"));
	}
	
	public void testFindPalindrome4() throws Exception {
		assertEquals(2, FindAllPalindrome.palindrome("ab"));
	}
	
	public void testFindPalindrome5() throws Exception {
		assertEquals(5, FindAllPalindrome.palindrome("dalad"));
	}
	
	public void testFindPalindrome6() throws Exception {
		assertEquals(4, FindAllPalindrome.palindrome("aaaa"));
	}
	
	public void testFindPalindrome7() throws Exception {
		assertEquals(4, FindAllPalindrome.palindrome("aaba"));
	}
	
	public void testFindPalindrome8() throws Exception {
		assertEquals(5, FindAllPalindrome.palindrome("aaaaa"));
	}

}
