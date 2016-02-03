package dev.research.himanshu.algorithm;

import java.util.Arrays;

public class CoinChange {

	public static int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);
		return recurseAndCompute(coins, coins.length - 1, 0, amount);
	}

	public static int recurseAndCompute(int[] coins, int index, int numberOfCoins, int amount) {
		if (index >= 0) {
			int traversingAmount = amount;
			int coin = coins[index];

			int remainder = (traversingAmount % coin);
			int coinnumber = traversingAmount / coin;
			int denominationResult = numberOfCoins + coinnumber;
			
			int lowerDenominationResult = recurseAndCompute (coins, index - 1, denominationResult, remainder);
			
			if (lowerDenominationResult == -1) {
				int loopcounter = 1;
				while (lowerDenominationResult == -1 && --coinnumber >= 0) {
					lowerDenominationResult = recurseAndCompute (coins, index - 1, denominationResult - loopcounter++, remainder += coin);
				}

				if (lowerDenominationResult == -1 && coinnumber < 0) {
					return -1;
				} else {
					System.out.println(" coin " + coin + " number : " + coinnumber);
					return coinnumber + lowerDenominationResult;
				}
			} else {
				System.out.println(" coin " + coin + " number : " + coinnumber);
				return coinnumber + lowerDenominationResult;
			}
		} else {
			return (amount == 0 ? 0 : -1);
		}
	}
	
}
