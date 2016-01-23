package dev.research.himanshu.algorithm;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * 
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 * 
 * @author Heman
 */
public class ShortestPalindrome {

	public String shortestPalindrome(String s) {
		StringBuffer stringToAppend = new StringBuffer();

		if (s.length() > 1) {
			char[] inputStringCharArr = s.toCharArray();
			int length = inputStringCharArr.length;
			boolean isEven = (length % 2 == 0);

			final int centerPivot = (isEven ? (length / 2 - 1) : (int) length / 2);
			boolean successfullyFound = false;
			int centerIndex = centerPivot;
			Boolean computationResult = false;

			for (; centerIndex >= 0 && !successfullyFound; centerIndex--) {
				computationResult = false;

				successfullyFound = (((computationResult = circulate(inputStringCharArr, centerIndex, 1,
						isEven)) == null)
								? true
								: (computationResult ? true
										: ((computationResult = circulate(inputStringCharArr, centerIndex, 1,
												!isEven)) == null) ? true : computationResult));
			}
			centerIndex++;

			if (successfullyFound && (computationResult != null && computationResult)) {
				System.out.println(" Its a palindrome !");
			} else {
				if (successfullyFound) {
					for (int inputStringCharArrIndex = 2 * centerIndex + (isEven ? 1
							: 0); inputStringCharArrIndex < inputStringCharArr.length; inputStringCharArrIndex++) {
						stringToAppend.append(inputStringCharArr[inputStringCharArrIndex]);
					}
				} else {
					for (int inputStringCharArrIndex = 1; inputStringCharArrIndex < inputStringCharArr.length; inputStringCharArrIndex++) {
						stringToAppend.append(inputStringCharArr[inputStringCharArrIndex]);
					}
				}
			}
		}

		return stringToAppend.reverse().toString();
	}

	// center + offset
	private Boolean circulate(char[] charArr, int center, int offset, boolean isEven) {
		Boolean result = null;
		if ((center - offset) >= 0) {
			if (isEven) {
				if (charArr[center - offset + 1] == charArr[center + offset]) {
					result = circulate(charArr, center, offset + 1, isEven);
				} else {
					result = false;
				}
			} else {
				if (charArr[center - offset] == charArr[center + offset]) {
					result = circulate(charArr, center, offset + 1, isEven);
				} else {
					result = false;
				}
			}
		} else {
			result = ((center + offset + (isEven ? 1 : 0)) == charArr.length ? true : null);
		}
		return result;
	}

	public String shortestPalindromeAnother(String s) {
		StringBuffer stringToAppend = new StringBuffer();
		
		if (s.length() > 1) {
			char[] inputStringCharArr = s.toCharArray();
			StringBuffer backupForIdenticalFail = new StringBuffer();
			boolean isIdenticalSituation = true;
			
			int forwardIndex = 0;
			int reverseIndex = inputStringCharArr.length - 1;
			Integer recordedIndex = null;
			
			while ((stringToAppend.length() + inputStringCharArr.length) % 2 == 0 ? (reverseIndex != forwardIndex) : reverseIndex >= forwardIndex) {
				char forwardElement = inputStringCharArr[forwardIndex];
				char reverseElement = inputStringCharArr[reverseIndex];

				if (forwardElement == reverseElement) {
					forwardIndex++;
					reverseIndex--;
				} else {
					if (recordedIndex == null) {
						
						char firstElement = inputStringCharArr[0];

						for (int charArrIndex = 0; charArrIndex < forwardIndex && isIdenticalSituation; charArrIndex++) {
							if (inputStringCharArr[charArrIndex] != firstElement)
								isIdenticalSituation = false;
						}

						for (int charArrIndex = (inputStringCharArr.length - 1); charArrIndex >= reverseIndex; charArrIndex--)
							backupForIdenticalFail.append(inputStringCharArr[charArrIndex]);
						
						if (isIdenticalSituation && (inputStringCharArr[reverseIndex] == firstElement)) {
							stringToAppend.append(firstElement);
						} else {
							isIdenticalSituation = false;
							stringToAppend.append(backupForIdenticalFail);
							forwardIndex = 0;
						}
					} else {
						if (isIdenticalSituation) {
							stringToAppend.setLength(0);
							stringToAppend.append(backupForIdenticalFail);
							isIdenticalSituation = false;
						}
						
						for (int charArrIndex = recordedIndex - 1; charArrIndex >= reverseIndex; charArrIndex--)
							stringToAppend.append(inputStringCharArr[charArrIndex]);
						
						forwardIndex = 0;
					}
					recordedIndex = reverseIndex;
					reverseIndex--;
				}
			}
		}
		stringToAppend.append(s);
		return stringToAppend.toString();
	}
	
}
