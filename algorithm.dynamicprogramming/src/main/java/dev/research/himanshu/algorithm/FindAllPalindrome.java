package dev.research.himanshu.algorithm;

import java.util.HashSet;
import java.util.Set;

public class FindAllPalindrome {
	
	public static int palindrome(String str) {
		Set<String> uniquePalindromes = new HashSet<>();
		
		for (int index = 0; index < str.length(); index ++) {
			uniquePalindromes.add ("" + str.charAt(index));
			
			if (index > 0 && index < str.length()) {
				circulate (uniquePalindromes, str, index, 1, true);
				circulate (uniquePalindromes, str, index, 1, false);
			}
		}
		
		System.out.println(uniquePalindromes);
		return uniquePalindromes.size();
	}
	
	private static void circulate (Set<String> uniquePalindromes, String string, int center, int offset, boolean isEven) {
		if ((center - offset) >= 0) {
			if (isEven 
					&& (center + offset - 1 < string.length())
					&& string.charAt(center - offset) == string.charAt(center + offset - 1)) {
				
				uniquePalindromes.add(string.substring(center - offset, center + offset));
				circulate(uniquePalindromes, string, center, offset + 1, true);
			}
			
			if (!isEven 
					&& (center + offset) < string.length()) {
				
				if (string.charAt(center - offset) == string.charAt(center + offset)) {
					uniquePalindromes.add(string.substring(center - offset, center + offset + 1));
					circulate(uniquePalindromes, string, center, offset + 1, false);
				}
			}
		}
	}

}
