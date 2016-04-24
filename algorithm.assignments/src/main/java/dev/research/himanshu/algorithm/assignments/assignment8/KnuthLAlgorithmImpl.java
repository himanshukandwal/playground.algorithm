package dev.research.himanshu.algorithm.assignments.assignment8;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of Knuth's L Algorithm.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class KnuthLAlgorithmImpl {

	public static void main(String[] args) {
		Scanner in = null;
		Timer timer = new Timer();
		
		try {
			if (args.length > 0)
				in = new Scanner(new File(args[0]));
			else
				in = new Scanner(System.in);
			
			List<Integer> inputList = new ArrayList<>();
			
			/**
			 * read the input.
			 */
			while (in.hasNext()) {
				String input = in.next().trim();
				input = input.replaceAll(",", " ");
				
				if (!input.isEmpty()) {
					if (input.matches("\\s+")) {
						for (String integer : input.split("\\s+")) 
							inputList.add(Integer.parseInt(integer.trim()));
					} else 
						inputList.add(Integer.parseInt(input.trim()));
				}
			}
			
			int[] inputArr = new int [inputList.size()];
			
			for (int index = 0; index < inputList.size(); index ++) 
				inputArr [index] = inputList.get(index);
			
			/**
			 * launch algorithm.
			 */
			timer.start();
			int permutations = algorithmL(inputArr);
			System.out.println("permutations : " + permutations);
			System.out.println(timer.end());
			
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (in != null) 
				in.close();
		}
	}

	/**
	 * a wrapper method for ease access.
	 * @param inputArr
	 */
	public static int algorithmL(int[] inputArr) {
		print(inputArr);
		return algorithmL(inputArr, 1);
	}
	
	/**
	 * Implementation of Knuth's algorithm L.
	 * 
	 * @param input
	 * @return
	 */
	private static int algorithmL(int[] inputArr, int count) {
		int j = inputArr.length - 2;
		
		// find position where a[j] < a[j + 1]
		for (; j >= 0; j --) {
			if (inputArr [j] < inputArr [j + 1])
				break;
		}
		
		// base condition : j == 0, processing done. Return the array. 
		if (j < 0)
			return count;
		
		// partition the array : inputArr [0 .. j] and inputArr [j+1 .. n]
		for (int partition2reverseIndex = inputArr.length -1; partition2reverseIndex >= (j + 1); partition2reverseIndex --) {
			if (inputArr [partition2reverseIndex] > inputArr [j]) {
				swap (inputArr, j, partition2reverseIndex);
				break;
			}			
		}

		// reverse the contents of second array : inputArr [j+1 .. n]
		for (int position = 0; position < (inputArr.length - j - 1) / 2; position ++)
			swap (inputArr, j + 1 + position, inputArr.length - position - 1);
		
		count ++;
		
		// print permutation
		print(inputArr);
		
		// execute algorithmL again and return the permutations count.
		return algorithmL(inputArr, count);
	}

	private static void print(int[] inputArr) {
		for (int input : inputArr) 
			System.out.print(input + " ");
		System.out.println();
	}

	/**
	 * a helper function to swap the contents of position1 and position2 within inputArr.
	 * 
	 * @param inputArr
	 * @param position1
	 * @param position2
	 */
	private static void swap (int[] inputArr, int position1, int position2) {
		int interimPlaceholder = inputArr [position1];
		inputArr [position1] = inputArr [position2];
		inputArr [position2] = interimPlaceholder;
	}
	
}
