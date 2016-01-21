package dev.research.himanshu.algorithm.assignments.assignment1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListSetOperations {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static <T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
		if (l1.size() > 0 && l2.size() > 0) {
			Iterator<T> leftListIterator = l1.iterator();
			Iterator<T> rightListIterator = l2.iterator();

			T leftListElement = leftListIterator.next();
			T rightListElement = rightListIterator.next();
			boolean hasReachedSentinal = false;

			while (!hasReachedSentinal) {
				int comparisonValue = leftListElement.compareTo(rightListElement);

				if (comparisonValue < 0) {
					if (leftListIterator.hasNext())
						leftListElement = leftListIterator.next();
					else
						hasReachedSentinal = true;
				} else if (comparisonValue > 0) {
					if (rightListIterator.hasNext())
						rightListElement = rightListIterator.next();
					else
						hasReachedSentinal = true;
				} else {
					outList.add(rightListElement);
					if (leftListIterator.hasNext())
						leftListElement = leftListIterator.next();
					else
						hasReachedSentinal = true;

					if (rightListIterator.hasNext())
						rightListElement = rightListIterator.next();
					else
						hasReachedSentinal = true;
				}
			}
		}
	}

	public static <T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> leftListIterator = l1.iterator();
		Iterator<T> rightListIterator = l2.iterator();

		T leftListElement = leftListIterator.next();
		T rightListElement = rightListIterator.next();
		boolean hasReachedLeftSentinal = false;
		boolean hasReachedRightSentinal = false;

		while (!hasReachedLeftSentinal || !hasReachedRightSentinal) {
			int comparisionValue = leftListElement.compareTo(rightListElement);

			if (comparisionValue < 0 && !hasReachedLeftSentinal) {
				outList.add(leftListElement);

				if (leftListIterator.hasNext())
					leftListElement = leftListIterator.next();
				else
					hasReachedLeftSentinal = true;
			} else if (comparisionValue > 0 && !hasReachedRightSentinal) {
				outList.add(rightListElement);

				if (rightListIterator.hasNext())
					rightListElement = rightListIterator.next();
				else
					hasReachedRightSentinal = true;
			} else {
				outList.add(rightListElement);

				if (leftListIterator.hasNext())
					leftListElement = leftListIterator.next();
				else
					hasReachedLeftSentinal = true;

				if (rightListIterator.hasNext())
					rightListElement = rightListIterator.next();
				else
					hasReachedRightSentinal = true;
			}
		}
	}

	public static <T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
		if (l1.size() > 0) {
			if (l2.size() == 0) {
				outList.addAll(l1);
				return;
			}

			Iterator<T> leftListIterator = l1.iterator();
			Iterator<T> rightListIterator = l2.iterator();

			T leftListElement = leftListIterator.next();
			T rightListElement = rightListIterator.next();
			boolean hasReachedLeftSentinal = false;
			boolean hasReachedRightSentinal = false;

			while (!hasReachedLeftSentinal) {
				int comparisionValue = leftListElement.compareTo(rightListElement);

				if (comparisionValue < 0 || hasReachedRightSentinal) {
					outList.add(leftListElement);

					if (leftListIterator.hasNext())
						leftListElement = leftListIterator.next();
					else
						hasReachedLeftSentinal = true;
				} else if (comparisionValue > 0 && !hasReachedRightSentinal) {
					if (rightListIterator.hasNext())
						rightListElement = rightListIterator.next();
					else
						hasReachedRightSentinal = true;
				} else {
					if (leftListIterator.hasNext())
						leftListElement = leftListIterator.next();
					else
						hasReachedLeftSentinal = true;

					if (rightListIterator.hasNext())
						rightListElement = rightListIterator.next();
					else
						hasReachedRightSentinal = true;
				}
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
		checkIntersection(1000000, 50);
		checkIntersection(5000000, 150);
		checkIntersection(10000000, 150);
		
		checkUnion(1000000, 50);
		checkUnion(5000000, 150);
		checkUnion(10000000, 150);
		
		checkDifference(1000000);
		checkDifference(5000000);
		checkDifference(10000000);
	}

	private static void checkIntersection(int range, int offset) {
		List<Integer> integerListOne = new ArrayList<Integer>(range);
		List<Integer> integerListTwo = new ArrayList<Integer>(range);

		for (int index = 0; index < range; index++)
			integerListOne.add(index);

		for (int index = range - offset; index < 2 * range + offset; index++)
			integerListTwo.add(index);

		List<Integer> outputList = new ArrayList<Integer>();

		System.out.println("\n ------------------ ");
		System.out.println("Checking intersection for elements of size - " + range);
		timer();
		intersect(integerListOne, integerListTwo, outputList);
		timer();
		System.out.println("output list size : " + outputList.size());
		System.out.println(" ------------------ ");
	}

	private static void checkUnion(int range, int offset) {
		List<Integer> integerListOne = new ArrayList<Integer>(range);
		List<Integer> integerListTwo = new ArrayList<Integer>(range);

		for (int index = 0; index < range; index++)
			integerListOne.add(index);

		for (int index = offset; index < range + offset; index++)
			integerListTwo.add(index);

		List<Integer> outputList = new ArrayList<Integer>();

		System.out.println("\n ------------------ ");
		System.out.println("Checking union for elements of size - " + range);
		timer();
		union(integerListOne, integerListTwo, outputList);
		timer();
		System.out.println("output list size : " + outputList.size());
		System.out.println(" ------------------ ");
	}
	
	private static void checkDifference(int range) {
		List<Integer> integerListOne = new ArrayList<Integer>(range);
		List<Integer> integerListTwo = new ArrayList<Integer>(range);

		for (int index = 0; index < range; index++)
			integerListOne.add(index);

		List<Integer> outputList = new ArrayList<Integer>();

		System.out.println("\n ------------------ ");
		System.out.println("Checking difference for elements of size - " + range);
		timer();
		difference(integerListOne, integerListTwo, outputList);
		timer();
		System.out.println("output list size : " + outputList.size());
		System.out.println(" ------------------ ");
	}

}
