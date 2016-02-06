package dev.research.himanshu.algorithm.dynamicprogramming;

import junit.framework.TestCase;

public class KnapsackComputationTest extends TestCase {
	
	public void testCheckKnapsack1() throws Exception {
		int [][] data = new int[][] {
			{10, 60},
			{20, 100},
			{30, 120}
		};
		
		assertEquals(300, KnapsackComputation.recurseEvaluationDuplicatesAllowed(data, 50, 0));
		assertEquals(220, KnapsackComputation.recurseEvaluationWithoutDuplicates(data, 50, 0));
	}
	
	public void testCheckKnapsack2() throws Exception {
		int [][] data = new int[][] {
			{2, 3},
			{3, 7},
			{4, 2},
			{5, 9}
		};
		
		assertEquals(10, KnapsackComputation.recurseEvaluationWithoutDuplicates(data, 5, 0));
	}
	
	public void testCheckKnapsack3() throws Exception {
		int [][] data = new int[][] {
			{2, 3},
			{3, 4},
			{4, 5},
			{5, 6}
		};
		
		assertEquals(7, KnapsackComputation.recurseEvaluationWithoutDuplicates(data, 5, 0));
	}
}	
