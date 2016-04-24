package dev.research.himanshu.algorithm.assignments.assignment7;

import java.util.Random;

/**
 * A class created to provide unbiased random values. Implemented the logic as professor suggested in class.
 * Logic : 
 * 		- find random value from range (0 - maxlevel) twice. If in both the repetitions, the value we got is same 
 * 		  then again find two values (till the point we have two distinct values in both first run and second run)
 * 
 * 		- return the first value.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 */
public class Randomizer {

	public static Random random = new Random();
	
	/**
	 * a method exposed that implements the feature to provide randomization.
	 * 
	 * @param maxlevel
	 * @return random value from (0 - maxlevel)
	 */
	public static int randomValue(int maxlevel) {
		if (maxlevel <= 1) 
			return 1;
		
		int firstRunValue = randomValueInternal(maxlevel);
		int secondRunValue = randomValueInternal(maxlevel);
		
		while (firstRunValue == secondRunValue) {
			firstRunValue = randomValueInternal(maxlevel);
			secondRunValue = randomValueInternal(maxlevel);
		}
		
		return firstRunValue;
	}
	
	/**
	 * a private method performing the choice of value from (0 - maxlevel).
	 * 
	 * @param maxlevel
	 * @return random value from (0 - maxlevel)
	 */
	private static int randomValueInternal(int maxlevel) {
		int loopcounter = 1;
		while (loopcounter < maxlevel) {
			if (random.nextBoolean())
				loopcounter ++;
			else 
				break;
		}
		
		return (loopcounter == maxlevel) ? randomValueInternal(maxlevel) : loopcounter;
	}
	
}
