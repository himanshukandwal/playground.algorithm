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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Implement two algorithms for ordering the nodes of a DAG topologically. Both
 * algorithms should return null if the given graph is not a DAG.
 * 
 * @author G31
 *
 */
public class DAGTopologicalOrdering {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	/*
	 * Algorithm 1. Remove vertices with no incoming edges, one at a time, along
	 * with their incident edges, and add them to a list.
	 */
	public static List<Vertex> toplogicalOrder1(Graph graph) {
		List<Vertex> topologicalOrder = new ArrayList<>();

		Queue<Vertex> zeroDegreeVerticesProcessingQueue = new LinkedList<>();

		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null && graphVertex.revAdj.size() == 0) {
				zeroDegreeVerticesProcessingQueue.add(graphVertex);
			}
		}

		while (zeroDegreeVerticesProcessingQueue.size() != 0) {
			Vertex vertexToProcess = zeroDegreeVerticesProcessingQueue.poll();

			List<Edge> connectedEdges = vertexToProcess.Adj;
			for (Iterator<Edge> vertexEdgesIterator = connectedEdges.iterator(); vertexEdgesIterator.hasNext();) {
				Edge connectedEdge = vertexEdgesIterator.next();
				Vertex otherVertex = connectedEdge.otherEnd(vertexToProcess);
				otherVertex.revAdj.remove(connectedEdge);
				vertexEdgesIterator.remove();

				if (otherVertex.revAdj.size() == 0) {
					zeroDegreeVerticesProcessingQueue.add(otherVertex);
				}
			}
			topologicalOrder.add(vertexToProcess);
		}

		if (topologicalOrder.size() != graph.verts.size() - 1)
			return null;
		else
			return topologicalOrder;
	}

	/*
	 * Algorithm 2. Run DFS on graph and push nodes to a stack in the order in
	 * which they finish. Write code without using global variables.
	 */
	public static Stack<Vertex> toplogicalOrder2(Graph graph) {

		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null) {
				graphVertex.parent = null;
				graphVertex.seen = false;
			}
		}

		Stack<Vertex> topologicalOrderStack = new Stack<>();
		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null && !graphVertex.seen)
				performDFS(topologicalOrderStack, graphVertex);
		}

		for (Vertex vertex : topologicalOrderStack) {
			System.out.println(vertex);
		}

		if (topologicalOrderStack.size() != graph.verts.size() - 1)
			return null;
		else
			return topologicalOrderStack;
	}

	private static void performDFS(Stack<Vertex> topologicalOrderStack, Vertex vertex) {
		
		for (Iterator<Edge> edgesIterator = vertex.Adj.iterator(); edgesIterator.hasNext();) {
			Edge connectingEdge = edgesIterator.next();
			Vertex otherVertex = connectingEdge.otherEnd(vertex);
			
			if (!otherVertex.seen) {
				if (otherVertex.Adj.size() == 0) {
					otherVertex.parent = vertex;
					otherVertex.seen = true;
					
					topologicalOrderStack.add(otherVertex);
				} else {
					performDFS(topologicalOrderStack, otherVertex);
				}
			}
		}

		topologicalOrderStack.add(vertex);
		vertex.seen = true;
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
			
			// testing 1st Scenario
			testAlgorithm1(args);
			
			// testing 2nd Scenario
			testAlgorithm2(args);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testAlgorithm2(String[] args) throws FileNotFoundException {
		int nodes = Integer.valueOf(args[0]);
		String connectionFileLocation = args[2];

		if (!connectionFileLocation.isEmpty()) {
			Graph graph = new Graph(nodes);

			BufferedReader fileReader = new BufferedReader 
					(new InputStreamReader (new FileInputStream (new File(connectionFileLocation)), Charset.defaultCharset()));
			
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
				Stack<Vertex> topologicalOrder = toplogicalOrder2(graph);
				timer();

				if (topologicalOrder == null) {
					System.out.println("Not a DAG, no topological order found !");
				} else {
					System.out.println("Topological Order found for DAG : ");
					System.out.println(
							"----------------------------------------------------------------------------------");

					for (Vertex vertex : topologicalOrder)
						System.out.print(vertex + " ");

					System.out.println(
							"\n----------------------------------------------------------------------------------");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Graph is empty, as no connection file location provided !");
		}
	}

	private static void testAlgorithm1(String[] args) throws FileNotFoundException {
		int nodes = Integer.valueOf(args[0]);
		String connectionFileLocation = args[2];

		if (!connectionFileLocation.isEmpty()) {
			Graph graph = new Graph(nodes);

			BufferedReader fileReader = new BufferedReader 
					(new InputStreamReader (new FileInputStream (new File(connectionFileLocation)), Charset.defaultCharset()));
			
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
				List<Vertex> topologicalOrder = toplogicalOrder1(graph);
				timer();

				if (topologicalOrder == null) {
					System.out.println("Not a DAG, no topological order found !");
				} else {
					System.out.println("Topological Order found for DAG : ");
					System.out.println(
							"----------------------------------------------------------------------------------");

					for (Vertex vertex : topologicalOrder)
						System.out.print(vertex + " ");

					System.out.println(
							"\n----------------------------------------------------------------------------------");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Graph is empty, as no connection file location provided !");
		}
	}
}