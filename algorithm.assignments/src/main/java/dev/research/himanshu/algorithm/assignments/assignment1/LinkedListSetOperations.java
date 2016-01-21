package dev.research.himanshu.algorithm.assignments.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
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
			if (l2.size() < 0) {
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
		List<Integer> listOne = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> listTwo = Arrays.asList(0, 4, 5, 6, 7, 8, 9);

		List<Integer> outputList = new ArrayList<Integer>();
		intersect(listOne, listTwo, outputList);

		for (Integer integer : outputList) {
			System.out.println(integer);
		}
		System.out.println(" ------------------ ");

		outputList.clear();
		union(listOne, listTwo, outputList);

		for (Integer integer : outputList) {
			System.out.println(integer);
		}
		System.out.println(" ------------------ ");

		outputList.clear();
		difference(listTwo, listOne, outputList);

		for (Integer integer : outputList) {
			System.out.println(integer);
		}
		System.out.println(" ------------------ ");
	}

}
