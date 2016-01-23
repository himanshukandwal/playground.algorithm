package dev.research.himanshu.algorithm;

import dev.research.himanshu.algorithm.model.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * @author Heman
 *
 */
public class ReverseNodesKGroup {

	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode start = head;
		int length = 0;
		
		if (head == null) {
			return head;
		}
		
		while (head != null) {
			length ++;
			head = head.next;   
		}
		
		head = start;
		int loopcounter = 0;
		ListNode lastTail = null;
		
		while (loopcounter + k <= length) {
			ListNode[] reversedListNode = reversal(head, head.next, loopcounter + 1, k);
			ListNode futurusticHead = reversedListNode[1];
			ListNode currentHead = reversedListNode[0];
			
			
			head = head.next = futurusticHead;
			
			if (loopcounter == 0) {
				start = currentHead;
				lastTail = getTail(currentHead, k);
			} else {
				lastTail.next = reversedListNode[0];
				lastTail = getTail(currentHead, k);
			}
			
			loopcounter += k;
		}

		return start;
	}
	
	private ListNode getTail(ListNode begin, int length) {
		ListNode trail = begin;
		int loopcounter = 1;
		
		while (trail != null && loopcounter < length) {
			trail = trail.next;
			loopcounter ++;
		}
		
		return trail;
	}

	private ListNode[] reversal (ListNode currentListNode, ListNode futureListNode, int counter, int base) {
		ListNode[] headingListArr = new ListNode[2];
		
		if (counter % base != 0) {
			if (futureListNode != null) {
				headingListArr = reversal (futureListNode, futureListNode.next, counter + 1, base);
				futureListNode.next = currentListNode;
				currentListNode.next = null;
			} else {
				// hoping this will never happen, condition avoided by the while loop
			}
		} else {
			headingListArr[0] = currentListNode;
			headingListArr[1] = futureListNode;
		}
		
		return headingListArr;
	}

}
