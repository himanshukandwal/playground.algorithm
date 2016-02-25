package dev.research.himanshu.algorithm.assignments.assignment4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class SelectionProblem {

	private static long startTime, endTime, elapsedTime;
	private static long phase = 0;


	public static void timer()
	{
		if(phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime-startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
	}

	private static Long[] buildArray() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Size of Array : -");
		int k = sc.nextInt() ;
		Long[] arr = new Long[k];
		Random randomGenerator = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] =  randomGenerator.nextLong();	
		}		
		return arr;
	}

	private static void heapApproach(BinaryHeap bh, int k, int len) {
		// Using delete minimum for n-k times.

		for (int i = 0; i < len-k; i++) {
			bh.deleteMin();
		}

	}

	private static Long[] linearApproach(Long[] arr, int k) {
		// Using Sorting + Take last K elements.
		Arrays.sort(arr);
		Long[] res = new Long[k];
		int j = 0 ;
		for (int i = arr.length-k; i < arr.length; i++) {
			res[j] = arr[i]; 
			j++;
		}

		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long[] arr = buildArray();

		BinaryHeap<Long> bh = new BinaryHeap<Long>(arr.length);

		for (int i = 0; i < arr.length; i++) {
			bh.insert(arr[i]);
		}
		
		//bh.printHeap();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Value of K : -");
		int k = sc.nextInt() ;

		System.out.println("Binary Heap Method.");
		timer();
		heapApproach(bh,k,arr.length);
		timer();

		System.out.println("Linear Approach.");
		timer() ;
		Long[] resLiner = linearApproach(arr,k);
		timer();

	}
	
}
