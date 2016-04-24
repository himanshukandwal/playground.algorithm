package dev.research.himanshu.algorithm.assignments.assignment7;

import java.util.TreeSet;

/**
 * A class to draw performance comparison between treeset and skiplist.
 * 
 * @author G31 (Dharmam Buch and Himanshu Kandwal)
 *
 */
public class TreeSetVsSkipList {

	public static Timer timer = new Timer();
	
	public static SkipList<Integer> skipList;
	public static TreeSet<Integer> treeSet;
	
	public static void testAdd(int range) {
		
		System.out.println("time taken by SkipList for adding " + range + " integers : ");
		
		skipList = new SkipList<>();
		timer.start();
		for (int integer = 0; integer < range; integer++) {
			skipList.add(integer);
		}
		System.out.println(timer.end());
		
		System.out.println("time taken by TreeSet for adding " + range + " integers : ");
	
		treeSet = new TreeSet<Integer>();
		timer.start();
		for (int integer = 0; integer < range; integer++) {
			treeSet.add(integer);
		}
		System.out.println(timer.end());
	}
	
	public static void main(String[] args) {
		int range = 10000;
		if (args.length > 0) {
			range = Integer.parseInt(args[0]);
		}
		
		testAdd(range);
		testContains();
		testRemove(range);
	}
	
	public static void testContains() {

		int[] searchItems = new int[5];
		searchItems[0] = 12;
		searchItems[1] = 0;
		searchItems[2] = 14;
		searchItems[3] = 300;
		searchItems[4] = 100;

		System.out.println("time taken by SkipList for finding integers : ");

		timer.start();
		boolean found = true;
		for (int searchItem : searchItems) {
			found = found && skipList.contains(searchItem);	
		}
		System.out.println(timer.end());
		
		if (found) {
			System.out.println("found all items successfully !");
		} else {
			System.out.println("not found all items successfully !");
		}
		
		System.out.println("time taken by TreeSet for finding integers : ");

		timer.start();
		found = true;
		for (int searchItem : searchItems) {
			found = found && treeSet.contains(searchItem);	
		}
		System.out.println(timer.end());
		
		if (found) {
			System.out.println("found all items successfully !");
		} else {
			System.out.println("not found all items successfully !");
		}
	}
	
	public static void testRemove(int range) {
		System.out.println("time taken by SkipList for removing all integers : ");

		timer.start();
		boolean removed = true;
		for (int integer = 0; integer < range; integer++) {
			removed = removed && skipList.remove(integer);
			
		}
		System.out.println(timer.end());
		
		if (removed) {
			System.out.println("removed all items successfully !");
		} else {
			System.out.println("not removed all items successfully !");
		}
		
		System.out.println("time taken by TreeSet for removing all integers : ");

		timer.start();
		removed = true;
		for (int integer = 0; integer < range; integer++) {
			removed = removed && treeSet.remove(integer);
		}
		System.out.println(timer.end());
		
		if (removed) {
			System.out.println("removed all items successfully !");
		} else {
			System.out.println("not removed all items successfully !");
		}
	}
	
}
