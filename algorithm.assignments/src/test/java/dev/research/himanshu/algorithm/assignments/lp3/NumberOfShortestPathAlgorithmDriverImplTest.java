package dev.research.himanshu.algorithm.assignments.lp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import junit.framework.TestCase;

public class NumberOfShortestPathAlgorithmDriverImplTest extends TestCase {

	private String smallDataBaseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp3/lp3-data/lp3-l2/";
	private String smallNegativeCycleDataBaseDirectory = System.getProperty("user.dir") + "/src/main/resources/lp3/lp3-data/lp3-no/";

	public void testSmallDataAlgorithms() throws Exception {
		Integer sum = null;

		sum = testCaseBuilder(smallDataBaseDirectory + "1.txt");
		assertNotNull(sum);
		assertEquals(17, sum.intValue());

		sum = testCaseBuilder(smallDataBaseDirectory + "5.txt");
		assertNotNull(sum);
		assertEquals(7, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "6.txt");
		assertNotNull(sum);
		assertEquals(7, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "2.txt");
		assertNotNull(sum);
		assertEquals(819, sum.intValue());

		sum = testCaseBuilder(smallDataBaseDirectory + "3.txt");
		assertNotNull(sum);
		assertEquals(4550, sum.intValue());

		sum = testCaseBuilder(smallDataBaseDirectory + "4.txt");
		assertNotNull(sum);
		assertEquals(348073, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "101a.txt");
		assertNotNull(sum);
		assertEquals(1512318, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "101b.txt");
		assertNotNull(sum);
		assertEquals(1151977, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "101c.txt");
		assertNotNull(sum);
		assertEquals(104838, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "101d.txt");
		assertNotNull(sum);
		assertEquals(3384867, sum.intValue());

		sum = testCaseBuilder(smallDataBaseDirectory + "101e.txt");
		assertNotNull(sum);
		assertEquals(1808929, sum.intValue());
		
		sum = testCaseBuilder(smallDataBaseDirectory + "channel-20-3.txt");
		assertNotNull(sum);
		assertEquals(16402, sum.intValue());

		sum = testCaseBuilder(smallDataBaseDirectory + "channel-50-2.txt");
		assertNotNull(sum);
		assertEquals(3145727, sum.intValue());

		sum = testCaseBuilder(smallDataBaseDirectory + "rchannel-100-2-3.txt");
		assertNotNull(sum);
		assertEquals(13, sum.intValue());
	}
	
	public void testSmallNegativeWeightCycleDataAlgorithms() throws Exception {
		Integer sum = null;

		sum = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in5.txt");
		assertNull(sum);
		
		sum = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in6.txt");
		assertNull(sum);
		
		sum = testCaseBuilder(smallDataBaseDirectory + "lp3-l1-in7.txt");
		assertNull(sum);
	}
	
	public void testSmallZeroWeightCycleDataAlgorithms() throws Exception {
		Integer sum = null;

		sum = testCaseBuilder(smallNegativeCycleDataBaseDirectory + "no30.txt");
		assertNull(sum);
		
		sum = testCaseBuilder(smallNegativeCycleDataBaseDirectory + "no-10k.txt");
		assertNull(sum);
	}
	
	private Integer testCaseBuilder(String filepath) {
		Integer sum = null;
		try {
			Scanner in = new Scanner(new File(filepath));
			
			Graph graph = Graph.readGraph(in, true);	
			
			AbstractGraphShortestPath algorithm = ShortestPathAlgorithmSelectionFactory.selectAlgorithm(graph, true);
			algorithm.arrangeShortestPath();
			
			if (algorithm.hasNegativeCycle()) {
				System.out.println("Unable to solve problem. Graph has a negative cycle.");
				
				algorithm = ShortestPathAlgorithmType.BFO1.getAlgorithm();
				algorithm.setInnerGraph(graph);
				algorithm.arrangeShortestPath();
				printStackOrder(algorithm.getNegativeCycleStack());
				return null;
			}
			
			algorithm = ShortestPathAlgorithmType.CPA.getAlgorithm(algorithm.getAlgorithmType().getAnalyzer());
			algorithm.setInnerGraph(graph);
			algorithm.optimizeGraph();
			
			// check for zero length cycle
			GraphTopologyAnalyzer analyzer = GraphTopologyAnalyzer.analyze(graph, true);
			
			if (analyzer.isAcyclic()) {
				algorithm.arrangeShortestPath();
				sum = printVerticesShortPaths(algorithm);
			} else {
				System.out.println("Non-positive cycle in graph. DAC is not applicable");
				printStackOrder(analyzer.getCycle(), algorithm.getInnerGraph());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	public int printVerticesShortPaths(AbstractGraphShortestPath algorithm) {
		StringBuffer sb = new StringBuffer();
		
		int sum = 0;
		for (Vertex vertex : algorithm.getInnerGraph()) {
			
			if (algorithm.getInnerGraph().numNodes <= 100)
				sb.append(vertex + " " + (vertex.distance == null ? "INF" : vertex.distance) + " " + (vertex.shortestPathCount == null ? "0" : vertex.shortestPathCount) + "\n");
			
			sum += (vertex.shortestPathCount == null ? 0 : vertex.shortestPathCount); 
		}
		
		System.out.println(sum);
		System.out.println(sb.toString());
		
		return sum;
	}
	
	public void printVerticesParent(AbstractGraphShortestPath algorithm) {
		StringBuffer sb = new StringBuffer();
		
		for (Vertex vertex : algorithm.getInnerGraph())
			sb.append(vertex + " " + (vertex.parent == null ? "-" : vertex.parent) + " " + (vertex.distance == null ? "INF" : vertex.distance) + "\n");
		
		System.out.println(sb.toString());
	}
	
	public static void printStackOrderLinear(Stack<Vertex> order) {
		int index = order.size() - 1;
		
		for (; index > 0; index --)
			System.out.print(order.get(index) + " -> ");
		
		if (order.size() > 0) 
			System.out.println(order.get(index));
	}
	
	public static void printStackOrder(Stack<Edge> order) {
		int index = order.size() - 1;
		
		for (; index >= 0; index --)
			System.out.print(order.get(index).From + " -> ");
		
		if (order.size() > 0) 
			System.out.println(order.get(index + 1).To);
		
		System.out.println();
	}
	
	public static void printStackOrder(Stack<Vertex> order, Graph graph) {
		int index = order.size() - 1;
		
		Vertex vertex = graph.verts.get(order.get(index).name);
		index --;
		while (index >= 0) {
			for (Edge edge : vertex.Adj) {
				if (edge.otherEnd(vertex) == order.get(index)) {
					System.out.println(edge.From + " " + edge.To + " " + edge.Weight);
					vertex = edge.otherEnd(vertex);
					index --;
					break;
				}
			}
		}
	}

}
