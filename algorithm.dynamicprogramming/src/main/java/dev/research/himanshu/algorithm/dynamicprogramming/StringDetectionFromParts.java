package dev.research.himanshu.algorithm.dynamicprogramming;

public class StringDetectionFromParts {
	
	public static void main(String[] args) {
		finder("zps", "apo", "zappos");
		finder("apo", "zps", "zappos");
		finder("alg", "ano", "analog");
		finder("zpsc", "apote", "zappostech");
		finder("zpscho", "apote","zappostech");
		finder("hewodr", "llol", "helloworld");
		finder("heword", "llol", "helloworld");
		finder("agths", "lorim", "algorithms");
		finder("zappos", "tech", "zappostech");
		finder("ppos", "zatech", "zappostech");
		finder("ppos", "zatecha", "zappostech");
		finder("pposa", "zatech", "zappostech");
		finder("ppos", "zatech", "zappostecha");
		finder("zappostecha", "", "zappostecha");
		finder("", "zappostecha", "zappostecha");
		finder("", "zappostecha", "zappostech");
		finder("", "zappostech", "zappostech");
		finder("", "abc", "abc");
		finder("abc", "abc", "");
		finder("abc", "abc", "ababcc");
		finder("aaabda", "aabdca", "aabdcaaabdaa");
	}
	
	public static int finder(String part1, String part2, String input) {
		int output;
		if (input.length() == (part1.length() + part2.length())) {
			if (find (part1.toCharArray(), 0, part2.toCharArray(), 0, input.toCharArray(), 0))
				output = 1;
			else 
				output = 0;
		} else
			output = 0;
		
		System.out.println("answer is : " + output);
		return output;
	}

	private static boolean find(char[] part1Array, int index1, char[] part2Array, int index2, char[] inputArray, int inputIndex) {
		
		if ((inputIndex == inputArray.length) && (index2 == part2Array.length) && (index2 == part2Array.length))
			return true;
		
		if (!checkLength(inputArray, inputIndex))
			return false;
		
		boolean output = false;
		
		if ((checkLength(part1Array, index1) && checkLength(inputArray, inputIndex) && part1Array[index1] != inputArray[inputIndex]) 
				&& (checkLength(part2Array, index2) && checkLength(inputArray, inputIndex) && part2Array[index2] != inputArray[inputIndex]))
			return false;
		else {
			if ((checkLength(part1Array, index1) && checkLength(inputArray, inputIndex) && part1Array[index1] == inputArray[inputIndex]) 
					&& (!checkLength(part2Array, index2) || (checkLength(part2Array, index2) && checkLength(inputArray, inputIndex) && part2Array[index2] != inputArray[inputIndex]))) {
				
				output= find (part1Array, index1 + 1, part2Array, index2, inputArray, inputIndex + 1);
				
			} else if ((!checkLength(part1Array, index1) || checkLength(part1Array, index1) && checkLength(inputArray, inputIndex) && part1Array[index1] != inputArray[inputIndex]) 
					&& (checkLength(part2Array, index2) && checkLength(inputArray, inputIndex) && part2Array[index2] == inputArray[inputIndex])) {
				
				output = find (part1Array,  index1, part2Array, index2 + 1, inputArray,  inputIndex + 1);
				
			} else {
				
				output = find (part1Array,  index1, part2Array, index2 + 1, inputArray,  inputIndex + 1) 
						|| find (part1Array,  index1 + 1, part2Array, index2, inputArray,  inputIndex + 1);
			}
		}
		
		return output;
		
	}
	
	public static boolean checkLength(char[] arr, int index) {
		return (index <= arr.length -1);
	}
	
}
