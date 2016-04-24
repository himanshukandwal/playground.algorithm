package dev.research.himanshu.algorithm.assignments.lp3;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

import junit.framework.TestCase;

/**
 * test class for validating various scenarios of topological ordering.
 * 
 * @author Heman
 * 
 */
public class GraphTopologyAnalyzerTest extends TestCase {

	private String testFilesFolder = System.getProperty("user.dir") + "/src/main/resources/lp3/cyclicity-scenarios/";
	
	public void testCase1() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-1.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph);

		assertTrue(orderer.isAcyclic());
		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(5, orderer.getTopologicalOrder().size());
	}

	public void testCase2() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-2.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph);
		
		assertFalse(orderer.isAcyclic());
	}

	public void testCase3() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-3.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertTrue(orderer.isAcyclic());
		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(5, orderer.getTopologicalOrder().size());
	}
	
	public void testCase4() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-4.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertTrue(orderer.isAcyclic());
		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(8, orderer.getTopologicalOrder().size());
	}

	public void testCase5() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-5.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
	}
	
	public void testCase6() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-6.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
	}
	
	public void testCase7() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-7.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertTrue(orderer.isAcyclic());
		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(14, orderer.getTopologicalOrder().size());
	}
	
	public void testCase8() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-8.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
	}
	
	public void testCase9() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-9.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
	}
	
	public void testCase10() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-10.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertTrue(orderer.isAcyclic());
	}
	
	public void testCase11() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-11.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
	}
	
	public void testCase12() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-12.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
	}
	
	public void testCase13() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-13.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph);

		assertTrue(orderer.isAcyclic());
		printStackOrder(orderer.getTopologicalOrder());
	}
	
	public void testCase14() throws Exception {
		Graph graph = Graph.readGraph(new Scanner(new File(testFilesFolder + "inputfile-14.txt")), true);
		GraphTopologyAnalyzer orderer = GraphTopologyAnalyzer.analyze(graph);

		assertTrue(orderer.isAcyclic());
		printStackOrder(orderer.getTopologicalOrder());
	}
	
	private void printStackOrder(Stack<Vertex> order) {
		printStackOrder(null, order);
	}
	
	private void printStackOrder(String padding, Stack<Vertex> order) {
		int index = order.size() - 1;
		
		System.out.print((padding == null ? "" : padding));
		for (; index > 0; index --)
			System.out.print(order.get(index) + " -> ");
		
		if (order.size() > 0) 
			System.out.println(order.get(index));
	}

}
