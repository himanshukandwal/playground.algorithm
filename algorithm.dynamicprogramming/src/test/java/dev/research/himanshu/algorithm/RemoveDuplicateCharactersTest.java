package dev.research.himanshu.algorithm;

import junit.framework.TestCase;

public class RemoveDuplicateCharactersTest extends TestCase {
	
	public void testScenario1() throws Exception {
		assertEquals("abc", RemoveDuplicateCharacters.removeDuplicateLetters("bcabc"));
	}

	public void testScenario2() throws Exception {
		assertEquals("acdb", RemoveDuplicateCharacters.removeDuplicateLetters("cbacdcbc"));
	}
	
}