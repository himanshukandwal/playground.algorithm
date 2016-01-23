package dev.research.himanshu.algorithm;

import junit.framework.TestCase;

public class OddEvenLinkedListTest extends TestCase {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	protected void setUp() throws Exception {
		super.setUp();
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

	public void testCase1() {
		final ListNode headNode = new ListNode(1);
		ListNode trainNode = headNode;

		for (int index = 2; index <= 12; index++) {
			ListNode nextNode = new ListNode(index);
			trainNode.next = nextNode;
			trainNode = nextNode;
		}
		trainNode = headNode;

		while (trainNode != null) {
			System.out.println(trainNode.val);
			trainNode = trainNode.next;
		}
		System.out.println(" -------- after sorting -------- ");
		OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
		trainNode = oddEvenLinkedList.oddEvenList(headNode);

		while (trainNode != null) {
			System.out.println(trainNode.val);
			trainNode = trainNode.next;
		}
	}
	
}
