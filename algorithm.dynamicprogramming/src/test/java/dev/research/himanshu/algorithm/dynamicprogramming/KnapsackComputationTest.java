package dev.research.himanshu.algorithm.dynamicprogramming;

import junit.framework.TestCase;

public class KnapsackComputationTest extends TestCase {
	
	public void testCheckKnapsack() throws Exception {
		int [][] data = new int[][] {
			{10, 60},
			{20, 100},
			{30, 120}
		};
		
		assertEquals(300, KnapsackComputation.recurseEvaluationDuplicatesAllowed(data, 50, 0));
		assertEquals(220, KnapsackComputation.recurseEvaluationWithoutDuplicates(data, 50, 0));
	}
}	
