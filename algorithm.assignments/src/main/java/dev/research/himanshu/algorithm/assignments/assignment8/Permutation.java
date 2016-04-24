package dev.research.himanshu.algorithm.assignments.assignment8;

import java.util.Scanner;

/**
 * Implement the following algorithms discussed in class: 
 * 	
 * 		(a) Combination(A, n, k), 
 * 		(b) Permute(A, n). 
 * 
 * For Permute(), implement Take 2 and Heap's algorithms, and compare their running times for n = 8..14.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class Permutation {

	private static long count = 0;
	private static Timer timer = new Timer();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter value of N : ");
		int n = in.nextInt();
		in.close();
		int[] Arr = new int[n + 1];
		
		for (int i = 0; i < Arr.length; i++)
			Arr[i] = i;
		
		timer.start();
		permutation(Arr, n);
		System.out.println(timer.end());
		
		System.out.println("Take 2 for n =  " + n + " and total number of permutation = " + count + "\n");
		
		count = 0;
		timer.start();
		permutationHeap(Arr, n);
		System.out.println(timer.end());
		
		System.out.println("Heap's Algorithm for n =  " + n + " and total number of permutation = " + count);
	}

	private static void permutation(int[] arr, int i) {
		if (i == 0)
			visit(arr);
		else {
			for (int j = 1; j <= i; j++) {
				swap(arr, j, i);
				permutation(arr, i - 1);
				swap(arr, i, j);
			}
		}
	}

	private static void permutationHeap(int[] A, int n) {
		if (n == 0)
			visit(A);
		else
			for (int i = 1; i <= n; i++) {
				permutationHeap(A, n - 1);
				if (n % 2 == 0)
					swap(A, i, n);
				else {
					swap(A, 1, n);
				}
			}
	}

	private static void visit(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// System.out.print(" " + arr[i]);
		}
		count++;
		// System.out.println();
	}

	private static void swap(int[] a, int i, int k) {
		int temp = a[i];
		a[i] = a[k];
		a[k] = temp;

	}

}
