package dev.research.himanshu.algorithm.assignments.lp2;

// Ver 1.0:  Wec, Feb 3.  Initial description.

public interface PQ<T> {
    public void insert(T x);
    public T deleteMin();
    public T min();

    public void add(T x);
    public T remove();
    public T peek();
}
