package dev.research.himanshu.algorithm.assignments.assignment3;

import java.util.Comparator;
import java.util.Random;

import junit.framework.TestCase;

public class IndexedHeapTest extends TestCase {

	private final Comparator<Vertex> vertComparator = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex o1, Vertex o2) {
			return (o1.name > o2.name) ? 1 : ((o1.name == o2.name) ? 0 : -1);
		}
	};
	
	private final  Comparator<Vertex> vertRevComparator = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex o1, Vertex o2) {
			return (o1.name > o2.name) ? -1 : ((o1.name == o2.name) ? 0 : 1);
		}
	};
	
	public void testInsertion() throws Exception {
		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(100), vertComparator), vertComparator));
		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(100), vertRevComparator), vertRevComparator));
		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(1000), vertComparator), vertComparator));
		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(1000), vertRevComparator), vertRevComparator));
		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(10000), vertComparator), vertComparator));
		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(10000), vertRevComparator), vertRevComparator));
//		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(100000), vertComparator), vertComparator));
//		assertTrue(checkArray(new IndexedHeap<Vertex>(createArray(100000), vertRevComparator), vertRevComparator));
	}
	
	public void testAddition() throws Exception {
		assertTrue(checkArray(createIndexedHeap(100, vertComparator), vertComparator));
		assertTrue(checkArray(createIndexedHeap(100, vertRevComparator), vertRevComparator));
		assertTrue(checkArray(createIndexedHeap(1000, vertComparator), vertComparator));
		assertTrue(checkArray(createIndexedHeap(1000, vertRevComparator), vertRevComparator));
		assertTrue(checkArray(createIndexedHeap(10000, vertComparator), vertComparator));
		assertTrue(checkArray(createIndexedHeap(10000, vertRevComparator), vertRevComparator));
//		assertTrue(checkArray(createIndexedHeap(100000, vertComparator), vertComparator));
//		assertTrue(checkArray(createIndexedHeap(100000, vertRevComparator), vertRevComparator));
	}
	
	public void testHeapSort() throws Exception {
		Vertex[] intArr = createArray(100);
		IndexedHeap.heapSort(intArr, vertComparator);
		assertTrue(checkVanillaArray(intArr, vertComparator));
		
		intArr = createArray(100);
		IndexedHeap.heapSort(intArr, vertRevComparator);
		assertTrue(checkVanillaArray(intArr, vertRevComparator));
		
		intArr = createArray(1000);
		IndexedHeap.heapSort(intArr, vertComparator);
		assertTrue(checkVanillaArray(intArr, vertComparator));
		
		intArr = createArray(1000);
		IndexedHeap.heapSort(intArr, vertRevComparator);
		assertTrue(checkVanillaArray(intArr, vertRevComparator));
		
		intArr = createArray(10000);
		IndexedHeap.heapSort(intArr, vertComparator);
		assertTrue(checkVanillaArray(intArr, vertComparator));
		
		intArr = createArray(10000);
		IndexedHeap.heapSort(intArr, vertRevComparator);
		assertTrue(checkVanillaArray(intArr, vertRevComparator));
	}

	private boolean checkVanillaArray(Vertex[] verttArr, Comparator<Vertex> comparator) {
		Vertex previousVertex = null;
		
		boolean isGood = true;
		for (Vertex vertex : verttArr) {
			if (previousVertex == null)
					previousVertex = vertex;
				else
					isGood = isGood && (comparator.compare(vertex, previousVertex) >= 0);
		}
		
		return isGood;
	}

	private boolean checkArray(IndexedHeap<Vertex> indexedHeap, Comparator<Vertex> comparator) {
		Vertex previousDeletedValue = null;
		Vertex deletedValue = null;
		
		boolean isGood = true;
		while ((deletedValue = indexedHeap.deleteMin()) != null) {
			if (previousDeletedValue == null)
				previousDeletedValue = deletedValue;
			else
				isGood = isGood && (comparator.compare(deletedValue, previousDeletedValue) >= 0);
			
			for (int index = 1; index < indexedHeap.getPq().length; index ++) {
				isGood = isGood && (indexedHeap.getPq() [index].index == index);
				
				if (!isGood)
					break;
			}
			
			if (!isGood)
				break;
		}
		return isGood;
	}

	private IndexedHeap<Vertex> createIndexedHeap(int range, Comparator<Vertex> comparator) {
		IndexedHeap<Vertex> indexedHeap = new IndexedHeap<Vertex>(range, comparator);
		
		Random random = new Random();
		for (int index = 0; index < range; index++)
			indexedHeap.add(new Vertex(random.nextInt(range)));
			
		return indexedHeap;
	}
	
	private Vertex[] createArray(int range) {
		Vertex[] vertexArr = new Vertex [range];
		
		Random random = new Random();
		for (int index = 0; index < vertexArr.length; index++)
			vertexArr[index] = new Vertex(random.nextInt(range));
		
		return vertexArr;
	}
	
}
