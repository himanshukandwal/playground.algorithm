package dev.research.himanshu.algorithm;

import java.util.Arrays;

public class CoinChange {

	public static int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);
		int innerloopcounter = 0;
		int outerloopcounter = 0;
		int minChange = 0;
		
		// another loop to change the coins array  -- all permutations and combinations while ()
		while (innerloopcounter < coins.length - 1) {
			int[] coinsArr = Arrays.copyOfRange(coins, innerloopcounter, coins.length);
			int change = recurseAndCompute(coins, coins.length - 1, 0, 0, amount);
			
			if (innerloopcounter == 0)
				minChange = change;
			
			if (minChange > change)
				minChange = change;
			
			innerloopcounter ++;
		}
		
		return minChange;
	}

	public static int recurseAndCompute(int[] coins, int index, int numberOfCoins, int descentValue, int amount) {
		if (index >= 0) {
			int traversingAmount = amount;
			int coin = coins[index];

			int remainder = (traversingAmount % coin);
			int coinnumber = traversingAmount / coin;
			int denominationResult = numberOfCoins + coinnumber;
			System.out.println(" [1st] coin " + coin + " number : " + denominationResult);
			int lowerDenominationResult = recurseAndCompute(coins, index - 1, numberOfCoins, 0, remainder);
			if (lowerDenominationResult == -1) {
				int loopcounter = 1;
				while (lowerDenominationResult == -1 && --coinnumber > 0) {
					lowerDenominationResult = recurseAndCompute(coins, index - 1, denominationResult - loopcounter++, 0, remainder += coin);
					loopcounter ++;
				}

				if (lowerDenominationResult == -1 && coinnumber == 0) {
					return -1;
				} else {
					System.out.println(" coin " + coin + " number : " + denominationResult);
					return denominationResult + lowerDenominationResult;
				}
			} else {
				System.out.println(" coin " + coin + " number : " + denominationResult);
				return denominationResult + lowerDenominationResult;
			}
		} else {
			return (amount == 0 ? 0 : -1);
		}
	}
	
	public static void main(String[] args) {
		int[] intarr = new int[] {2, 5, 10, 1};
		
		Arrays.sort(intarr);
		
		for (int numberOfItemsToRemove = -1; numberOfItemsToRemove < intarr.length; numberOfItemsToRemove++) {
			for (int index = 0; index < intarr.length; index++) {
				for (int innerindex = index + 1; innerindex < intarr.length; innerindex++) {
					if (numberOfItemsToRemove == -1) {
						System.out.print(intarr[innerindex]);
					} else if (intarr[index] != intarr[numberOfItemsToRemove]) {
						System.out.print(intarr[innerindex]);
					} else {
						System.out.print(intarr[innerindex]);
					}
				}
				System.out.println();
			}
		}
		
		
	}

}
