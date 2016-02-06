package dev.research.himanshu.algorithm.dynamicprogramming;

import java.util.Arrays;

public class CoinChange {

	public static int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);
		return recurseAndCompute(coins, coins.length - 1, amount);
	}

	public static int recurseAndCompute(int[] coins, int index, int amount) {
		if (index >= 0) {
			int coin = coins[index];

			if (coin > amount) {
				return recurseAndCompute (coins, index - 1, amount);
			} else {
				int remainder = (amount % coin);
				int coinnumber = amount / coin;
				int min = -1;

				// if perfect fit, then just return. No need to see the below tree
				if (remainder == 0) 
					return coinnumber;
				
				// if not, then check all possible cases including me and without me (0 1 2 ..... .coinnumber) and compute the minima out of it.
				// in worst case, minima could be -1 (scene when no child completely divides the amount, i.e. all or partial amount, supplied) 
				for (int loopcounter = 0; loopcounter <= coinnumber; loopcounter++) {
					int lowermin = recurseAndCompute (coins, index - 1, remainder + loopcounter * coin);

					if (lowermin != -1) {
						if (min == -1)
							min = (coinnumber - loopcounter) + lowermin;   // no previous minima determined. Assigning this as the first minima.
						else
							min = Math.min (min, (coinnumber - loopcounter) + lowermin); // summing the actual coins as retrieved from lower tree and then comparing with the minima (if not -1)
					}
				}	
				
				return min;
			}
		} else
			return -1;
	}

}
