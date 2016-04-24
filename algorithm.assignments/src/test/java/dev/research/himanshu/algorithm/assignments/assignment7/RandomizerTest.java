package dev.research.himanshu.algorithm.assignments.assignment7;

import junit.framework.TestCase;

public class RandomizerTest extends TestCase {

	public void testRandomization() throws Exception {
		for (int index = 1; index < 100000; index++) {
			int randomValue = Randomizer.randomValue(index);
			
			assertTrue("failed test for index : " + index + " returned value : " + randomValue, 
					(0 <= randomValue) && (randomValue < index));
		}
	}
}
