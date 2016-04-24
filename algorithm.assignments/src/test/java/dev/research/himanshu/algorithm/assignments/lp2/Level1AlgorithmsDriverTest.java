package dev.research.himanshu.algorithm.assignments.lp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

public class Level1AlgorithmsDriverTest extends TestCase {

	private String baseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp2/level1/";
	
	public void testPrims() throws Exception {
		runAlgorithms(baseDirectory + "input-prims1-1.txt");
		runAlgorithms(baseDirectory + "input-prims1-2.txt");
		runAlgorithms(baseDirectory + "input-prims1-3.txt");
		runAlgorithms(baseDirectory + "input-prims1-4.txt");
		runAlgorithms(baseDirectory + "input-prims1-5.txt");
		runAlgorithms(baseDirectory + "input-prims1-6.txt");
		runAlgorithms(baseDirectory + "input-prims1-7.txt");
	}
	
	private void runAlgorithms(String inputFileLocation) throws FileNotFoundException {
		Graph graph1 = Graph.readGraph(new Scanner(new File(inputFileLocation)), false);
		Graph graph2 = Graph.readGraph(new Scanner(new File(inputFileLocation)), false);
		Graph graph3 = Graph.readGraph(new Scanner(new File(inputFileLocation)), false);
		
		Prims1Algorithm algorithm1 = new Prims1Algorithm();
		algorithm1.PrimMST(graph1);
		
		Prims2Algorithm algorithm2 = new Prims2Algorithm();
		algorithm2.PrimMST(graph2);
		
		KruskalAlgorithm algorithm3 = new KruskalAlgorithm();
		algorithm3.kruskalMST(graph3);
		
		Integer wmst = algorithm1.getMinimumCost();
		
		System.out.println(wmst);
		assertEquals(wmst, algorithm2.getMinimumCost());
		assertEquals(wmst, algorithm3.getMinimumCost());
	}
	
}
