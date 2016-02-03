package dev.research.himanshu.algorithm;

import java.util.Stack;

public class PatternMatching {
	public static void main(String[] args) {
		System.out.println(main1());
	}
	
	public static String main1() {
		String value = "{}}";
		char[] charArr = value.toCharArray();
		
		Stack<Character> charStack = new Stack<>();
		
		for (int index = 0; index < charArr.length; index++) {
			char ch = charArr[index];
			
			if (index == 0) {
				if (ch == ']' || ch == ')' || ch == '}')
					return "NO";
				else 
					charStack.push(ch);
			} else {
				if (ch == ']' || ch == ')' || ch == '}') {
					if (ch == ']') {
						if (!charStack.isEmpty() && charStack.peek() == '[')
							charStack.pop();
						else 
							return "NO";
					} else if (ch == '}') {
						if (!charStack.isEmpty() && charStack.peek() == '{')
							charStack.pop();
						else
							return "NO";
					} else if (ch == ')') {
						if (!charStack.isEmpty() && charStack.peek() == '(')
							charStack.pop();
						else 
								return "NO";
					}
				} else {
					charStack.push(ch);
				}
			}	
		}
		
		if (!charStack.isEmpty())
			return "NO";
		else
			return "YES";
	}
	
}
