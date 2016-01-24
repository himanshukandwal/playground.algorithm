package dev.research.himanshu.algorithm.assignments.assignment1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListAdditionWithBase {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static void add(List<Integer> x, List<Integer> y, List<Integer> z, int b) {
		operate(x, y, z, b, true);
	}
	
	public static void operate(List<Integer> x, List<Integer> y, List<Integer> z, int b, boolean isAddition) {
		
		Iterator<Integer> xListIterator = x.iterator();
		Iterator<Integer> yListIterator = y.iterator();

		Integer xListElement = (xListIterator.hasNext() ? xListIterator.next() : null);
		Integer yListElement = (yListIterator.hasNext() ? yListIterator.next() : null);

		boolean hasReachedxListSentinal = (xListElement == null ? true : false);
		boolean hasReachedyListSentinal = (yListElement == null ? true : false);

		while (!hasReachedxListSentinal || !hasReachedyListSentinal) {
			if (!hasReachedxListSentinal && !hasReachedyListSentinal) {
				z.add(computeBaseResult((isAddition ? (xListElement + yListElement) : (xListElement - yListElement)), b));

				hasReachedxListSentinal = ((xListElement = nextIt(xListIterator)) == null ? true : false);
				hasReachedyListSentinal = ((yListElement = nextIt(yListIterator)) == null ? true : false);
			} else if (hasReachedyListSentinal && !hasReachedxListSentinal) {
				z.add(computeBaseResult(xListElement, b));

				hasReachedxListSentinal = ((xListElement = nextIt(xListIterator)) == null ? true : false);
			} else if (hasReachedxListSentinal && !hasReachedyListSentinal) {
				// reverse the sign in case of subtraction.
				z.add(computeBaseResult(yListElement, b) * (isAddition ? 1 : -1));

				hasReachedyListSentinal = ((yListElement = nextIt(yListIterator)) == null ? true : false);
			}
		}
	}

	public static void subtract(List<Integer> x, List<Integer> y, List<Integer> z, int b) {
		operate(x, y, z, b, false);
	}

	private static Integer nextIt(Iterator<Integer> iterator) {
		return (iterator.hasNext() ? iterator.next() : null);
	}

	private static int computeBaseResult(int summationResult, int base) {
		Integer result = null;
		int leastSignificantDigit = 0;

		while ((leastSignificantDigit = summationResult % base) > 0 || (summationResult / base > 0)) {
			if (null == result)
				result = ((leastSignificantDigit != 0) ? leastSignificantDigit : null);
			else
				result = (result * base) + leastSignificantDigit;

			summationResult /= base;
		}
		return (result == null ? 0 : result);
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
		testAdd(1000000, 50000, 10);
		testAdd(5000000, 50000, 10);
		testAdd(10000000, 50000, 10);
		
		testSubtract(1000000, 50000, 10);
		testSubtract(5000000, 50000, 10);
		testSubtract(10000000, 50000, 10);
	}
	
	public static void testAdd(int range, int offset, int base) {
		List<Integer> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();
		List<Integer> zList = new ArrayList<>();
		
		for (int index = 0; index < range; index++)
			xList.add(index);
		
		for (int index = offset; index < range + offset; index++)
			yList.add(index);
		
		System.out.println("\n ------------------ ");
		System.out.println("Checking addition on the list of elements of size - " + range);
		timer();
		add(xList, yList, zList, base);
		timer();
		System.out.println("output list size : " + zList.size());
		System.out.println(" ------------------ ");
	}
	
	public static void testSubtract(int range, int offset, int base) {
		List<Integer> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();
		List<Integer> zList = new ArrayList<>();
		
		for (int index = offset; index < range + offset; index++)
			xList.add(index);

		for (int index = 0; index < range; index++)
			yList.add(index);
		
		System.out.println("\n ------------------ ");
		System.out.println("Checking subtraction on the list of elements of size - " + range);
		timer();
		subtract(xList, yList, zList, base);
		timer();
		System.out.println("output list size : " + zList.size());
		System.out.println(" ------------------ ");
	}

}
