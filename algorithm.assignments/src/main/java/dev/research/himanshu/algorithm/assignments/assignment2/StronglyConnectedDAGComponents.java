package dev.research.himanshu.algorithm.assignments.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Strongly connected components of a directed graph. Implement the algorithm
 * for finding strongly connected components of a directed graph (see page 617
 * of Cormen et al, Introduction to algorithms, 3rd ed.). Run DFS on G and push
 * the nodes into a stack as they complete DFSVisit(). Find G^T, the graph
 * obtained by reversing all edges of G. Run DFS on G^T, but using the stack
 * order in DFS outer loop.
 * 
 * Each DSF tree in the second DFS is a strongly connected component.
 * 
 * @author G31
 *
 */
public class StronglyConnectedDAGComponents {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	/**
	 * Each node is marked with a component number.
	 * 
	 * @param graph
	 * @return the number of strongly connected components of G
	 */
	public static int stronglyConnectedComponents(Graph graph) {
		resetGraph(graph);

		/*
		 * Step 1 : Perform a DFS of G and number the vertices in order of
		 * completion of the recursive calls.
		 */
		Stack<Vertex> topologicalOrderStack = toplogicalOrder(graph);

		resetGraph(graph);

		/*
		 * Step 2 : Perform a DFS on Reverse stack starting the search from the
		 * highest numbered vertex according to the numbering assigned at step
		 * 1.
		 */
		int resultingConnectedComponents = 0;

		while (topologicalOrderStack.size() != 0) {
			Vertex topologicalOrderedVertex = topologicalOrderStack.pop();
			if (!topologicalOrderedVertex.seen) {
				List<Vertex> numberOfComponents = new ArrayList<>();

				performDFS(numberOfComponents, topologicalOrderedVertex, true, ++resultingConnectedComponents);
			}
		}

		return resultingConnectedComponents;
	}

	/*
	 * Algorithm : Run DFS on graph and push nodes to a stack in the order in
	 * which they finish. Write code without using global variables.
	 */
	public static Stack<Vertex> toplogicalOrder(Graph graph) {
		Stack<Vertex> topologicalOrderStack = new Stack<>();
		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null && !graphVertex.seen)
				performDFS(topologicalOrderStack, graphVertex, false, 0);
		}

		if (topologicalOrderStack.size() != graph.verts.size() - 1)
			return null;
		else
			return topologicalOrderStack;
	}

	private static void performDFS(List<Vertex> topologicalOrderList, Vertex vertex, boolean isReversedAdj, int componentNumber) {

		vertex.seen = true;
		vertex.distance = componentNumber;
		
		/*
		 * for step 1, we would be needing regular adjacency list and for the
		 * step 2, we would be needing reverse adjacency list.
		 */
		List<Edge> adjacencyEdgeList = (!isReversedAdj ? vertex.Adj : vertex.revAdj);

		/* DFS logic */
		for (Iterator<Edge> edgesIterator = adjacencyEdgeList.iterator(); edgesIterator.hasNext();) {
			Edge connectingEdge = edgesIterator.next();
			Vertex otherVertex = connectingEdge.otherEnd(vertex);

			List<Edge> otherAdjacencyEdgeList = (!isReversedAdj ? otherVertex.Adj : otherVertex.revAdj);
			if (!otherVertex.seen) {
				if (otherAdjacencyEdgeList.size() == 0) {
					otherVertex.parent = vertex;
					otherVertex.seen = true;
					otherVertex.distance = componentNumber;
					topologicalOrderList.add(otherVertex);
				} else {
					performDFS(topologicalOrderList, otherVertex, isReversedAdj, componentNumber);
				}
			}
		}
		
		topologicalOrderList.add(vertex);
	}

	/* resetting the graph for fresh usage */
	private static void resetGraph(Graph graph) {
		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null) {
				graphVertex.seen = false;
				graphVertex.distance = 0;
			}
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	/**
	 * Please run the main method as :
	 * 
	 * 	10 20 <graph-detail-file>
	 * 
	 * where 
	 * 		1st argument (args[0]) : Number of nodes in graph
	 * 		2nd argument (args[1]) : Number of edges in graph
	 * 		3rd argument (args[2]) : File location containing graph edges details 
	 * 					
	 * 			(Containing entries with the format of : Start-node	End-node	weight)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Please provide 3 Inputs :");
			System.out.println("a) number of nodes, which will going to be in the tree. ");
			System.out.println("b) number of edges, which will going to be in the tree. ");
			System.out.println("c) Graph file (connection data amoing edges vertices)");
			System.exit(-1);
		}

		try {

			// testing Scenario
			testAlgorithm(args);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testAlgorithm(String[] args) throws FileNotFoundException {
		int nodes = Integer.valueOf(args[0]);
		String connectionFileLocation = args[2];

		if (!connectionFileLocation.isEmpty()) {
			Graph graph = new Graph(nodes);

			BufferedReader fileReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(connectionFileLocation)), Charset.defaultCharset()));

			String line = null;

			try {
				while ((line = fileReader.readLine()) != null) {
					if (line.startsWith("#") || line.isEmpty())
						continue;

					String[] edgesInformation = line.split("\\t");
					graph.addDirectedEdge(Integer.valueOf(edgesInformation[0]), Integer.valueOf(edgesInformation[1]),
							Integer.valueOf(edgesInformation[2]));
				}

				timer();
				int connectedComponents = stronglyConnectedComponents(graph);
				timer();
				
				System.out.println(" Detected connected components : " + connectedComponents);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Graph is empty, as no connection file location provided !");
		}
	}

}
