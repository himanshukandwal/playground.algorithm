package dev.research.himanshu.algorithm.dynamicprogramming;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ShortestPathBetweenTwoNumbers {
	
	public static void main(String[] args) {
//		finder(1, 2);
//		finder(132, 135);
//		finder(99, 144);
//		finder(100, 103);
//		finder(100, 102);
//		finder(100, 104);
//		finder(100, 108);
//		finder(100, 208);
//		finder(100, 208);
		finder(99, 155); // 10 giving 11
	}
	
	public static void finder(int number1, int number2) {
		int answer;
		
		if (number1 <= number2) {
			if (number1 == number2)
				answer = 0;
			else 
				answer = find (number1, number2, 0);
		} else
			answer = -1;
		
		System.out.println("answer is : " + answer);
	}

	private static int find (int number1, int number2, int pathLength) {
		if (number1 == number2) 
			return pathLength;
		
		if (number1 > number2) 
			return -1;
		
		Integer foundGoodNumber = null;
		Integer shortestPath = Integer.MAX_VALUE;
		
		for (int number : uniqueReverseSorted(number1)) {
			int effectiveNum = number1 + number;
			int outcome = Integer.MAX_VALUE;
			
			if (foundGoodNumber == null || (foundGoodNumber != null && (foundGoodNumber < number)))
					outcome = find (effectiveNum, number2, pathLength + 1);
			
			if ((outcome > 0) && (shortestPath > outcome)) {
				shortestPath = outcome;
			
				if (foundGoodNumber == null || (foundGoodNumber != null && foundGoodNumber < number))
					foundGoodNumber = number;
			}
		}
		
		return (shortestPath == Integer.MAX_VALUE ? -1 : shortestPath);
	}
	
	public static Set<Integer> uniqueReverseSorted(int number) {
		Set<Integer> unique = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return (o2.compareTo(o1));
			}
		});
		
		while (number/10 > 0 || number % 10 > 0) {
			unique.add(number % 10);
			number /= 10; 
		}
		
		return unique;
	}
	
}
