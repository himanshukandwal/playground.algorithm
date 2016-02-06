package dev.research.himanshu.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {
	
	/**
	 * The best usage of maps !
	 * 
	 */
	public static int lengthOfLongestSubstring(String s) {
		int start = 0;
		int end = 0;
		int head = 0;
		
		Map<Character, Integer> metadataMap = new HashMap<>();
		
		for (int index = 0; index < s.length(); index++) {
			char ch = s.charAt(index);
			
			Integer pos = metadataMap.put(ch, index);
			
			// pos < head : for the cases of previous substring's clutter
			if (pos == null || pos < head) {	
				if ((index + 1 - head) > (end - start)) {
					end = index + 1;
					start = head;
				}
			} else {
				head = pos + 1;
			}
		}

		return end - start;
	}
	
}
