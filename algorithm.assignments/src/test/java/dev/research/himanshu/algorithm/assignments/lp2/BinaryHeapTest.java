package dev.research.himanshu.algorithm.assignments.lp2;

import java.util.Comparator;
import java.util.Random;

import junit.framework.TestCase;

public class BinaryHeapTest extends TestCase {
	
	private final Comparator<Integer> intComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	};
	
	private final  Comparator<Integer> intRevComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return (o1 > o2) ? -1 : ((o1 == o2) ? 0 : 1);
		}
	};
	
	public void testInsertion() throws Exception {
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(100), intComparator), intComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(100), intRevComparator), intRevComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(1000), intComparator), intComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(1000), intRevComparator), intRevComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(10000), intComparator), intComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(10000), intRevComparator), intRevComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(100000), intComparator), intComparator));
		assertTrue(checkArray(new BinaryHeap<Integer>(createArray(100000), intRevComparator), intRevComparator));
	}
	
	public void testAddition() throws Exception {
		assertTrue(checkArray(createBinaryHeap(100, intComparator), intComparator));
		assertTrue(checkArray(createBinaryHeap(100, intRevComparator), intRevComparator));
		assertTrue(checkArray(createBinaryHeap(1000, intComparator), intComparator));
		assertTrue(checkArray(createBinaryHeap(1000, intRevComparator), intRevComparator));
		assertTrue(checkArray(createBinaryHeap(10000, intComparator), intComparator));
		assertTrue(checkArray(createBinaryHeap(10000, intRevComparator), intRevComparator));
		assertTrue(checkArray(createBinaryHeap(100000, intComparator), intComparator));
		assertTrue(checkArray(createBinaryHeap(100000, intRevComparator), intRevComparator));
	}
	
	public void testHeapSort() throws Exception {
		Integer[] intArr = createArray(100);
		BinaryHeap.heapSort(intArr, intComparator);
		assertTrue(checkVanillaArray(intArr, intComparator));
		
		intArr = createArray(100);
		BinaryHeap.heapSort(intArr, intRevComparator);
		assertTrue(checkVanillaArray(intArr, intRevComparator));
		
		intArr = createArray(1000);
		BinaryHeap.heapSort(intArr, intComparator);
		assertTrue(checkVanillaArray(intArr, intComparator));
		
		intArr = createArray(1000);
		BinaryHeap.heapSort(intArr, intRevComparator);
		assertTrue(checkVanillaArray(intArr, intRevComparator));
		
		intArr = createArray(10000);
		BinaryHeap.heapSort(intArr, intComparator);
		assertTrue(checkVanillaArray(intArr, intComparator));
		
		intArr = createArray(10000);
		BinaryHeap.heapSort(intArr, intRevComparator);
		assertTrue(checkVanillaArray(intArr, intRevComparator));
		
		intArr = createInvertedArray(10000);
		BinaryHeap.heapSort(intArr, intRevComparator);
		assertTrue(checkVanillaArray(intArr, intRevComparator));
	}

	private boolean checkVanillaArray(Integer[] intArr, Comparator<Integer> comparator) {
		Integer previousValue = null;
		
		boolean isGood = true;
		for (Integer integer : intArr) {
			if (previousValue == null)
					previousValue = integer;
				else {
					isGood = isGood && (comparator.compare(integer, previousValue) >= 0);
					previousValue = integer;
				}
		}
		
		return isGood;
	}

	private boolean checkArray(BinaryHeap<Integer> binaryHeap, Comparator<Integer> comparator) {
		Integer previousDeletedValue = null;
		Integer deletedValue = null;
		
		boolean isGood = true;
		while ((deletedValue = binaryHeap.deleteMin()) != null) {
			if (previousDeletedValue == null)
				previousDeletedValue = deletedValue;
			else {
				isGood = isGood && (comparator.compare(deletedValue, previousDeletedValue) >= 0);
				previousDeletedValue = deletedValue;
			}
		}
		return isGood;
	}

	private BinaryHeap<Integer> createBinaryHeap(int range, Comparator<Integer> comparator) {
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(range, comparator);
		
		Random random = new Random();
		for (int index = 0; index < range; index++)
			binaryHeap.add(random.nextInt(range));
			
		return binaryHeap;
	}
	
	private Integer[] createArray(int range) {
		Integer[] intArr = new Integer[range + 1];
		intArr [0] = null;
		
		Random random = new Random();
		for (int index = 1; index < range + 1; index++)
			intArr[index] = random.nextInt(range);
		
		return intArr;
	}
	
	private Integer[] createInvertedArray(int range) {
		Integer[] intArr = new Integer[range + 1];
		intArr [0] = null;

		for (int index = range; index > 0; index --)
			intArr[range - index + 1] = index;
		
		return intArr;
	}
	
}
