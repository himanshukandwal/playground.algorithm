package dev.research.himanshu.algorithm.assignments.assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Cuckoo Hashing Algorithm implementation.
 * 
 * @author G31
 *
 */
public class CuckooHashingAlgorithmImpl {

	private static int tableSize = 2;

	private static Integer[] array = new Integer[tableSize];
	
	private static Map<Integer, Integer> overflowTable = new HashMap<>();

	/*
	 * the 'a' and 'b' table for the 3 hash functions.
	 * 
	 * h(x) = (a*x + b) mod hashPrime
	 */
	private static HashFunction<Integer>[] linearHashFunctions = new HashFunction[2];

	private static List<Integer> usedWeights = new ArrayList<>();

	private static int maxTries = 15;

	// static block to populate the weights set.
	private static void initializeHashFunctions() {
		Random random = new Random();
		while (usedWeights.size() < 4) {
			int weight = random.nextInt();

			while (usedWeights.contains(weight)) {
				weight = random.nextInt();
			}

			usedWeights.add(weight);
		}
		System.out.println(usedWeights);
		linearHashFunctions[0] = new HashFunction<>(usedWeights.get(0), usedWeights.get(1));
		linearHashFunctions[1] = new HashFunction<>(usedWeights.get(2), usedWeights.get(3));
	}
	
	public static void add(Integer number, int hashFunctionIndex, int tries) {
		if (tries <= maxTries) {
			for (int hashFuncIndex = 0; hashFuncIndex < array.length; hashFuncIndex ++)
				System.out.println(getTableIndex(hashFuncIndex, number));
			
			int index = getTableIndex(hashFunctionIndex, number);
			
			Integer existingNumber = null;
			
			if (array [index] != null)
				existingNumber = array [index];
			
			array [index] = number;
			
			if (existingNumber != null) {
				for (int hashFuncIndex = 0; hashFuncIndex < array.length; hashFuncIndex ++) {
					int existingNumberIndex = getTableIndex(hashFuncIndex, existingNumber);
					System.out.println("existingNumberIndex : " + existingNumberIndex);
					if (existingNumberIndex != index)  {
						add(existingNumber, hashFuncIndex, tries ++);
						break;
					}
				}
			}
		} else {
			overflowTable.put(number.hashCode(), number);
		}
	}

	public static int getTableIndex(int hashFunctionIndex, Integer number) {
		int index = linearHashFunctions[hashFunctionIndex].hash(number) % tableSize;
		if (index < 0)
			index = tableSize + index;
		
		return index;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputScanner;
		initializeHashFunctions();
		
		if (args.length > 0) {
			File inputFile = new File(args[0]);
			inputScanner = new Scanner(inputFile);
		} else {
			inputScanner = new Scanner(System.in);
		}
		
		while (inputScanner.hasNext()) {
			String value = inputScanner.next();
			if (value.equals("!"))
				break;
		
			int num = Integer.valueOf(value);
			add(num, 0, 1);
			
			System.out.println("array : ");
			for (int index = 0; index < array.length; index++)
				System.out.print(array[index] + " ");
			System.out.println(overflowTable);
		}

		inputScanner.close();

		
	}

}
