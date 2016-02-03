package dev.research.himanshu.algorithm.assignments.assignment1;

public class MultiZippingLinkedList {

	public static <T extends Comparable<? super T>> void multiUnzip(SinglyLinkedList<T>.Entry<T> beginEntryPoint, int k) {
		SinglyLinkedList<T>.Entry<T> traversalEntryNode = beginEntryPoint;
		while (k > 1) {
			traversalEntryNode = zipper (traversalEntryNode, k --);
		}
	}

	private static <T extends Comparable<? super T>> SinglyLinkedList<T>.Entry<T> zipper(
			SinglyLinkedList<T>.Entry<T> beginEntryPoint, int frequency) {
		
		if (beginEntryPoint == null || beginEntryPoint.next == null)
			return beginEntryPoint;
		
		SinglyLinkedList<T>.Entry<T> traversalEntryNode = beginEntryPoint;
		
		final SinglyLinkedList<T>.Entry<T> zippedHeaderEntryNode = beginEntryPoint;
		final SinglyLinkedList<T>.Entry<T> otherHeaderEntryNode = beginEntryPoint.next;
		
		SinglyLinkedList<T>.Entry<T> zippedTraversalEntryNode = zippedHeaderEntryNode;
		SinglyLinkedList<T>.Entry<T> otherTraversalEntryNode = otherHeaderEntryNode;
		
		int loopcounter = 0;
		while (traversalEntryNode != null) {
			loopcounter ++;
			beginEntryPoint = traversalEntryNode.next;
			traversalEntryNode.next = null;
			
			if ((loopcounter - 1)% frequency == 0)
				zippedTraversalEntryNode = zippedTraversalEntryNode.next = traversalEntryNode;
			else 
				otherTraversalEntryNode = otherTraversalEntryNode.next = traversalEntryNode;
			
			traversalEntryNode = beginEntryPoint;
		}
		
		zippedTraversalEntryNode.next = otherHeaderEntryNode;
		return otherHeaderEntryNode;
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

		for (int index = 1; index <= 10; index ++)
			singlyLinkedList.add(new Integer(index));
		
		singlyLinkedList.printList();
		multiUnzip(singlyLinkedList.header.next, 3);
		singlyLinkedList.printList();
	}

}
