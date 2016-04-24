package dev.research.himanshu.algorithm.assignments.assignment5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Cuckoo Hashing Algorithm implementation.
 * 
 * @author G31
 *
 */
public class CuckooHashingAlgorithmImpl {

	private static int tableSize = 31;
	private static Integer[] array = new Integer[tableSize];
	private static Map<Integer, Integer> overflowTable = new LinkedHashMap<>();
	private static int maxTries = 200;
	private static int entryCount;
	private static double fillpct;
	private static boolean isPassiveHashing;
	private static Timer timer = new Timer();
	
	/*
	 * the 'a' and 'b' table for the 3 hash functions.
	 * 
	 * h(x) = (a*x + b) mod hashPrime
	 */
	private static HashFunction<Integer>[] linearHashFunctions = new HashFunction[3];
	private static List<Integer> usedWeights = new ArrayList<>();
	private static int maxOverflowSize = 10;

	// static block to populate the weights set.
	private static void initializeHashFunctions() {
		Random random = new Random();
		while (usedWeights.size() < 6) {
			int weight = random.nextInt();

			while (weight > 0 && usedWeights.contains(weight)) {
				weight = random.nextInt();
			}

			usedWeights.add(weight);
		}

		linearHashFunctions[0] = new HashFunction<>(usedWeights.get(0), usedWeights.get(1));
		linearHashFunctions[1] = new HashFunction<>(usedWeights.get(2), usedWeights.get(3));
		linearHashFunctions[2] = new HashFunction<>(usedWeights.get(4), usedWeights.get(5));
	}

	public static int getEntryCount() {
		return entryCount;
	}

	public static void setEntryCount(int entryCount) {
		CuckooHashingAlgorithmImpl.entryCount = entryCount;
	}

	public static Double getFillpct() {
		return fillpct * 100d;
	}

	public static boolean add(Integer number) {
		boolean result;
		if ((result = add (number, 0, 1))) {
			updateMetaData(true);
		}
		return result;
	}

	public static void updateMetaData(boolean increment) {
		if (increment)
			entryCount ++;
		else
			entryCount --;

		fillpct = (double) entryCount / tableSize;
		
		// if the storage is more than 95 percent full, then rehash the table.
		if (getFillpct() >= 95)
			rehash ();

		if (!isPassiveHashing) {
			if (overflowTable.size() > maxOverflowSize) {
				rehash (false);
				isPassiveHashing = false;
			}
		}
	}

	private static void rehash() {
		rehash(true);
	}
	
	private static void rehash(boolean active) {
		System.out.println(" -- rehashing " + (active ? "active" : "passive" ) +" !!");
		if (isPassiveHashing) 
			return;
			
		isPassiveHashing = !active;
		
		if (active) {
			tableSize = PrimeNumberGenerator.getNextLargestPrime(2 * tableSize);
			
			Integer[] oldArray = array;

			array = new Integer[tableSize];
			entryCount = 0;

			for (int index = 0; index < oldArray.length; index++) {
				if (oldArray[index] != null) {
					add (oldArray[index]);
				}
			}
		}
		
		Map<Integer, Integer> oldOverflowTable = new LinkedHashMap<>(overflowTable);
		overflowTable.clear();
		
		for (Iterator<Map.Entry<Integer, Integer>> overflowMapEntryIterator = oldOverflowTable.entrySet().iterator(); overflowMapEntryIterator.hasNext();) {
			Map.Entry<Integer, Integer> overflowMapEntry = overflowMapEntryIterator.next();
			if (add (overflowMapEntry.getValue()))
				overflowMapEntryIterator.remove();
			else 
				overflowTable.put(overflowMapEntry.getKey(), overflowMapEntry.getValue());
		}
	}

	public static boolean find (int number) {
		boolean found = false;
		for (int hashFuncIndex = 0; hashFuncIndex < linearHashFunctions.length; hashFuncIndex++) {
			int estimatedIndex = getTableIndex(hashFuncIndex, number);
			if (array [estimatedIndex] != null && array [estimatedIndex].compareTo(number) == 0)
				return true;
		}
		
		for (Map.Entry<Integer, Integer> overflowMapEntry : overflowTable.entrySet()) {
			if (overflowMapEntry.getValue().compareTo(number) == 0)
				return true;
		}
		
		return found;
	}
	
	private static boolean add(Integer number, int hashFunctionIndex, int tries) {
		boolean result = true;
		if (tries <= maxTries) {

			int index = getTableIndex(hashFunctionIndex, number);
			Integer existingNumber = null;

			if (array [index] != number) {

				if (array [index] != null)
					existingNumber = array [index];

				array [index] = number;

				if (existingNumber != null) {
					for (int hashFuncIndex = 0; hashFuncIndex < linearHashFunctions.length; hashFuncIndex++) {
						int existingNumberIndex = getTableIndex (hashFuncIndex, existingNumber);

						if (existingNumberIndex != index) {
							add (existingNumber, hashFuncIndex, ++tries);
							break;
						}
					}
				}
			} else {
				result = false;
			}
		} else {
			overflowTable.put(number.hashCode(), number);
		}
		
		return result;
	}

	public static int getTableIndex(int hashFunctionIndex, Integer number) {
		int index = linearHashFunctions[hashFunctionIndex].hash(number) % tableSize;
		if (index < 0)
			index = tableSize + index;

		return index;
	}

	public static void main(String[] args) throws FileNotFoundException {
		initializeHashFunctions();

		int range = 1000000;
		
		System.out.println(" -- adding ");
		timer.start();
		for (int index = 0; index < range; index++) {
			add (index);
			System.out.print("| " + getFillpct().intValue() + "% |  array : [ ");
			for (int loopIndex = 0; loopIndex < array.length; loopIndex++)
				if (array[loopIndex] != null)
					System.out.print(array[loopIndex] + " , ");
			System.out.print(" ] ");
		}
		System.out.println(timer.end().toString());
		
		System.out.println(" -- searching ");
		timer.start();
		Random random = new Random();
		for (int index = 0; index < range; index++) {
			int lookupNumber = random.nextInt(range + 150);
//			System.out.println(" -- found " + lookupNumber + " : " + find (lookupNumber));
		}
		System.out.println(timer.end().toString());
		
		System.out.print("| " + getFillpct().intValue() + "% |  array : [ ");
		for (int loopIndex = 0; loopIndex < array.length; loopIndex++)
			if (array[loopIndex] != null)
				System.out.print(array[loopIndex] + " , ");
		System.out.print(" ] ");
	}

}
