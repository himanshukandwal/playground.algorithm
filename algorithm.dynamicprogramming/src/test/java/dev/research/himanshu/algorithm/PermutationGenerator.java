package dev.research.himanshu.algorithm;

import java.util.ArrayList;
import java.util.List;

public class PermutationGenerator {

	private List<Integer> intArr;

	public PermutationGenerator(List<Integer> intArr) {
		this.intArr = intArr;
	}

	public List<Integer> getIntArr() {
		return intArr;
	}

	public List<List<Integer>> permutate() {
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		
		List<Integer> seenElements = new ArrayList<Integer>();
		List<Integer> permutedList = null;
		seenElements.add(-1);
		
		for (int arrayIndex = 0; arrayIndex < getIntArr().size(); arrayIndex ++) {
			for (int permutationPosition = 0; permutationPosition < getIntArr().size(); permutationPosition ++) {
				if (permutationPosition == arrayIndex)
					continue;
				else {
					permutedList = new ArrayList<Integer>();
					permutedList.add(getIntArr().get(arrayIndex));
					
					for (int innerPermutationIndex = permutationPosition; innerPermutationIndex < getIntArr().size(); innerPermutationIndex ++) {
						if (innerPermutationIndex == arrayIndex)
							continue;
						else
							permutedList.add(getIntArr().get(innerPermutationIndex));
					}
					
					if (permutedList.size() != getIntArr().size()) 
						permutations.add(permutedList);
				}
			}
			
			
			for (int permutationPosition = getIntArr().size() -1; permutationPosition > 0; permutationPosition --) {
				if (permutationPosition == arrayIndex)
					continue;
				else {
					permutedList = new ArrayList<Integer>();
					permutedList.add(getIntArr().get(arrayIndex));
					
					for (int innerPermutationIndex = permutationPosition; innerPermutationIndex > 0; innerPermutationIndex --) {
						if (innerPermutationIndex == arrayIndex)
							continue;
						else
							permutedList.add(getIntArr().get(innerPermutationIndex));
					}
					
					if (permutedList.size() != getIntArr().size()) 
						permutations.add(permutedList);
				}
			}
		}
		
		return permutations;
	}

	public static void main(String[] args) {
		List<Integer> inputArrayList = new ArrayList<Integer>();

		for (int index = 0; index < 5; index++)
			inputArrayList.add(index);
		
		System.out.println("Input array : ");
		for (Integer element : inputArrayList) {
			System.out.print(element + " ");
		}
		System.out.println();
		
		PermutationGenerator generator = new PermutationGenerator(inputArrayList);
		List<List<Integer>> permutations = generator.permutate();
		
		System.out.println("Permutations array : ");
		for (List<Integer> permutation : permutations) {
			for (Integer element : permutation) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}

}
