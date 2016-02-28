package dev.research.himanshu.algorithm.assignments.lp1;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ConversionUtils {

	public static List<Long> convertToBase(String input, int base) {
		return convertToBase(Long.valueOf(input), base);
	}
	
	public static List<Long> convertToBase(long input, int base) {
		boolean isNegative = input < 0;

		if (isNegative)
			input *= -1;

		List<Long> resultingList = positiveConvertToBase(input, base);

		if (isNegative) {
			ListIterator<Long> listIterator = resultingList.listIterator();
			
			while (listIterator.hasNext())
				listIterator.next();
			
			listIterator.set(0l - resultingList.get(resultingList.size() - 1));
		}

		return resultingList;
	}
	
	private static List<Long> positiveConvertToBase(long input, int base) {
		List<Long> resultingList = new LinkedList<>();
		
		if (input > 0) {
			while (input / base > 0 || input % base > 0) {
				resultingList.add(input % base);
				input /= base;
			}
		} else {
			resultingList.add(0l);
		}
		
		return resultingList;
	}
	
	public static long shiftBase(int base, int times) {
		long extendedBase = 1;
		for (int counter = 0; counter < times; counter++)
			extendedBase *= base;
		
		return extendedBase;
	}
	
	public static boolean verifyBaseRepresentation(List<Long> list, long input, int base) {
		if (input > 0 && (list == null || list.size() == 0))
			return false;
		
		long resultingNumber = 0;
		int counter = 0;
		
		for (Long longVal : list)
			resultingNumber += longVal * Math.pow(base, counter ++); 

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
