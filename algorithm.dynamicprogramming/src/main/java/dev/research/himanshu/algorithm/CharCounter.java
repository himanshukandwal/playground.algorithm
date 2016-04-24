package dev.research.himanshu.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class CharCounter {
	public static void main(String[] args) {
		int charPlace = 7;

		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(new File(System.getProperty("user.dir") + "/src/main/resources/test.txt")));

			String line;
			int linenum = 0;
			int charsDiscovered = 0;
			
			boolean found = false;
			while ((line = bufferedReader.readLine()) != null) {
				++linenum;
				StringTokenizer tokenizer = new StringTokenizer(line, " \r\n");
				
				while (tokenizer.hasMoreElements()) {
					String word = (String) tokenizer.nextElement();
					
					if ((charsDiscovered += word.length()) > charPlace) {
						System.out.println(linenum);
						found = true;
						break;
					}
				}
				if (found)
					break;
			}
			
			bufferedReader.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
}
