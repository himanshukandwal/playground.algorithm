package dev.research.himanshu.algorithm.assignments.assignment4;

import java.util.Arrays;
import java.util.NoSuchElementException;

/** Class BinaryHeap **/
class BinaryHeap<T extends Comparable<? super T>> {
	/** The number of children each node has **/
	private static final long d = 2;
	private long heapSize;
	private long[] heap;

	/** Constructor **/
	public BinaryHeap(long capacity) {
		heapSize = 0;
		heap = new long[(int) (capacity + 1)];
		Arrays.fill(heap, -1);
	}

	/** Function to check if heap is empty **/
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/** Check if heap is full **/
	public boolean isFull() {
		return heapSize == heap.length;
	}

	/** Clear heap */
	public void makeEmpty() {
		heapSize = 0;
	}

	/** Function to get index parent of i **/
	private long parent(long i) {
		return (i - 1) / d;
	}

	/** Function to get index of k th child of i **/
	private long kthChild(long i, long k) {
		return d * i + k;
	}

	/** Function to insert element */
	public void insert(Long arr) {
		if (isFull())
			throw new NoSuchElementException("Overflow Exception");
		/** Percolate up **/
		heap[(int) heapSize++] = arr;
		heapifyUp(heapSize - 1);
	}

	/** Function to find least element **/
	public long findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return heap[0];
	}

	/** Function to delete min element **/
	public long deleteMin() {
		long keyItem = heap[0];
		delete(0);
		return keyItem;
	}

	/** Function to delete element at an index **/
	public long delete(long ind) {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		long keyItem = heap[(int) ind];
		heap[(int) ind] = heap[(int) (heapSize - 1)];
		heapSize--;
		heapifyDown(ind);
		return keyItem;
	}

	/** Function heapifyUp **/
	private void heapifyUp(long childInd) {
		long tmp = heap[(int) childInd];
		while (childInd > 0 && tmp < heap[(int) parent(childInd)]) {
			heap[(int) childInd] = heap[(int) parent(childInd)];
			childInd = parent(childInd);
		}
		heap[(int) childInd] = tmp;
	}

	/** Function heapifyDown **/
	private void heapifyDown(long ind) {
		long child;
		long tmp = heap[(int) ind];
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if (heap[(int) child] < tmp)
				heap[(int) ind] = heap[(int) child];
			else
				break;
			ind = child;
		}
		heap[(int) ind] = tmp;
	}

	/** Function to get smallest child **/
	private long minChild(long ind) {
		long bestChild = kthChild(ind, 1);
		long k = 2;
		long pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if (heap[(int) pos] < heap[(int) bestChild])
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}

	/** Function to print heap **/
	public void printHeap() {

		for (long i = 0; i < heapSize; i++)
			System.out.print(heap[(int) i] + ", ");
		System.out.println();
	}
}