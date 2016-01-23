package dev.research.himanshu.algorithm.assignments.assignment1;

public class MergeSortAlgorithmOnLinkedListTest extends AbstractAssignmentTest {

	public void testGetMidwayEntryForOdd() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1; index <= 9; index ++) 
			singlyLinkedList.add(new Integer(index));
				
		assertEquals(5, MergeSortAlgorithmOnLinkedList.getMidwayEntry(singlyLinkedList.header.next, singlyLinkedList.tail).element.intValue());
	}
	
	public void testGetMidwayEntryForEven() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1; index <= 10; index ++) 
			singlyLinkedList.add(new Integer(index));
				
		assertEquals(5, MergeSortAlgorithmOnLinkedList.getMidwayEntry(singlyLinkedList.header.next, singlyLinkedList.tail).element.intValue());
	}
	
	public void testGetMidwayEntryForEvenReversed() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 10; index > 0; index --) 
			singlyLinkedList.add(new Integer(index));
				
		assertEquals(6, MergeSortAlgorithmOnLinkedList.getMidwayEntry(singlyLinkedList.header.next, singlyLinkedList.tail).element.intValue());
	}
	
	public void testGetMidwayEntryForSingleEntry() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1; index < 2; index ++) 
			singlyLinkedList.add(new Integer(index));
				
		assertEquals(1, MergeSortAlgorithmOnLinkedList.getMidwayEntry(singlyLinkedList.header.next, singlyLinkedList.tail).element.intValue());
	}
	
	public void testGetMidwayEntryForNoEntry() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
				
		assertEquals(singlyLinkedList.header.next, MergeSortAlgorithmOnLinkedList.getMidwayEntry(singlyLinkedList.header.next, singlyLinkedList.tail));
	}
	
	public void testMergeSortedList() {
		SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
		
		for (int index = 1000000; index > 0; index --) 
			singlyLinkedList.add(new Integer(index));
		
		//singlyLinkedList.printList();
		timer();
		MergeSortAlgorithmOnLinkedList.mergeSort(singlyLinkedList);
		timer();
		//singlyLinkedList.printList();
	
	}
}
