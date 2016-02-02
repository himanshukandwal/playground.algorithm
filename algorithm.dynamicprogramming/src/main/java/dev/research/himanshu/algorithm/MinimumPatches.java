package dev.research.himanshu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumPatches {

	public static int minPatches(int[] nums, int n) {
		if (n == Integer.MAX_VALUE) {
			return 0;
		}
<<<<<<< HEAD

		Integer[] completeArray = new Integer[n + 1];

		for (int index = 0; index < nums.length; index++) {
			if (nums[index] <= n)
				completeArray[nums[index]] = 1;
		}

		Arrays.sort(nums);

		// Initial Stabilization patch
		nums = fillResultingArray(completeArray, nums, null, n, true);
		Integer smallestNullPosition = null;

		int iterations = 0;
		while ((smallestNullPosition = containsSmallestNullAt(completeArray)) != null) {
			List<Integer> deltaIntegerList = new ArrayList<Integer>();
			deltaIntegerList.add(smallestNullPosition);
			
			completeArray[smallestNullPosition] = 1;

			nums = fillResultingArray(completeArray, nums, deltaIntegerList, n, false);
			iterations++;
		}

		return iterations;
	}

	private static Integer containsSmallestNullAt(Integer[] completeArray) {
		Integer firstNullPosition = null;
		for (int index = 1; index < completeArray.length; index++) {
			if (completeArray[index] == null && firstNullPosition == null) {
=======
		
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
>>>>>>> 105b8f7b64d1ca69f99027bcffe523e6a721373c
				firstNullPosition = index;
				break;
			}
		}
		return firstNullPosition;
	}
<<<<<<< HEAD

	private static int[] fillResultingArray(Integer[] completeArray, int[] nums, List<Integer> deltaIntegerList, int n,
			boolean isStablizing) {
		boolean recurseFurther = false;
		deltaIntegerList = deltaIntegerList == null ? new ArrayList<Integer>() : deltaIntegerList;

		for (int index = 0; index < (isStablizing ? nums.length : deltaIntegerList.size()); index++) {
			int futureValue = 0;

			for (int innerIndex = (isStablizing ? index + 1 : 0); innerIndex < nums.length; innerIndex++) {
				if (isStablizing)
					futureValue = nums[index] + nums[innerIndex];
				else
					futureValue = deltaIntegerList.get(index) + nums[innerIndex];

				if (futureValue <= n) {
					if (completeArray[futureValue] == null) {
						deltaIntegerList.add(futureValue);
=======
	
	private static int[] fillResultingArray(Integer[] completeArray, int[] nums, int n) {
		boolean recurseFurther = false;
		List<Integer> integerList = new ArrayList<Integer>();
		for (int index = 0; index < nums.length; index ++) {
			for (int innerIndex = index + 1; innerIndex < nums.length; innerIndex ++) {
				int futureValue = nums [index] + nums [innerIndex];
			
				if (futureValue <= n) {
					if (completeArray[futureValue] == null) {
						integerList.add(futureValue);
>>>>>>> 105b8f7b64d1ca69f99027bcffe523e6a721373c
						completeArray[futureValue] = 1;
						recurseFurther = true;
					}
				}
			}
		}
<<<<<<< HEAD

		if (recurseFurther)
			nums = fillResultingArray(completeArray, nums, deltaIntegerList, n, false);
		else {
			if (deltaIntegerList.size() != 0) {
				Integer[] integerArr = deltaIntegerList.toArray(new Integer[0]);
				int[] numsCopy = Arrays.copyOf(nums, nums.length + integerArr.length);

				for (int index = nums.length; index < numsCopy.length; index++)
					numsCopy[index] = integerArr[index - nums.length];

				Arrays.sort(numsCopy);
				nums = numsCopy;
			}
		}
		return nums;
	}
	
	public static int minPatches2(int[] nums, int n) {
		long miss = 1;
		int added = 0;
		int i = 0;
	    while (miss <= n) {
	        if (i < nums.length && nums[i] <= miss) {
	            miss += nums[i++];
	        } else {
	            miss += miss;
	            added++;
	        }
	    }
	    return added;
	}
=======
		
		if (recurseFurther) {
			Integer[] integerArr = integerList.toArray(new Integer[0]);
			int[] numsCopy = Arrays.copyOf(nums, nums.length + integerArr.length);
			
			for (int index = nums.length; index < numsCopy.length; index ++) 
				numsCopy [index] = integerArr [index - nums.length];
			
			nums = fillResultingArray(completeArray, numsCopy, n);
		}
		
		return nums;
	}
>>>>>>> 105b8f7b64d1ca69f99027bcffe523e6a721373c

}