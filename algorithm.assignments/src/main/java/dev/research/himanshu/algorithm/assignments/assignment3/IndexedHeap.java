package dev.research.himanshu.algorithm.assignments.assignment3;

import java.util.Comparator;

//Ver 1.0:  Wec, Feb 3.  Initial description.

/**
 * @author G31
 */

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	
	IndexedHeap(int n, Comparator<T> comp) {
		super(n, comp);
	}

	/** restore heap order property after the priority of x has decreased */
	void decreaseKey(T x) {  
		percolateUp(x.getIndex());   
	}
	
	@Override
	public void percolateUp(int i) {
		super.percolateUp(i);
	}
	
	@Override
	public void assignIndex(T t, int index) {
		super.assignIndex(t, index);
		t.putIndex(index);
	}
	
}
