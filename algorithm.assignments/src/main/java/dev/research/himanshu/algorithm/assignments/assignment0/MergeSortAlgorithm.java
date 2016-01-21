package dev.research.himanshu.algorithm.assignments.assignment0;

import java.util.Arrays;
import java.util.Collections;

public class MergeSortAlgorithm {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public MergeSortAlgorithm() {
	}

	public static <T extends Comparable<? super T>> void mergeSort(T[] input, int p, int r) {
		if (p < r) {
			int q = (p + r) >> 1;
			mergeSort(input, p, q);
			mergeSort(input, q + 1, r);
			merge(input, p, q, r);
		}
	}

	public static <T extends Comparable<? super T>> void merge(T[] input, int p, int q, int r) {
		T[] leftArr = Arrays.copyOfRange(input, p, q + 1);
		T[] rightArr = Arrays.copyOfRange(input, q + 1, r + 1);

		int leftIndex, rightIndex, leftArrMaxLength, rightArrMaxLength;

		leftIndex = rightIndex = 0;
		leftArrMaxLength = leftArr.length;
		rightArrMaxLength = rightArr.length;

		for (int index = p; index <= r; index++) {
			if (leftIndex < leftArrMaxLength && rightIndex < rightArrMaxLength) {
				if (leftArr[leftIndex].compareTo(rightArr[rightIndex]) < 0) {
					input[index] = leftArr[leftIndex++];
				} else {
					input[index] = rightArr[rightIndex++];
				}
			} else if (rightIndex < rightArrMaxLength) {
				input[index] = rightArr[rightIndex++];
			} else if (leftIndex < leftArrMaxLength) {
				input[index] = leftArr[leftIndex++];
			}
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void main(String[] args) {
		System.out.println("----------------------------------------------------");
		
		testsForInt(1000000, true);
		testsForInt(1000000, false);
		
		testsForInt(5000000, true);
		testsForInt(5000000, false);
		
		testsForInt(10000000, true);
		testsForInt(10000000, false);
		
		System.out.println("----------------------------------------------------");

		System.out.println("----------------------------------------------------");

		testsForFloat(1000000, true);
		testsForFloat(1000000, false);
		
		testsForFloat(1000000, true);
		testsForFloat(5000000, false);
		
		testsForFloat(1000000, true);
		testsForFloat(10000000, false);
		
		System.out.println("----------------------------------------------------");

		System.out.println("----------------------------------------------------");

		testsForDouble(1000000, true);
		testsForDouble(1000000, false);
		
		testsForDouble(5000000, true);
		testsForDouble(5000000, false);
		
		testsForDouble(5000000, true);
		testsForDouble(10000000, false);
		
		System.out.println("----------------------------------------------------");

	}

	private static void testsForInt(int range, boolean isRandom) {
		Integer[] intArr = new Integer[range];
		
		for (int index = range; index > 0; index--)
			intArr[range - index] = index;
		
		if (isRandom)
			Collections.shuffle(Arrays.asList(intArr));
		
		System.out.println("\nmerge sorting for " + range + " <Integer> elements " + (isRandom ? " in random order " : " in reverse order " ) +"!");
		timer();
		mergeSort(intArr, 0, intArr.length - 1);
		System.out.println("sorted for " + range + " elements !");
		timer();
	}
	
	private static void testsForFloat(int range, boolean isRandom) {
		Float[] floatArr = new Float[range];
		
		for (int index = range; index > 0; index--)
			floatArr[range - index] = (float) index;
		
		if (isRandom)
			Collections.shuffle(Arrays.asList(floatArr));
		
		System.out.println("\nmerge sorting for " + range + " <Float> elements " + (isRandom ? " in random order " : " in reverse order " ) +"!");
		timer();
		mergeSort(floatArr, 0, floatArr.length - 1);
		System.out.println("sorted for " + range + " elements !");
		timer();
	}

	private static void testsForDouble(int range, boolean isRandom) {
		Double[] doubleArr = new Double[range];
		
		for (int index = range; index > 0; index--)
			doubleArr[range - index] = (double) index;
		
		if (isRandom)
			Collections.shuffle(Arrays.asList(doubleArr));
		
		System.out.println("\nmerge sorting for " + range + " <Double> elements " + (isRandom ? " in random order " : " in reverse order " ) +"!");
		timer();
		mergeSort(doubleArr, 0, doubleArr.length - 1);
		System.out.println("sorted for " + range + " elements !");
		timer();
	}
}
