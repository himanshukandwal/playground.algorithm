package dev.research.himanshu.algorithm.assignments.assignment4;

<<<<<<< HEAD
=======
import java.util.Arrays;
import java.util.Collections;
>>>>>>> d92b3a96edab6f039ab1f9d534b223254f26b0b0
import java.util.Comparator;
import java.util.Random;

public class QuickSortingAlgorithmImpl {
	
	private static Random random = new Random();
	
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	
	/**
	 * Single-Pivot Quick Sort 
	 * 
	 * @param input
	 * @param comparator
	 * @param p
	 * @param r
	 */
	public static <T extends Comparable<? super T>> void quickSortTraditional (T[] input, Comparator<T> comparator, int p, int r) {
		if (p < r) {
			int q = partitionTraditional (input, comparator, p, r);
			quickSortTraditional (input, comparator, p, q - 1);
			quickSortTraditional (input, comparator, q + 1, r);
		}
	}
	
	private static <T extends Comparable<? super T>> int partitionTraditional (T[] input, Comparator<T> comparator, int p, int r) {
		T swappingIntermediate = null;
		
		/* select random index and exchange it with the input[r] */
		int randomIndex = p + random.nextInt(r - p + 1);
		swappingIntermediate = input [randomIndex];
		input [randomIndex] = input [r];
		input [r] = swappingIntermediate;
		
		int rightPivotCursor = p - 1;
		
		for (int index = p; index < r; index ++) {
			if (comparator.compare(input [index], input [r]) < 0) {
				rightPivotCursor ++;
			
				swappingIntermediate = input [index];
				input [index] = input [rightPivotCursor];
				input [rightPivotCursor] = swappingIntermediate;
			}
		}
		
		rightPivotCursor ++;
		
		if (rightPivotCursor != r) {
			swappingIntermediate = input [rightPivotCursor];
			input [rightPivotCursor] = input [r];
			input [r] = swappingIntermediate;
		}
		
		return rightPivotCursor;
	}
	
	
	/**
	 * Dual-Pivot Quick Sort 
	 * 
	 * @param input
	 * @param comparator
	 * @param p
	 * @param r
	 */
	public static <T extends Comparable<? super T>> void quickSortDualPivot (T[] input, Comparator<T> comparator, int p, int r) {
		if (p < r) {
			partitionDualPivot (input, comparator, p, r);
		}
	}
	
	private static <T extends Comparable<? super T>> void partitionDualPivot (T[] input, Comparator<T> comparator, int p, int r) {
		T swappingIntermediate = null;
		
		/* select random index and exchange it with the input[r] */
		int randomIndex = p + random.nextInt(r - p + 1);
		swappingIntermediate = input [randomIndex];
		input [randomIndex] = input [r];
		input [r] = swappingIntermediate;
		
		/* select random index and exchange it with the input[p] */
		randomIndex = p + random.nextInt(r - p + 1);
		swappingIntermediate = input [randomIndex];
		input [randomIndex] = input [p];
		input [p] = swappingIntermediate;
		
		if (comparator.compare(input [p], input [r]) > 0) {
			swappingIntermediate = input [r];
			input [r] = input [p];
			input [p] = swappingIntermediate;
		}

		Integer rightPivotCursor = p;
		Integer leftPivotCursor = p;
		
		for (int index = p + 1; index < r; index ++) {
			if (comparator.compare(input[index], input[p]) < 0) {
				
				// case : when the input [index] element is smaller than both the pivot values
				if (comparator.compare(input[index], input[r]) < 0) {
					if (rightPivotCursor == leftPivotCursor) {
						rightPivotCursor++;
						leftPivotCursor ++;
						
						if (rightPivotCursor > index) {
							swappingIntermediate = input [index];
							input [index] = input [leftPivotCursor];
							input [leftPivotCursor] = swappingIntermediate;
						}
					} else {
						
						// cyclic swapping of elements
						Integer greaterPivotCursor = (rightPivotCursor < leftPivotCursor ?  leftPivotCursor : rightPivotCursor);
						Integer smallerPivotCursor = (rightPivotCursor > leftPivotCursor ?  leftPivotCursor : rightPivotCursor);
						
						swappingIntermediate = input [index];
						input [index] = input [greaterPivotCursor + 1];
						input [greaterPivotCursor + 1] = swappingIntermediate;
						
						swappingIntermediate = input [greaterPivotCursor + 1];
						input [greaterPivotCursor + 1] = input [smallerPivotCursor + 1];
						input [smallerPivotCursor + 1] = swappingIntermediate;
						
						rightPivotCursor ++;
						leftPivotCursor ++;
					}
					
				// case : when the input [index] element is smaller than left pivot but, greater than right pivot	
				} else {
					leftPivotCursor ++;
				
					if (leftPivotCursor > index) {
						swappingIntermediate = input[index];
						input[index] = input[leftPivotCursor];
						input[leftPivotCursor] = swappingIntermediate;
					}
				}
			} else {
				// case : when the input [index] element is smaller than right pivot but, greater than left pivot
				if (comparator.compare(input[index], input[r]) < 0) {
					rightPivotCursor ++;
					
					if (rightPivotCursor > index) {
						swappingIntermediate = input [index];
						input [index] = input [rightPivotCursor];
						input [rightPivotCursor] = swappingIntermediate;
					}
				}
			}
		}
		
		rightPivotCursor ++;
		
		// Placing the right pivot to its correct position 
		if (rightPivotCursor != p) {
			swappingIntermediate = input [rightPivotCursor];
			input [rightPivotCursor] = input [r];
			input [r] = swappingIntermediate;
		}

		// Placing the left pivot to its correct position
		if (leftPivotCursor != p) {
			swappingIntermediate = input [leftPivotCursor];
			input [leftPivotCursor] = input [p];
			input [p] = swappingIntermediate;
		}
		
		quickSortDualPivot(input, comparator, p, leftPivotCursor - 1);
		quickSortDualPivot(input, comparator, leftPivotCursor + 1, rightPivotCursor - 1);
		quickSortDualPivot(input, comparator, rightPivotCursor + 1, r);
		
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
		
<<<<<<< HEAD
		testsForInt(1000000, true);
		testsForInt(1000000, false);
		
		testsForInt(5000000, true);
		testsForInt(5000000, false);
		
		testsForInt(10000000, true);
		testsForInt(10000000, false);
=======
//		testsForInt(1000000, true);
//		testsForInt(1000000, false);
//		
//		testsForInt(5000000, true);
//		testsForInt(5000000, false);
//		
//		testsForInt(10000000, true);
//		testsForInt(10000000, false);
//		
//		testsForInt(50000000, true);
//		testsForInt(50000000, false);
		
>>>>>>> d92b3a96edab6f039ab1f9d534b223254f26b0b0
		
		testsForInt(1048576, false);
		
		testsForInt(4194304, false);
		
		testsForInt(16777216, false);
		
		testsForInt(67108864, false);
		
<<<<<<< HEAD
		testsForInt(50000000, true);
		testsForInt(50000000, false);
		
=======
>>>>>>> d92b3a96edab6f039ab1f9d534b223254f26b0b0
		System.out.println("----------------------------------------------------");

	}
	

	private static void testsForInt(int range, boolean isSinglePivot) {
		Integer[] intArr = new Integer[range];
		
		for (int index = range; index > 0; index--)
			intArr[range - index] = index;
		
		System.out.println("\n" + (isSinglePivot ? "Single pivot (traditional) " : "Dual-pivot ") + "quick sorting for " + range + " <Integer> elements in random order !");
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		
		timer();
		if (isSinglePivot) {
			quickSortTraditional(intArr, comparator, 0, intArr.length - 1);
		} else {
			quickSortDualPivot(intArr, comparator, 0, intArr.length - 1);
		}
		timer();
		
		System.out.println("sorted for " + range + " elements !");
	}

}
