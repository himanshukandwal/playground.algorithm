package dev.research.himanshu.algorithm;

import dev.research.himanshu.algorithm.model.ListNode;

public class OddEvenLinkedList {

	public ListNode oddEvenList(ListNode head) {
		final ListNode oddTrainHead = head;

		if (head.next == null) {
			return oddTrainHead;
		}
		head = head.next;
		final ListNode evenTrainHead = head;

		int counter = 2;

		ListNode evenTrain = evenTrainHead;
		ListNode oddTrain = oddTrainHead;

		while (head != null && head.next != null) {
			counter++;
			ListNode currentNodePointer = head.next;

			if (counter % 2 == 0) {
				evenTrain.next = currentNodePointer;
				evenTrain = evenTrain.next;
			} else {
				oddTrain.next = currentNodePointer;
				oddTrain = oddTrain.next;
			}
			head = head.next;
		}
		evenTrain.next = null;
		oddTrain.next = evenTrainHead;
		return oddTrainHead;
	}
}