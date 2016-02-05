package dev.research.himanshu.algorithm.dynamicprogramming;

public class KnapsackComputation {
	
	public static int recurseEvaluationDuplicatesAllowed(int[][] data, int totalWeight, int index) {
		if (index >= data.length || totalWeight < 0)
			return 0;
		
		int weight = data[index][0];
		int value = data[index][1];
		int max = 0;
		if (totalWeight < weight)
			max = recurseEvaluationDuplicatesAllowed(data, totalWeight, index + 1);
		else {
			int times = totalWeight / weight;
			int remainder = totalWeight % weight;
			int loopcounter = 0;
			max = times * value;
			
			while (times >= 0) {
				max = Math.max(max, recurseEvaluationDuplicatesAllowed(data, remainder + loopcounter * weight, index + 1));
				loopcounter ++;
				times --;
			} 
		}	
		return max;
	}
	
	public static int recurseEvaluationWithoutDuplicates(int[][] data, int totalWeight, int index) {
		if (index >= data.length || totalWeight < 0)
			return 0;
		
		int weight = data[index][0];
		int value = data[index][1];
		int max = 0;
		
		if (totalWeight < weight)
			return recurseEvaluationWithoutDuplicates(data, totalWeight, index + 1);
		else {
			int remainder = totalWeight - weight;
			
			if (remainder == 0) {
				return value;
			} else {
				
			}
			max = Math.max (recurseEvaluationWithoutDuplicates (data, totalWeight, index + 1), value + recurseEvaluationWithoutDuplicates (data, remainder, index + 1));
		}
		
		return max;
	}
}
