package dev.research.himanshu.algorithm;

<<<<<<< HEAD
import dev.research.himanshu.algorithm.model.ListNode;

=======
<<<<<<< HEAD
import dev.research.himanshu.algorithm.model.ListNode;

=======
>>>>>>> 1231a9fffb2e7ad01fe5b3a79d9b29c403e61f1c
>>>>>>> e7d2831f59c65b043b65818a906adc172352d1b3
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
<<<<<<< HEAD

}
=======
<<<<<<< HEAD
	
=======

}

class ListNode {
	int val;
	ListNode next;

	public ListNode(int x) {
		val = x;
	}
>>>>>>> 1231a9fffb2e7ad01fe5b3a79d9b29c403e61f1c
}
>>>>>>> e7d2831f59c65b043b65818a906adc172352d1b3
