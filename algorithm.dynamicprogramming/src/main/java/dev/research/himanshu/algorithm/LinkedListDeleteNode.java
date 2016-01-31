package dev.research.himanshu.algorithm;

public class LinkedListDeleteNode {

	static LinkedListNode removeElems(int value, LinkedListNode head) {
		LinkedListNode resultinghead = null;
		System.out.println("entered");
		
		if (head == null) {
			return null;
		}
		
		LinkedListNode currentnode = head;
		LinkedListNode futurenode = head.next;
		LinkedListNode previousnode = null;

		while (futurenode != null) {
			if (currentnode.val == value) {
				if (previousnode == null) {
					currentnode = resultinghead = futurenode;
				} else {
					currentnode = previousnode.next = futurenode;
				}
			} else {
				if (previousnode == null) {
					resultinghead = previousnode = currentnode;
				} else {
					previousnode = currentnode;
				}
				currentnode = futurenode;
			}
			futurenode = futurenode.next;
		}

		LinkedListNode traversingHead = resultinghead;
		while (traversingHead != null && traversingHead.next != null) {
			System.out.println(traversingHead.val);
			traversingHead = traversingHead.next;
		}

		System.out.println("exit");
		System.out.println(resultinghead);
		return resultinghead;
	}

	public static void main(String[] args) {
		LinkedListNode linkedListNode = new LinkedListNode();
		final LinkedListNode head = linkedListNode;

		int[] arr = new int[] { 6, 2, 1, 1, 2, 3, 3 };

		for (int i = 0; i < 7; i++) {
			linkedListNode.val = arr[i];
			linkedListNode.next = new LinkedListNode();
			linkedListNode = linkedListNode.next;
		}

		removeElems(2, null);
	}
}

class LinkedListNode {
	public int val;
	public LinkedListNode next;
}