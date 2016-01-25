package dev.research.himanshu.algorithm.assignments.assignment1;

public class MultiZippingLinkedListTest extends AbstractAssignmentTest {

	public void testZippingCase1() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1; index <= 10; index ++) 
			singlyLinkedList.add(new Integer(index));
				
		System.out.println("\n ------------------------------------------------------------------------------------------ ");
		System.out.println("Multiunzipping for frequency - 2");
		System.out.println(" ------------------------------------------------------------------------------------------ ");
		
		singlyLinkedList.printList();
		MultiZippingLinkedList.multiUnzip(singlyLinkedList.header.next, 2);	
		singlyLinkedList.printList();
	}
	
	public void testZippingCase2() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1; index <= 10; index ++) 
			singlyLinkedList.add(new Integer(index));
			
		System.out.println("\n ------------------------------------------------------------------------------------------ ");
		System.out.println("Multiunzipping for frequency - 4");
		System.out.println(" ------------------------------------------------------------------------------------------ ");
		
		singlyLinkedList.printList();
		MultiZippingLinkedList.multiUnzip(singlyLinkedList.header.next, 4);	
		singlyLinkedList.printList();
	}
	
	public void testZippingCase3() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1; index <= 10; index ++) 
			singlyLinkedList.add(new Integer(index));
				
		System.out.println("\n ------------------------------------------------------------------------------------------ ");
		System.out.println("Multiunzipping for frequency - 5");
		System.out.println(" ------------------------------------------------------------------------------------------ ");
		
		singlyLinkedList.printList();
		MultiZippingLinkedList.multiUnzip(singlyLinkedList.header.next, 5);	
		singlyLinkedList.printList();
	}
}
