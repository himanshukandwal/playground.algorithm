package dev.research.himanshu.algorithm.assignments.assignment1;

import java.lang.reflect.Array;

public class MergeSortAlgorithmOnLinkedList {

	public static <T extends Comparable<? super T>> void mergeSort(SinglyLinkedList<T> singlyLinkedList) {
		// first node element
		SinglyLinkedList<T>.Entry<T> start = singlyLinkedList.header.next;

		if (start == null || start.next == null)
			return;

		mergeSort(singlyLinkedList.header, start, singlyLinkedList.tail);
	}

	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> SinglyLinkedList<T>.Entry<T>[] mergeSort(SinglyLinkedList<T>.Entry<T> parent,
			SinglyLinkedList<T>.Entry<T> beginEntryPoint, SinglyLinkedList<T>.Entry<T> endEntryPoint) {

		SinglyLinkedList<T>.Entry<T>[] resultEntries = null;
		SinglyLinkedList<T>.Entry<T> midEntryPoint = getMidwayEntry(beginEntryPoint, endEntryPoint);

		if (beginEntryPoint != endEntryPoint) {
			SinglyLinkedList<T>.Entry<T>[] resultEntriesLeft = mergeSort(parent, beginEntryPoint, midEntryPoint);
			SinglyLinkedList<T>.Entry<T>[] resultEntriesRight = mergeSort(resultEntriesLeft[1], resultEntriesLeft[1].next, endEntryPoint);
			resultEntries = merge(parent, resultEntriesLeft[0], resultEntriesLeft[1], resultEntriesRight[1]);
		} else {
			resultEntries = (SinglyLinkedList<T>.Entry<T>[]) Array.newInstance(SinglyLinkedList.Entry.class, 2);
			resultEntries[0] = resultEntries[1] = beginEntryPoint;
		}
		
		return resultEntries;
	}

	private static <T extends Comparable<? super T>> SinglyLinkedList<T>.Entry<T>[] merge(SinglyLinkedList<T>.Entry<T> parent, 
			SinglyLinkedList<T>.Entry<T> beginEntryPoint, SinglyLinkedList<T>.Entry<T> midEntryPoint, 
			SinglyLinkedList<T>.Entry<T> endEntryPoint) {

		SinglyLinkedList<T>.Entry<T>[] resultEntries = (SinglyLinkedList<T>.Entry<T>[]) Array.newInstance(SinglyLinkedList.Entry.class, 2);
		
		final SinglyLinkedList<T>.Entry<T> sentinalEntryPoint = endEntryPoint.next;
		SinglyLinkedList<T>.Entry<T> leftParentEntryPoint = parent;
		SinglyLinkedList<T>.Entry<T> rightParentEntryPoint = midEntryPoint;

		SinglyLinkedList<T>.Entry<T> leftTraversingEntryPoint = beginEntryPoint;
		SinglyLinkedList<T>.Entry<T> rightTraversingEntryPoint = midEntryPoint.next;

		while (leftTraversingEntryPoint != rightTraversingEntryPoint && rightTraversingEntryPoint != sentinalEntryPoint) {
			// swapping the linking of the elements.
			if (leftTraversingEntryPoint.element.compareTo(rightTraversingEntryPoint.element) >= 0) {
				leftParentEntryPoint = leftParentEntryPoint.next = rightTraversingEntryPoint;
				rightParentEntryPoint.next = rightTraversingEntryPoint.next;
				rightTraversingEntryPoint.next = leftTraversingEntryPoint;
				rightTraversingEntryPoint = rightParentEntryPoint.next;
			} else {
				leftParentEntryPoint = leftTraversingEntryPoint;
				leftTraversingEntryPoint = leftTraversingEntryPoint.next;
			}
		}
		// returning head and tail of the new linkedList
		resultEntries[0] = parent.next;
		resultEntries[1] = getPreviousEntry(parent.next, sentinalEntryPoint);
		return resultEntries;
	}

	public static <T extends Comparable<? super T>> SinglyLinkedList<T>.Entry<T> getMidwayEntry(
			final SinglyLinkedList<T>.Entry<T> beginEntryPoint, final SinglyLinkedList<T>.Entry<T> endEntryPoint) {

		if (beginEntryPoint == null || beginEntryPoint.next == null) {
			return beginEntryPoint;
		}

		int loopcounter = 1;
		SinglyLinkedList<T>.Entry<T> traversingEntryPoint = beginEntryPoint;
		SinglyLinkedList<T>.Entry<T> binaryTraversalEntryPoint = null;

		while (traversingEntryPoint != endEntryPoint.next) {
			traversingEntryPoint = traversingEntryPoint.next;
			loopcounter++;

			if (loopcounter % 2 == 0) {
				if (loopcounter == 2) {
					binaryTraversalEntryPoint = beginEntryPoint;
				} else {
					binaryTraversalEntryPoint = binaryTraversalEntryPoint.next;
				}
			}
		}
		return binaryTraversalEntryPoint;
	}

	public static <T extends Comparable<? super T>> SinglyLinkedList<T>.Entry<T> getPreviousEntry (
			final SinglyLinkedList<T>.Entry<T> beginEntryPoint, final SinglyLinkedList<T>.Entry<T> sentinalEntryPoint) {
		
		SinglyLinkedList<T>.Entry<T> traversingEntryPoint = beginEntryPoint;
		
		if (beginEntryPoint == null || beginEntryPoint.next == null)
			return beginEntryPoint;
		
		while (traversingEntryPoint.next != sentinalEntryPoint)
			traversingEntryPoint = traversingEntryPoint.next;
			
		return traversingEntryPoint;
	}
}