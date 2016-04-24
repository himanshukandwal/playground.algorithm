package dev.research.himanshu.algorithm.assignments.assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dev.research.himanshu.algorithm.assignments.lp2.KruskalAlgorithm;
import junit.framework.TestCase;

public class Level1PrimsDriverTest extends TestCase {

	private String baseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp2/level1/";
	
	public void testPrims() throws Exception {
		runAlgorithms(baseDirectory + "input-prims1-1.txt");
		runAlgorithms(baseDirectory + "input-prims1-2.txt");
		runAlgorithms(baseDirectory + "input-prims1-3.txt");
		runAlgorithms(baseDirectory + "input-prims1-4.txt");
		runAlgorithms(baseDirectory + "input-prims1-5.txt");
	}
	
	private void runAlgorithms(String inputFileLocation) throws FileNotFoundException {
		Graph graph1 = Graph.readGraph(new Scanner(new File(inputFileLocation)), false);
		Graph graph2 = Graph.readGraph(new Scanner(new File(inputFileLocation)), false);
		Graph graph3 = Graph.readGraph(new Scanner(new File(inputFileLocation)), false);
		
		Prims1Algorithm algorithm1 = new Prims1Algorithm();
		algorithm1.PrimMST(graph1);
		
		Prims2Algorithm algorithm2 = new Prims2Algorithm();
		algorithm2.PrimMST(graph2);
		
		
		assertEquals(algorithm1.getMinimumCost(), algorithm2.getMinimumCost());
	}
	
}
