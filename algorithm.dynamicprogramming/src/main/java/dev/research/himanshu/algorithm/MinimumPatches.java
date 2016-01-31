package dev.research.himanshu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumPatches {

	public static int minPatches(int[] nums, int n) {
		if (n == Integer.MAX_VALUE) {
			return 0;
		}
		
		Integer[] completeArray = new Integer[n + 1];
		
		for (int index = 0; index < nums.length; index++) {
			if (nums [index] <= n)
				completeArray[nums[index]] = 1;	
		}
		
		// Initial Stabilization patch
		nums = fillResultingArray(completeArray, nums, n);
		Integer smallestNullPosition = null;
		
		int iterations = 0;
		while ((smallestNullPosition = containsSmallestNullAt(completeArray)) != null) {
			int [] numsCopy = Arrays.copyOf(nums, nums.length + 1);
			numsCopy [nums.length] = smallestNullPosition;
			completeArray[smallestNullPosition] = 1;
			
			nums = fillResultingArray(completeArray, numsCopy, n);
			iterations ++;
		}
		
		return iterations;
	}
	
	private static Integer containsSmallestNullAt(Integer[] completeArray) {
		Integer firstNullPosition = null;
		for (int index = 1; index < completeArray.length; index ++) {
			if (completeArray [index] == null && firstNullPosition == null) {
				firstNullPosition = index;
				break;
			}
		}
		return firstNullPosition;
	}
	
	private static int[] fillResultingArray(Integer[] completeArray, int[] nums, int n) {
		boolean recurseFurther = false;
		List<Integer> integerList = new ArrayList<Integer>();
		for (int index = 0; index < nums.length; index ++) {
			for (int innerIndex = index + 1; innerIndex < nums.length; innerIndex ++) {
				int futureValue = nums [index] + nums [innerIndex];
			
				if (futureValue <= n) {
					if (completeArray[futureValue] == null) {
						integerList.add(futureValue);
						completeArray[futureValue] = 1;
						recurseFurther = true;
					}
				}
			}
		}
		
		if (recurseFurther) {
			Integer[] integerArr = integerList.toArray(new Integer[0]);
			int[] numsCopy = Arrays.copyOf(nums, nums.length + integerArr.length);
			
			for (int index = nums.length; index < numsCopy.length; index ++) 
				numsCopy [index] = integerArr [index - nums.length];
			
			nums = fillResultingArray(completeArray, numsCopy, n);
		}
		
		return nums;
	}

}