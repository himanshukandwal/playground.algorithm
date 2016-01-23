package dev.research.himanshu.algorithm;

import dev.research.himanshu.algorithm.model.ListNode;

public class ReverseNodesLinkedList {

	public ListNode reverseNodesWithRecursion (ListNode head) {
		if (head.next != null) {
			return reversal (head, head.next);
		} else {
			return head;
		}
	}

	private ListNode reversal (ListNode currentListNode, ListNode futureListNode) {
		ListNode reverseHead = null;
		if (futureListNode != null) {
			reverseHead = reversal (futureListNode, futureListNode.next);
			futureListNode.next = currentListNode;
			currentListNode.next = null;
		} else {
			reverseHead = currentListNode;
		}
		return reverseHead;
	}
	
	public ListNode reverseNodesWithoutRecursion (ListNode head) {
		if (head.next != null) {
			ListNode trail = head;
			
			ListNode lastProcessedNode = null;
			ListNode futuristicListHead = null;
			
			while (trail.next != null) {
				lastProcessedNode = trail.next;
				futuristicListHead = lastProcessedNode.next;
				trail.next.next = trail;
				trail = futuristicListHead;
			}
			trail.next = lastProcessedNode;
			return trail;
		} else {
			return head;
		}
	}

}
