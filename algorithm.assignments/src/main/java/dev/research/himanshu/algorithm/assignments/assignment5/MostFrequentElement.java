package dev.research.himanshu.algorithm.assignments.assignment5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Given an array of integers return an integer that is most frequent in the array.
 *
 * @author G31
 *
 */
public class MostFrequentElement {
	
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
	 * method to return the most frequent element.
	 *  
	 * @param arr
	 * @return
	 */
	public static int mostFrequent(int[] arr) {
		HashMap<Integer, Integer> elementCountMap = new HashMap<>();
		
		int maxOccurences = 0;
		int element = arr[0];
		
		for (int index = 0; index < arr.length; index++) {
			// reducing to 2 operations (in worst case)
			Integer previousValue = elementCountMap.put(arr[index], 1);
			if (previousValue != null) 
				elementCountMap.put(arr[index], ++ previousValue);
			else
				previousValue  = 1;
			
			if (maxOccurences < previousValue) {
				maxOccurences = previousValue;
				element = arr[index];
			}
		}
		
		return element;
	}
	
	/**
	 * other algorithm that determines the most frequent element.
	 * 
	 * @param arr
	 * @return
	 */
	public static int otherWayToFindMostFrequent(int[] arr) {
		Arrays.sort(arr);
		
		int maxOccurence = 1;
		int maxOccuringElement = arr[0];
		
		int element = arr[0];
		int localOccurence = 1;
		
		for (int index = 1; index < arr.length; index++) {
			if (element == arr[index]) {
				localOccurence ++;
				
				if (localOccurence > maxOccurence) {
					maxOccurence = localOccurence;
					maxOccuringElement = element;
				}
			} else {
				element = arr[index];
				localOccurence = 0;
			}
		}
		
		return maxOccuringElement;
	}

	public static void main(String[] args) {
		
		testRunner(10);
		testRunner(100);
		testRunner(1000);
		testRunner(10000);
		testRunner(100000);
		testRunner(1000000);
		testRunner(10000000);
		
		System.out.println(" OVERALL PASSING RESULT : " + passed + " / " + total);
	}
	
	private static void testRunner(int range) {
		total++;
		int[] arr = buildArray(range);
		
		System.out.println(">> running test case : " + total);
		System.out.println(" 1) estimating time for mostFrequent method ");
		getTimer().start();
		int valueOne = mostFrequent(arr);
		System.out.println(getTimer().end().toString());
		
		System.out.println(" 2) estimating time for other mostFrequent method ");
		getTimer().start();
		int valueTwo = otherWayToFindMostFrequent(arr);
		System.out.println(getTimer().end().toString());	
		
		if (valueOne == valueTwo)
			passed ++;
		else 
			System.out.println("Incorrect element values [ 1 : " + valueOne + " , 2 : " + valueTwo + " ] ");
		
		System.out.println();
	}
	
	private static int[] buildArray(int range) {
		int arr[] = new int[range];
		
		Random random = new Random();
		int randomFrequency = 0;
		
		int nextRandomFrequency = 3;
		int number = random.nextInt(range);
		
		Set<Integer> processedNumbers = new HashSet<>();
		processedNumbers.add(number);
		
		for (int index = 0; index < range; index++) {
			if (randomFrequency < nextRandomFrequency) {
				arr[index] = number;
				randomFrequency ++;
			} else {
				while (processedNumbers.contains(number))
					number = random.nextInt(range);
				
				randomFrequency = 0;
				nextRandomFrequency += 1;
			}
		}
		
		return arr;
	}
	
}