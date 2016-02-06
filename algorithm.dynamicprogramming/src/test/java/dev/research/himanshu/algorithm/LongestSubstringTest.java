package dev.research.himanshu.algorithm;

import junit.framework.TestCase;

public class LongestSubstringTest extends TestCase {
	
	public void testFindLongestSubstring1() throws Exception {
		assertEquals(1, LongestSubstring.lengthOfLongestSubstring("aaa"));
	}
	
	public void testFindLongestSubstring2() throws Exception {
		assertEquals(2, LongestSubstring.lengthOfLongestSubstring("aab"));
	}
	
	public void testFindLongestSubstring3() throws Exception {
		assertEquals(3, LongestSubstring.lengthOfLongestSubstring("dvdf"));
	}
	
	public void testFindLongestSubstring4() throws Exception {
		assertEquals(5, LongestSubstring.lengthOfLongestSubstring("tmmzuxt"));
	}
	
	public void testFindLongestSubstring5() throws Exception {
		assertEquals(4, LongestSubstring.lengthOfLongestSubstring("vqblqcb"));
	}
	
}
