package dev.research.himanshu.algorithm.assignments.lp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

public class ShortestPathAlgorithmsDriverImplTest extends TestCase {
	
	private String smallDataBaseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp3/lp3-data/lp3-l1/";
	private String bigDataBaseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp3/lp3-big/";
	private String generalDataBaseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp3/";
	
	public void testSmallDataAlgorithms() throws Exception {
		AbstractGraphShortestPath algorithm = null;
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in-k1.txt");
		assertEquals(ShortestPathAlgorithmType.BFS, algorithm.getAlgorithmType());
		assertEquals(185, calculateShortestPath(algorithm));

		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in-k2.txt");
		assertEquals(ShortestPathAlgorithmType.DIJ, algorithm.getAlgorithmType());
		assertEquals(2096, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in-k3.txt");
		assertEquals(ShortestPathAlgorithmType.BFO, algorithm.getAlgorithmType());
		assertEquals(1286, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in-k4.txt");
		assertEquals(ShortestPathAlgorithmType.DAG, algorithm.getAlgorithmType());
		assertEquals(-1998, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in1.txt");
		assertEquals(ShortestPathAlgorithmType.BFS, algorithm.getAlgorithmType());
		assertEquals(151, calculateShortestPath(algorithm));

		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in2.txt");
		assertEquals(ShortestPathAlgorithmType.DIJ, algorithm.getAlgorithmType());
		assertEquals(384, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in3.txt");
		assertEquals(ShortestPathAlgorithmType.BFO, algorithm.getAlgorithmType());
		assertEquals(296, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in4.txt");
		assertEquals(ShortestPathAlgorithmType.DAG, algorithm.getAlgorithmType());
		assertEquals(-255, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(generalDataBaseDirectory + "inputfile-1.txt");
		assertEquals(ShortestPathAlgorithmType.DIJ, algorithm.getAlgorithmType());
		assertEquals(20, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in5.txt");
		assertEquals(ShortestPathAlgorithmType.BFO, algorithm.getAlgorithmType());
		assertTrue(algorithm.hasNegativeCycle());
		
		algorithm = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in6.txt");
		assertEquals(ShortestPathAlgorithmType.BFO, algorithm.getAlgorithmType());
		assertTrue(algorithm.hasNegativeCycle());
		
	}
	
	public void testBigDataAlgorithms() throws Exception {
		AbstractGraphShortestPath algorithm = null;
		
		algorithm = testCaseBuilder(bigDataBaseDirectory + "lp3-l1-in-m1.txt");
		assertEquals(ShortestPathAlgorithmType.BFS, algorithm.getAlgorithmType());
		assertEquals(25400, calculateShortestPath(algorithm));

		algorithm = testCaseBuilder(bigDataBaseDirectory + "lp3-l1-in-m2.txt");
		assertEquals(ShortestPathAlgorithmType.DIJ, algorithm.getAlgorithmType());
		assertEquals(1475038, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(bigDataBaseDirectory + "lp3-l1-in-m3.txt");
		assertEquals(ShortestPathAlgorithmType.BFO, algorithm.getAlgorithmType());
		assertEquals(147639, calculateShortestPath(algorithm));
		
		algorithm = testCaseBuilder(bigDataBaseDirectory + "lp3-l1-in-m4.txt");
		assertEquals(ShortestPathAlgorithmType.DAG, algorithm.getAlgorithmType());
		assertEquals(13584, calculateShortestPath(algorithm));		
	}
	
	private AbstractGraphShortestPath testCaseBuilder(String filepath) {
		AbstractGraphShortestPath algorithm = null;
		try {
			Scanner in = new Scanner(new File(filepath));
			Graph graph = Graph.readGraph(in, true);
			
			algorithm = ShortestPathAlgorithmSelectionFactory.selectAlgorithm (graph);
			algorithm.arrangeShortestPath();
			
			if (algorithm.hasNegativeCycle())
				System.out.println("Unable to solve problem. Graph has a negative cycle.");
			
			if (graph.numNodes <= 100)
				printVertices(algorithm);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return algorithm;
	}
	
	private int calculateShortestPath(AbstractGraphShortestPath algorithm) {
		int sum = 0;
		
		for (Vertex vertex : algorithm.getInnerGraph())
			if (vertex.distance != null)
				sum += vertex.distance;

		return sum;
	}
	
	public void printVertices(AbstractGraphShortestPath algorithm) {
		StringBuffer sb = new StringBuffer();
		
		for (Vertex vertex : algorithm.getInnerGraph())
			sb.append(vertex + " " + (vertex.distance == null ? "INF" : vertex.distance) + " " + (vertex.parent == null ? "-" : vertex.parent) + "\n");
		
		System.out.println(sb.toString());
	}

}
