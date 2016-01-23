package dev.research.himanshu.algorithm;

public class CheckIfAPowerOfANumber {
	
	public static boolean isPowerOfThree(int n) {
		if (n > 0) {
			Double value = Math.log(n) / Math.log(3);
			System.out.println(value);
			return !(((value - value.intValue()) > 0.0) &&
					((value - value.intValue()) < 0.999999999999990));
		} 
		return false;
	}

}
