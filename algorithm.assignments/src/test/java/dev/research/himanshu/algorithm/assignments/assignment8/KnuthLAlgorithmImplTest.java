package dev.research.himanshu.algorithm.assignments.assignment8;

import static org.junit.Assert.*;
import org.junit.Test;

public class KnuthLAlgorithmImplTest {
	
	@Test
	public void testAlgorithm() {
		assertEquals(24, KnuthLAlgorithmImpl.algorithmL(new int[] {1, 2, 3, 4}));
		assertEquals(12, KnuthLAlgorithmImpl.algorithmL(new int[] {1, 2, 2, 3}));
	}
	
	@Test
	public void testAlgorithmMain() {
		KnuthLAlgorithmImpl.main(new String[] { System.getProperty("user.dir") + "/src/main/resources/assignment8/knuth-algorithm/input.txt" });
	}

}
