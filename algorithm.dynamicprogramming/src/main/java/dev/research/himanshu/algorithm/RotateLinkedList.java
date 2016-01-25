package dev.research.himanshu.algorithm;

import dev.research.himanshu.algorithm.model.ListNode;

public class RotateLinkedList {

	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null || k <= 0)
			return head;

		ListNode traversingListNode = head;
		int length = 0;
		while (traversingListNode != null) {
			length++;
			traversingListNode = traversingListNode.next;
		}
		k = k % length;
		if (k == 0)
            return head;
		
		traversingListNode = head;
		ListNode rotatedHeadNode = null;
		while (length > k + 1) {
			traversingListNode = traversingListNode.next;
			length--;
		}
		rotatedHeadNode = traversingListNode.next;
		traversingListNode.next = null;
		traversingListNode = rotatedHeadNode;
		while (traversingListNode.next != null)
			traversingListNode = traversingListNode.next;

		traversingListNode.next = head;
		return rotatedHeadNode;
	}

}
