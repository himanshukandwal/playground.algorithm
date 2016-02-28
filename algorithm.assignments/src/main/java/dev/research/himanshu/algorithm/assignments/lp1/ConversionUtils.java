package dev.research.himanshu.algorithm.assignments.lp1;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ConversionUtils {

	public static List<Integer> convertToBase(String input, int base) {
		return convertToBase(Long.valueOf(input), base);
	}
	
	public static List<Integer> convertToBase(long input, int base) {
		boolean isNegative = input < 0;

		if (isNegative)
			input *= -1;

		List<Integer> resultingList = positiveConvertToBase(input, base);

		if (isNegative) {
			ListIterator<Integer> listIterator = resultingList.listIterator();
			
			while (listIterator.hasNext())
				listIterator.next();
			
			listIterator.set(0 - resultingList.get(resultingList.size() - 1));
		}

		return resultingList;
	}
	
	private static List<Integer> positiveConvertToBase(long input, int base) {
		List<Integer> resultingList = new LinkedList<>();
		
		if (input > 0) {
			while (input / base > 0 || input % base > 0) {
				resultingList.add((int) (input % base));
				input /= base;
			}
		} else {
			resultingList.add(0);
		}
		
		return resultingList;
	}
	
	public static boolean verifyBaseRepresentation(List<Integer> list, long input, int base) {
		if (input > 0 && (list == null || list.size() == 0))
			return false;
		
		long resultingNumber = 0;
		int counter = 0;
		
		for (Integer integer : list)
			resultingNumber += integer * Math.pow(base, counter ++); 

		return (resultingNumber == input ? true : false);
	}
	
	public static void main(String[] args) {
		System.out.println(verifyBaseRepresentation(convertToBase(1562322, 12), 1562322, 12));
		System.out.println(verifyBaseRepresentation(convertToBase(1562322, 576), 1562322, 12));
		System.out.println(verifyBaseRepresentation(convertToBase(1562322, 576), 1562322, 576));
		System.out.println(verifyBaseRepresentation(convertToBase(0, 576), 0, 576));
		System.out.println(verifyBaseRepresentation(convertToBase(-10, 10), 10, 10));
		System.out.println(verifyBaseRepresentation(convertToBase(-10, 10), -10, 10));
	}
	
}