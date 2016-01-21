package dev.research.himanshu.algorithm.assignments.assignment0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueInsertionAlgorithm {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	
	public static void memory() {
        long memAvailable = Runtime.getRuntime().totalMemory();
        long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
    }
	
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
	
	public static void main(String[] args) {
		System.out.println("----------------------------------------------------");
		
		testsForInt(1000000, true);
		testsForInt(1000000, false);
		
		testsForInt(5000000, true);
		testsForInt(5000000, false);
		
		testsForInt(10000000, true);
		testsForInt(10000000, false);
		
		System.out.println("----------------------------------------------------");

		System.out.println("----------------------------------------------------");

		testsForFloat(1000000, true);
		testsForFloat(1000000, false);
		
		testsForFloat(1000000, true);
		testsForFloat(5000000, false);
		
		testsForFloat(1000000, true);
		testsForFloat(10000000, false);
		
		System.out.println("----------------------------------------------------");

		System.out.println("----------------------------------------------------");

		testsForDouble(1000000, true);
		testsForDouble(1000000, false);
		
		testsForDouble(5000000, true);
		testsForDouble(5000000, false);
		
		testsForDouble(5000000, true);
		testsForDouble(10000000, false);
		
		System.out.println("----------------------------------------------------");

	}

	private static void testsForInt(int range, boolean isRandom) {
		List<Integer> intList = new ArrayList<Integer>(range);
		
		for (int index = range; index > 0; index--)
			intList.add(range - index, index);
		
		if (isRandom)
			Collections.shuffle(intList);
		
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		
		System.out.println("\nmerge sorting for " + range + " <Integer> elements " + (isRandom ? " in random order " : " in reverse order " ) +"!");
		timer();
		priorityQueue.addAll(intList);
		System.out.println("sorted for " + range + " elements !");
		timer();
	}
	
	private static void testsForFloat(int range, boolean isRandom) {
		List<Float> floatList = new ArrayList<Float>(range);
		
		for (int index = range; index > 0; index--)
			floatList.add(range - index, (float) index);
		
		if (isRandom)
			Collections.shuffle(floatList);
		
		PriorityQueue<Float> priorityQueue = new PriorityQueue<Float>();
		
		System.out.println("\nmerge sorting for " + range + " <Double> elements " + (isRandom ? " in random order " : " in reverse order " ) +"!");
		timer();
		priorityQueue.addAll(floatList);
		System.out.println("sorted for " + range + " elements !");
		timer();
	}

	private static void testsForDouble(int range, boolean isRandom) {
		List<Double> doubleList = new ArrayList<Double>(range);
		
		for (int index = range; index > 0; index--)
			doubleList.add(range - index, (double) index);
		
		if (isRandom)
			Collections.shuffle(doubleList);
		
		PriorityQueue<Double> priorityQueue = new PriorityQueue<Double>();
		
		System.out.println("\nmerge sorting for " + range + " <Double> elements " + (isRandom ? " in random order " : " in reverse order " ) +"!");
		timer();
		priorityQueue.addAll(doubleList);
		System.out.println("sorted for " + range + " elements !");
		timer();
	}
}
