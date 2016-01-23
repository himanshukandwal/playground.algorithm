package dev.research.himanshu.algorithm;

import dev.research.himanshu.algorithm.model.ListNode;

public class ReverseNodesLinkedList {

	public ListNode reverseNodes(ListNode head) {
		if (head.next != null) {
			return reversal(head, head.next);
		} else {
			return head;
		}
	}

	public ListNode reversal(ListNode currentListNode, ListNode futureListNode) {
		ListNode reverseHead = null;
		if (futureListNode != null) {
			reverseHead = reversal(futureListNode, futureListNode.next);
			futureListNode.next = currentListNode;
			currentListNode.next = null;
		} else {
			reverseHead = currentListNode;
		}
		return reverseHead;
	}

}
