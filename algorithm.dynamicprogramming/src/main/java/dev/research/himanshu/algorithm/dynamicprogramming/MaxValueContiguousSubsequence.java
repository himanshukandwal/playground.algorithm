package dev.research.himanshu.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a sequence of n real numbers A(1) ... A(n), determine a contiguous subsequence A(i) ... A(j) for which the sum of elements in the subsequence is maximized.
 * @author Himanshu Kandwal
 */
public class MaxValueContiguousSubsequence {
	
	private int[] inputSequence;
	
	public MaxValueContiguousSubsequence() {}

	public MaxValueContiguousSubsequence(int[] inputSequence) {
		this.inputSequence = inputSequence;
	}
	
	public void setInputSequence(int[] inputSequence) {
		this.inputSequence = inputSequence;
	}
	
	public int[] getInputSequence() {
		return inputSequence;
	}
	
	public int[] getMaxSequenceSubsequence() {
		if (getInputSequence() == null || getInputSequence().length == 0) {
			System.out.println(" No data present in input sequence !");
			return null;
		}
		
		int maxSum, runningSum, start, end, j;
		maxSum = runningSum = getInputSequence()[0];
		start = end = j = 0;
		
		for (int index = 1; index < getInputSequence().length; index ++) {
			int currentValue = getInputSequence() [index];
			
			if (runningSum + currentValue >= currentValue) {
				runningSum += currentValue;
			} else {
				runningSum = currentValue;
				j = index;
			}
			
			if (maxSum <= runningSum) {
				maxSum = runningSum;
				start = j;
				end = index;
			}
		}
		
		System.out.println(" Max sum : " + maxSum + " , Substring range : " + start + " , " + end);
		return Arrays.copyOfRange(getInputSequence(), start, end + 1);
	}
	
}
