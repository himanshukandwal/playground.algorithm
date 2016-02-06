package dev.research.himanshu.algorithm;

import junit.framework.TestCase;

public class WordPatternTest extends TestCase {

	/**
	 * Way 1
	 */
	public void testPattern1() throws Exception {
		assertEquals(true, WordPattern.wordPattern("abba", "dog cat cat dog"));
	}
	
	public void testPattern2() throws Exception {
		assertEquals(false, WordPattern.wordPattern("abba", "dog cat cat fish"));
	}
	
	public void testPattern3() throws Exception {
		assertEquals(false, WordPattern.wordPattern("aaa", "dog cat cat fish"));
	}

	public void testPattern4() throws Exception {
		assertEquals(false, WordPattern.wordPattern("jquery", "jquery"));
	}

	public void testPattern5() throws Exception {
		assertEquals(false, WordPattern.wordPattern("abba", "dog dog dog dog"));
	}
	
	/**
	 * Way 2
	 */
	public void testPattern6() throws Exception {
		assertEquals(true, WordPattern.wordPattern2("abba", "dog cat cat dog"));
	}
	
	public void testPattern7() throws Exception {
		assertEquals(false, WordPattern.wordPattern2("abba", "dog cat cat fish"));
	}
	
	public void testPattern8() throws Exception {
		assertEquals(false, WordPattern.wordPattern2("aaa", "dog cat cat fish"));
	}

	public void testPattern9() throws Exception {
		assertEquals(false, WordPattern.wordPattern2("jquery", "jquery"));
	}

	public void testPattern10() throws Exception {
		assertEquals(false, WordPattern.wordPattern2("abba", "dog dog dog dog"));
	}
	
}
