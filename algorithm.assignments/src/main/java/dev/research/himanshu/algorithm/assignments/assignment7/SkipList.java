package dev.research.himanshu.algorithm.assignments.assignment7;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * A simple class implementing skipList behavior.
 * 
 * See http://en.wikipedia.org/wiki/Skip_list
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 * @param <T>
 */
public class SkipList<T extends Comparable<? super T>> implements Iterable<T> {

	private static final int MAX_LEVEL = 5;

	private SkipListEntry<T> _root = new SkipListEntry<T>(null, 1);
	private SkipListEntry<T> head;
	private int size;

	/*
	 * a skipListEntry node class.
	 */
	public class SkipListEntry<T extends Comparable<? super T>> {

		T element;
		int level;
		SkipListEntry<T>[] next;

		SkipListEntry(T x, int lev) {
			this.element = x;
			this.level = lev;
			next = new SkipListEntry[level];
		}
		
		public SkipListEntry<T>[] getNext() {
			return next;
		}
		
		public void setNext(SkipListEntry<T>[] next) {
			this.next = next;
		}
		
		@Override
		public String toString() {
			return element.toString() + " [ " + next.length + " ] ";
		}
	}

	// Add an element x to the list. Returns true if x was a new element.
	boolean add(T x) {
		SkipListEntry<T>[] prev = find(x);

		// case : empty list
		if (prev[0] == null) {
			head = new SkipListEntry<T>(x, MAX_LEVEL);
			_root.next[0] = head;
			size ++;
		} else {
			if (prev[0].next[0] != null && prev[0].next[0].element.compareTo(x) == 0)
				prev[0].next[0].element = x;
			else {
				int lev = Randomizer.randomValue(MAX_LEVEL);
				SkipListEntry<T> entry = new SkipListEntry<T>(x, lev);

				for (int index = 0; index < lev; index ++) {
					entry.next[index] = prev[index].next[index];
					prev[index].next[index] = entry;
				}
				size ++;
			}
		}
		
		return true;
	}

	T ceiling(T x) { // Least element that is >= x, or null if no such element
		return null;
	}

	boolean contains(T x) { // Is x in the list?
		SkipListEntry<T>[] prev = find(x);
		
		return ((head != null && head.element.compareTo(x)  == 0) 
				|| (prev[0] != null && prev[0].next[0] != null && prev[0].next[0].element.compareTo(x) == 0));
	}

	T findIndex(int index) { // Return the element at a given position (index) in the list
		return null;
	}

	T first() { // Return the first element of the list
		return null;
	}

	T floor(T x) { // Greatest element that is <= x, or null if no such element
		return null;
	}

	boolean isEmpty() { // Is the list empty?
		return (size == 0);
	}

	// Returns an iterator for the list
	public Iterator<T> iterator() { 
		return new SkipListIterator<T>(_root);
	}

	T last() { // Return the last element of the list
		return null;
	}

	void rebuild() { // Rebuild this list into a perfect skip list
	}
	
	@SuppressWarnings("unchecked")
	boolean remove(T x) { // Remove x from list; returns false if x was not in list
		
		// request to remove head node
		if (head != null && head.element.compareTo(x)  == 0) {
			
			if (head.next[0] != null) {
				// case : remove element next to head
				if (head.next[0].next.length < MAX_LEVEL) {
					SkipListEntry<T>[] newNextArr = (SkipListEntry<T>[]) Array.newInstance(head.next[0].getClass(),
							MAX_LEVEL);

					for (int index = head.next[0].next.length - 1; index > 0; index--)
						newNextArr[index] = head.next[0].next[index];

					newNextArr[0] = head.next[0].next[0];
					head.next[0].setNext(newNextArr);
				}

				_root.next[0] = head.next[0];
				head = head.next[0];
			} else {
				// case : remove head itself
				head = null;
				_root.next[0] = null;
			}
			
			size --;
		} else {
			SkipListEntry<T>[] prev = find(x);
			
			SkipListEntry<T> entry = prev[0].next[0];
			
			if (entry != null && entry.element.compareTo(x) != 0)
				return false;
			else {
				for (int index = 0; index < MAX_LEVEL; index++) {
					if (prev[index].next[index] == entry)
						prev[index].next[index] = entry.next[index];
					else
						break;
				}
			}
			size --;
		}
		
		return true;
	}

	int size() { // Number of elements in the list
		return size;
	}

	SkipListEntry<T>[] find(T x) {
		SkipListEntry<T> [] prev = new SkipListEntry[MAX_LEVEL]; 
		
		if (head != null) { // case of empty list			
			SkipListEntry<T> p = head; // get the head pointer
			
			for (int level = MAX_LEVEL - 1; level >= 0; level--) {
				while (p.next[level] != null && p.next[level].element.compareTo(x) < 0)
					p = p.next[level];
				prev[level] = p;
			}
		}
		
		return prev;
	}

	public class SkipListIterator<T extends Comparable<? super T>> implements Iterator<T> {

		private SkipList<T>.SkipListEntry<T> iterator;
		
		public SkipListIterator(SkipList<T>.SkipListEntry<T> _root) {
			iterator = _root;
		}
		
		@Override
		public boolean hasNext() {
			return (iterator.next[0] != null);
		}

		@Override
		public T next() {
			iterator = iterator.next[0];
			return iterator.element;
		}
		
	}
}