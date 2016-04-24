package dev.research.himanshu.algorithm.assignments.toolkit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BipartiteGenerator {

	public static void main(String[] args) {
//		buildFullyGeneratedGraph(10);
//		buildFullyGeneratedGraph(100);
//		buildFullyGeneratedGraph(1000);
//		buildFullyGeneratedGraph(10000);
	}
	
	public static void buildFullyGeneratedGraph(int nodes) {
		try {
			String filename = System.getProperty("user.dir") + "/src/main/resources/lp5/small/small-bipartite-" + nodes + ".txt";
			File file = File.createTempFile("small-bipartite", Integer.toString(nodes));
			
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			String header = nodes + " ";
			
			long edges = 0;
			for (int i = 1; i <= nodes; i++) {
				if ((i % 2) == 1) {
					String prefix = i + " ";
					for (int j = 1; j <= nodes; j++) {
						if ((j % 2) == 0) {
							edges ++;
							bufferedWriter.write(prefix + j + " " + 1 + "\n");
							bufferedWriter.flush();
						}
					}
				}
			}
			
			header += edges + "\n";
			System.out.println(header);
			bufferedWriter.close();
			
			bufferedWriter = new BufferedWriter(new FileWriter(new File(filename)));
			bufferedWriter.write(header);
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
			
			while ((line = bufferedReader.readLine()) != null)
				bufferedWriter.write(line + "\n");
			
			bufferedReader.close();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
