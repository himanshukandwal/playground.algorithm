package dev.research.himanshu.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class WordPattern {

	public static boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");
		boolean isEqual = true;

		if (pattern.length() == words.length) {
			Map<Character, String> patternWordmap = new HashMap<>();
			Set<String> wordsUsed = new HashSet<>();
			int index = 0;
			
			for (index = 0; index < pattern.length() && index < words.length; index++) {
				if (patternWordmap.containsKey(pattern.charAt(index))) {
					isEqual = patternWordmap.get(pattern.charAt(index)).equals(words[index]);
				} else {
					isEqual = wordsUsed.add(words[index]);
					patternWordmap.put(pattern.charAt(index), words[index]);
				}
				if (!isEqual)
					break;
			}

			if (index < words.length - 1)
				isEqual = false;
		} else {
			isEqual = false;
		}
		
		return isEqual;
	}
	
	public static boolean wordPattern2(String pattern, String str) {
	    String[] words = str.split(" ");
	    if (words.length != pattern.length())
	        return false;
	    Map index = new HashMap();
	    
	    /**
	     * go through the pattern letters and words in parallel and compare the indexes where they last appeared.
	     */
	    for (Integer i=0; i<words.length; ++i)
	    	if (!Objects.equals(index.put(pattern.charAt(i), i), index.put(words[i], i)))
	    		return false;
	    return true;
	}

}
