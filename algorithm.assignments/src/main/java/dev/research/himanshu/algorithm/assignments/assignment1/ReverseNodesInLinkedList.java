package dev.research.himanshu.algorithm.assignments.assignment1;

public class ReverseNodesInLinkedList {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static <T extends Comparable<? super T>> void reverseNodesWithRecursion (SinglyLinkedList<T> singlyLinkedList) {
		if (singlyLinkedList.header.next != null && singlyLinkedList.header.next.next != null) {
			singlyLinkedList.header.next = reversal(singlyLinkedList.header.next, singlyLinkedList.header.next.next);
		}
	}

	public static <T extends Comparable<? super T>> void reverseNodesWithoutRecursion (SinglyLinkedList<T> singlyLinkedList) {
		if (singlyLinkedList.header.next != null && singlyLinkedList.header.next.next != null) {
			SinglyLinkedList<T>.Entry<T> traversingNode = singlyLinkedList.header.next;

			SinglyLinkedList<T>.Entry<T> forwardNode = null;
			SinglyLinkedList<T>.Entry<T> lastProcessedNode = null;

			while (traversingNode.next != null) {
				forwardNode = traversingNode.next;
				if (lastProcessedNode != null) {
					traversingNode.next = lastProcessedNode;
				} else {
					traversingNode.next = null;
				}
				lastProcessedNode = traversingNode;
				traversingNode = forwardNode;
			}
			traversingNode.next = lastProcessedNode;
			singlyLinkedList.header.next = traversingNode;
		}
	}

	private static <T extends Comparable<? super T>> SinglyLinkedList<T>.Entry<T> reversal (
			SinglyLinkedList<T>.Entry<T> currentListNode, SinglyLinkedList<T>.Entry<T> futureListNode) {
		
		SinglyLinkedList<T>.Entry<T> reverseHead = null;
		if (futureListNode != null) {
			reverseHead = reversal(futureListNode, futureListNode.next);
			futureListNode.next = currentListNode;
			currentListNode.next = null;
		} else {
			reverseHead = currentListNode;
		}
		return reverseHead;
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void main(String[] args) {
		createAndReverseLinkedList(5, true);
		createAndReverseLinkedList(5, false);
		createAndReverseLinkedList(15, true);
		createAndReverseLinkedList(15, false);
	}

	private static void createAndReverseLinkedList(int range, boolean withRecursion) {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

		for (int index = range; index > 0; index--)
			singlyLinkedList.add(new Integer(index));

		System.out.println(
				"\n ------------------------------------------------------------------------------------------ ");
		System.out.println("Reversing elements of a linked list "
				+ (withRecursion ? "with recursion " : "without recursion ") + "on " + range + " elements");
		System.out.println(
				" ------------------------------------------------------------------------------------------ ");

		singlyLinkedList.printList();
		timer();

		if (withRecursion)
			reverseNodesWithRecursion(singlyLinkedList);
		else
			reverseNodesWithoutRecursion(singlyLinkedList);

		timer();
		singlyLinkedList.printList();
	}
	
}
