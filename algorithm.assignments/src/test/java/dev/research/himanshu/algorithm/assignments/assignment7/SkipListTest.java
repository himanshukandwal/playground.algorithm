package dev.research.himanshu.algorithm.assignments.assignment7;

import junit.framework.TestCase;

public class SkipListTest extends TestCase {

	public void testAdd() throws Exception {
		SkipList<Integer> skipList = new SkipList<>();
		skipList.add(12);
		skipList.add(13);
		skipList.add(15);
		skipList.add(15);
		skipList.add(14);
		skipList.add(16);
		skipList.add(17);
		
		for (Integer integer : skipList) {
			System.out.println(integer);
		}
		System.out.println(skipList.size());
	}
	
	public void testContains() throws Exception {
		SkipList<Integer> skipList = new SkipList<>();
		skipList.add(12);
		skipList.add(13);
		skipList.add(15);
		skipList.add(15);
		skipList.add(14);
		skipList.add(16);
		skipList.add(17);
		
		boolean found = false;
		assertTrue("failed to find element, get result : " + found, found = skipList.contains(12));
		assertTrue("failed to find element, get result : " + found, found = skipList.contains(13));
		assertFalse("failed to find element, get result : " + found, found = skipList.contains(53));
		assertFalse("failed to find element, get result : " + found, found = skipList.contains(45));
		assertTrue("failed to find element, get result : " + found, found = skipList.contains(17));
	}
	
	public void testRemove() throws Exception {
		SkipList<Integer> skipList = new SkipList<>();
		skipList.add(12);
		skipList.add(13);
		skipList.add(15);
		skipList.add(15);
		skipList.add(14);
		skipList.add(16);
		skipList.add(17);
		
		int[] intArr = new int[5];
		intArr[0] = 12;
		intArr[1] = 13;
		intArr[2] = 14;
		intArr[3] = 15;
		intArr[4] = 16;
		
		for (int integer : intArr) {
			System.out.println("removing - " + integer);
			if (skipList.contains(integer)) 
				assertTrue(skipList.remove(integer));
			
			System.out.println(skipList.contains(integer));
		}
		
		System.out.println("elements remaining : " + skipList.size());
		for (Integer integer : skipList)
			System.out.println(integer);
	}
	
	public void testRemoveAll() throws Exception {
		SkipList<Integer> skipList = new SkipList<>();
		skipList.add(12);
		skipList.add(13);
		skipList.add(15);
		skipList.add(15);
		skipList.add(14);
		skipList.add(16);
		skipList.add(17);
		
		int[] intArr = new int[6];
		intArr[0] = 12;
		intArr[1] = 13;
		intArr[2] = 14;
		intArr[3] = 15;
		intArr[4] = 16;
		intArr[5] = 17;
		
		for (int integer : intArr) {
			System.out.println("removing - " + integer);
			if (skipList.contains(integer)) 
				assertTrue(skipList.remove(integer));
			
			System.out.println(skipList.contains(integer));
		}
		
		System.out.println("elements remaining : " + skipList.size());
		for (Integer integer : skipList)
			System.out.println(integer);
	}
	
}
