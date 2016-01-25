package dev.research.himanshu.algorithm;

import static dev.research.himanshu.algorithm.RotateLinkedList.rotateRight;

import dev.research.himanshu.algorithm.model.ListNode;
import junit.framework.TestCase;

public class RotateLinkedListTest extends TestCase {
	
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

		for (int index = 2; index <= 10; index++) {
			ListNode nextNode = new ListNode(index);
			trainNode.next = nextNode;
			trainNode = nextNode;
		}

		trainNode = rotateRight(headNode, 4);

		while (trainNode != null) {
			System.out.print(trainNode.val + ", ");
			trainNode = trainNode.next;
		}
	}
}
