package dev.research.himanshu.algorithm.assignments.assignment1;

/**
 * Singly linked list: for instructional purposes only
 * 
 * @author rbk
 * 
 */
public class SinglyLinkedList<T> {

	public class Entry<T> {
		T element;
		Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}
		
		@Override
		public String toString() {
			return element.toString();
		}
	}

	Entry<T> header, tail;
	int size;

	SinglyLinkedList() {
		header = new Entry<>(null, null);
		tail = null;
		size = 0;
	}

	void add(T x) {
		if (tail == null) {
			header.next = new Entry<>(x, header.next);
			tail = header.next;
		} else {
			tail.next = new Entry<>(x, null);
			tail = tail.next;
		}
		size++;
	}

	void printList() {
		Entry<T> x = header.next;
		while (x != null) {
			System.out.print(x.element + " ");
			x = x.next;
		}
		System.out.println();
	}

	void unzip() {
		if (size < 3) { // Too few elements. No change.
			return;
		}

		Entry<T> tail0 = header.next;
		Entry<T> head1 = tail0.next;
		Entry<T> tail1 = head1;
		Entry<T> c = tail1.next;
		int state = 0;

		// Invariant: tail0 is the tail of the chain of elements with even
		// index.
		// tail1 is the tail of odd index chain.
		// c is current element to be processed.
		// state indicates the state of the finite state machine
		// state = i indicates that the current element is added after taili
		// (i=0,1).
		while (c != null) {
			if (state == 0) {
				tail0.next = c;
				tail0 = c;
				c = c.next;
			} else {
				tail1.next = c;
				tail1 = c;
				c = c.next;
			}
			state = 1 - state;
		}

		tail0.next = head1;
		tail1.next = null;
	}

	public static void main(String[] args) {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
		for (int i = 1; i <= n; i++) {
			lst.add(new Integer(i));
		}

		lst.printList();
		lst.unzip();
		lst.printList();
	}
}

/*
 * Sample output: 1 2 3 4 5 6 7 8 9 10 1 3 5 7 9 2 4 6 8 10
 */