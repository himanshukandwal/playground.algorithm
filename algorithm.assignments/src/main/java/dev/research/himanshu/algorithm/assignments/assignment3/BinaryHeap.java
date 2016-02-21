package dev.research.himanshu.algorithm.assignments.assignment3;

import java.util.Arrays;
import java.util.Comparator;

//Ver 1.0:  Wec, Feb 3.  Initial description.

/**
 * @author G31
 */
public class BinaryHeap<T> implements PQ<T> {
	protected T[] pq;
	protected Comparator<T> c;
	private Integer maxsize;
	
	/** Build a priority queue with a given array q */
	BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		c = comp;
		maxsize = q.length;
		buildHeap();
	}

	/** Create an empty priority queue of given maximum size */
	BinaryHeap(int n, Comparator<T> comp) {
		maxsize = n;
		c = comp;
		pq = (T []) new Object [1];
		pq[0] = null;
	}

	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void add(T x) {
		if (pq.length + 1 > maxsize) {
			System.out.println("cannot add more elements. Priority queue reached max size [" + maxsize + "]");
		} else {
			pq = Arrays.copyOf(pq, pq.length + 1);
			assignIndex(x, pq.length - 1);
			assignIndex(pq[pq.length - 1], 0);
			percolateUp(pq.length - 1);
			pq[0] = null;
		}
	}

	public T remove() {
		T returnVal = null;
		if (pq.length > 1) {
			returnVal = pq [1];
			
			assignIndex(pq [pq.length - 1], 1);
			pq = Arrays.copyOf (pq, pq.length - 1);

			percolateDown (1);
		}
		return returnVal;
	}

	public T peek() {
		return (pq.length <= 1 ? null : pq[1]);
	}

	/** pq[i] may violate heap order with parent */
	public void percolateUp (int i) {
		if (c.compare (pq [i], pq [i / 2]) < 0) {
			assignIndex(pq [i / 2], i);
			assignIndex(pq [0], i / 2);
			
			percolateUp (i / 2);
		}
	}
	
	public void assignIndex(T t, int index) {
		pq [index] = t;
	}

	/** pq[i] may violate heap order with children */
	public void percolateDown(int i) {
		if (i < pq.length && (2 * i < pq.length || (2 * i + 1) < pq.length)) {
			int index = -1;
			
			// case : two children
			if ((2 * i + 1) < pq.length)
				index = (c.compare (pq [2 * i], pq [2 * i + 1]) > 0 ? 2 * i + 1 : 2 * i);
			else // case : one child
				index = 2 * i;
				
			if (c.compare (pq [i], pq [index]) > 0) {
				pq [0] = pq [i];
				pq [i] = pq [index];
				pq [index] = pq [0];
				pq [0] = null;

				percolateDown (index);
			}
		}
	}

	/** Create a heap. Precondition: none. */
	void buildHeap() {
		heapSort(pq, c);
	}

	/*
	 * sort array A [1..n]. A [0] is not used. Sorted order depends on comparator used to buid heap.
	 *    	min heap ==> descending order 
	 * 		max heap ==> ascending order
	 */
	public static <T> void heapSort(T[] A, Comparator<T> comp) {
		for (int index = A.length/2; index > 0; index --)
			heapify(A, index, comp);
	}
	
	public static <T> void heapify(T[] A, int index, Comparator<T> comp) {
		int leftIndex = 2 * index;
		int rightIndex = 2 * index + 1;
		Integer minima = null;
		
		if (leftIndex < A.length) {
			if (comp.compare(A[leftIndex], A[index]) < 0) {
				minima = leftIndex;
			} else {
				minima = index;
			}
		}
		
		if (rightIndex < A.length) {
			if (comp.compare(A[rightIndex], A [minima]) < 0) {
				minima = rightIndex;
			}
		}
		
		if (minima != null && minima != index) {
			A[0] = A[index];
			A[index] = A [minima];
			A[minima] = A[0];
			A[0] = null;
			heapify(A, minima, comp);
		}
	}
	
	public T[] getPq() {
		return pq;
	}

	public static void main(String[] args) {
		Integer[] intArr = new Integer[] { null, 10, 7, 8, 9, 100, 0, 19, 13, 3, -19, 15, 677, 385 };

		Comparator<Integer> intComparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(intArr, intComparator);
		
		System.out.println("Created heap !");
		
		for (Integer integer : intArr) {
			System.out.println(integer);
		}
		
		System.out.println(" deleting now : ");
		Integer deletedValue = null;
		while ((deletedValue = binaryHeap.deleteMin()) != null) {
			System.out.println(deletedValue);
		}
		
		intArr = new Integer[] { null, 10, 7, 8, 9, 100, 0, 19, 13, 3, -19, 15, 677, 385 };
		
		System.out.println(" -- With Sorting !");
		BinaryHeap.heapSort(intArr, intComparator);
		
		for (int index = 0; index < intArr.length; index++) {
			System.out.println(intArr [index]);
		}
	
	}
}
