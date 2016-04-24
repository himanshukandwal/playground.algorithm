package dev.research.himanshu.algorithm;

import java.util.List;
import java.util.Stack;

import junit.framework.TestCase;

/**
 * test class for validating various scenarios of topological ordering.
 * 
 * @author Heman
 * 
 */
public class GraphCyclicityAnalyzerTest extends TestCase {

	private String testFilesFolder = System.getProperty("user.dir") + "/src/main/resources/lp3/cyclicity-scenarios/";
	
	public void testCase1() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-1.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph);

		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(5, orderer.getTopologicalOrder().size());
	}

	public void testCase2() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-2.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph);
		
		assertFalse(orderer.isAcyclic());
	}

	public void testCase3() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-3.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(5, orderer.getTopologicalOrder().size());
	}
	
	public void testCase4() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-4.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(8, orderer.getTopologicalOrder().size());
	}

	public void testCase5() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-5.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
		assertEquals(1, orderer.getCycles().size());
		
		System.out.println(" -- cycles detected : ");
		printListOrder("\t", orderer.getCycles());
	}
	
	public void testCase6() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-6.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
		assertEquals(2, orderer.getCycles().size());
		
		System.out.println(" -- cycles detected : ");
		printListOrder("\t", orderer.getCycles());
	}
	
	public void testCase7() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-7.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		printStackOrder(orderer.getTopologicalOrder());
		assertEquals(14, orderer.getTopologicalOrder().size());
	}
	
	public void testCase8() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-8.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
		assertEquals(2, orderer.getCycles().size());
		
		System.out.println(" -- cycles detected : ");
		printListOrder("\t", orderer.getCycles());
	}
	
	public void testCase9() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-9.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
		assertEquals(1, orderer.getCycles().size());
		
		System.out.println(" -- cycles detected : ");
		printListOrder("\t", orderer.getCycles());
	}
	
	public void testCase10() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-10.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertTrue(orderer.isAcyclic());
	}
	
	public void testCase11() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-11.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
		assertEquals(3, orderer.getCycles().size());
		
		System.out.println(" -- cycles detected : ");
		printListOrder("\t", orderer.getCycles());
	}
	
	public void testCase12() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-12.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph, 2);

		assertFalse(orderer.isAcyclic());
		assertEquals(5, orderer.getCycles().size());
		
		System.out.println(" -- cycles detected : ");
		printListOrder("\t", orderer.getCycles());
		
		Stack<Vertex> negativeCycle = ShortestPathAlgorithmSelectionFactory.getNegativeCycle(orderer.getCycles());
		assertNotNull(negativeCycle);
		
		System.out.println("\n\t -- negative cycle detected : ");
		printListOrder("\t\t", negativeCycle);
	}
	
	public void testCase13() throws Exception {
		Graph graph = GraphCyclicityAnalyzer.readGraph(testFilesFolder + "inputfile-13.txt");
		GraphCyclicityAnalyzer orderer = GraphCyclicityAnalyzer.analyze(graph);

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
	
	private void printListOrder(String padding, List<Stack<Vertex>> orders) {
		for (Stack<Vertex> order : orders)
			printListOrder(padding, order);
	}
	
	private void printListOrder(String padding, Stack<Vertex> order) {
		int index = 0;
		
		System.out.print((padding == null ? "" : padding));
		for (; index < order.size() - 1; index ++)
			System.out.print(order.get(index) + " -> ");
		
		if (order.size() > 0) 
			System.out.println(order.get(index));
	}
	
	
}
