package dev.research.himanshu.algorithm.assignments.assignment5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Given an array of unsorted objects of some class, move the distinct elements
 * of the array to the front.
 *
 * @author G31
 *
 */
public class RemovingDuplicates {
	
	private static Timer timer;
	private static int total = 0;
	private static int passed = 0;
	
	/**
	 * timer function.
	 * 
	 * @return
	 */
	private static Timer getTimer() {
		if (timer == null)
			timer = new Timer();

		return timer;
	}
	
	/**
	 * function that returns the number of distinct element in an array.
	 * 
	 * @param arr
	 * @return
	 */
	public static <T> int findDistinct(T[] arr) {
		Map<Integer, Integer> hashcodeIndexMap = new HashMap<>();
		T swapIntermediate = null;

		int swapIndexRangeStart, swapIndexRangeEnd;
		swapIndexRangeStart = swapIndexRangeEnd = -1;

		for (int arrayIndex = 0; arrayIndex < arr.length; arrayIndex++) {
			if (hashcodeIndexMap.containsKey(arr[arrayIndex].hashCode())) {
				if (swapIndexRangeStart == -1)
					swapIndexRangeEnd = swapIndexRangeStart = arrayIndex;
				else
					swapIndexRangeEnd++;
			} else {
				if (swapIndexRangeStart > 0 && swapIndexRangeEnd >= swapIndexRangeStart) {
					swapIntermediate = arr[arrayIndex];
					arr[arrayIndex] = arr[swapIndexRangeStart];
					arr[swapIndexRangeStart] = swapIntermediate;

					hashcodeIndexMap.put(swapIntermediate.hashCode(), swapIndexRangeStart);
					swapIndexRangeStart++;
					swapIndexRangeEnd++;
				} else {
					hashcodeIndexMap.put(arr[arrayIndex].hashCode(), arrayIndex);
				}
			}
		}

		return (swapIndexRangeStart == -1 ? arr.length : swapIndexRangeStart);
	}

	public static void main(String[] args) {
		
		testRunner(10, 7);
		testRunner(1000, 100);
		testRunner(1000, 10);
		testRunner(1000, 0);
		testRunner(1000, 16);
		testRunner(1000, 570);
		testRunner(1000, 970);
		testRunner(1000, 450);
		testRunner(10000, 1000);
		testRunner(100000, 5000);
		testRunner(1000000, 1000);

		System.out.println(" OVERALL PASSING RESULT : " + passed + " / " + total);
	}
	
	/**
	 * test 
	 * 
	 * @param range
	 * @param itemsDuplicate
	 */
	private static void testRunner(int range, int itemsDuplicate) {
		total ++;
		if (testRunnerInternal (range, itemsDuplicate))
			passed ++;
	}

	/**
	 * test runner internal logic.
	 * 
	 * @param range
	 * @param itemsDuplicate
	 * @return
	 */
	public static boolean testRunnerInternal(int range, int itemsDuplicate) {
		boolean isEqual = true;

		int computedValue = buildArrayAndEvaluate(range, itemsDuplicate);
		int biggerComparisonValue = ((range - itemsDuplicate) > itemsDuplicate ? (range - itemsDuplicate)
				: itemsDuplicate);

		if (biggerComparisonValue == computedValue) {
			System.out.println("passed test for range : " + range + " with duplicate count : " + itemsDuplicate);
		} else {
			isEqual = false;
			System.out.println("failed test for range : " + range + " with duplicate count : " + itemsDuplicate
					+ " , found : " + computedValue);
		}

		System.out.println();

		return isEqual;
	}

	/**
	 * function that creates the input for distinct feature evaluation.
	 *  
	 * @param range
	 * @param itemsDuplicate
	 * @return
	 */
	protected static int buildArrayAndEvaluate(int range, int itemsDuplicate) {
		// initialize array
		ArrayItem[] arr = new ArrayItem[range];

		// build array.
		int index = 0;
		for (index = 0; index < range - itemsDuplicate; index++)
			arr[index] = new ArrayItem(index, " This is array Item with rank : " + index);

		for (; index < range; index++)
			arr[index] = new ArrayItem(index - range + itemsDuplicate,
					" This is array Item with rank : " + (index - range + itemsDuplicate));

		randomize(arr);

		// compute and evaluate
		getTimer().start();
		int distinct = findDistinct(arr);
		System.out.println(getTimer().end().toString());

		return distinct;
	}

	/**
	 * function that perform task of randomization.
	 * 
	 * @param array
	 */
	private static void randomize(ArrayItem[] array) {
		int index = -1;
		ArrayItem temp = null;

		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

}
