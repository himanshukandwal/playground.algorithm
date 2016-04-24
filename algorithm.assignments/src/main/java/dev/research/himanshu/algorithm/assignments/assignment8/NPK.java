package dev.research.himanshu.algorithm.assignments.assignment8;

import java.io.File;
import java.util.Scanner;

/**
 * Combine the solutions to part a to get Permute(A, n, k): ordered sets of cardinality k from a set of size n.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 * 
 */
public class NPK {
	
	public static void main(String[] args) {
		Scanner in = null;
		Timer timer = new Timer();
		
		try {
			if (args.length > 0)
				in = new Scanner(new File(args[0]));
			else
				in = new Scanner(System.in);
			
			/**
			 * read the input. (read only first 2 int values, for N and K)
			 */
			
			System.out.println("Enter value of N : ");
			int n = in.nextInt();
			
			System.out.println("Enter value of K : ");
			int k = in.nextInt();
			
			boolean[] A = new boolean[n + 1];
			
			/**
			 * launch the nPk
			 */
			timer.start();
			nPk(A, n, k, 0);
			System.out.println(timer.end());
			
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (in != null) 
				in.close();
		}
	}
	
	/**
	 * main entry point for nPk.
	 * 
	 * @param A
	 * @param n
	 * @param k
	 * @param count
	 */
	public static void nPk(boolean[] A, int n, int k, int count) {
		if (n < k)
			return;
		else if (k == 0)
			visit(A, count);
		else {
			nPk(A, n - 1, k, count);
			A[n] = true;
			count ++;
			nPk(A, n - 1, k - 1, count);
			A[n] = false;
		}
	}

	/**
	 * main method for calculating the permutation.
	 * 
	 * @param arr
	 * @param i
	 */
	private static void permutation(int[] arr, int i) {
		if (i == 0)
			print(arr);
		else {
			for (int j = 1; j <= i; j++) {
				swap(arr, j, i);
				permutation(arr, i - 1);
				swap(arr, i, j);
			}
		}
	}

	/**
	 * visit method for array A
	 * 
	 * @param A
	 * @param count
	 */
	private static void visit(boolean[] A, int count) {
		int[] arr = new int[count + 1];
		int j = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i]) {
				arr[j] = i;
				j++;
			}
		}
		permutation(arr, arr.length - 1);
	}

	/**
	 * print the array to display the permutation.
	 * 
	 * @param arr
	 */
	private static void print(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	/**
	 * helper function to swap the positions 'i' and 'k' in array 'a'
	 * 
	 * @param a
	 * @param i
	 * @param k
	 */
	private static void swap(int[] a, int i, int k) {
		int temp = a[i];
		a[i] = a[k];
		a[k] = temp;

	}
}
