package dev.research.himanshu.algorithm.assignments.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Finding an odd-length cycle in a non-bipartite graph. Given a graph, find an
 * odd-length cycle and return it. If the graph is bipartite, return null.
 * 
 * Algorithm: 
 * 		Run BFS. 
 * 			If no edge of G connects two nodes at the same level,
 * 			then the graph is bipartite and has no odd-length cycle.
 * 
 * 			If two nodes u and v at the same level are connected by edge (u,v), then an
 * 			odd-length cycle can be found by combining the edge (u,v) with the paths from
 * 			u and v to their least common ancestor in the BFS tree.
 * 
 * If graph is not connected, this is repeated in each component.
 * 
 * Since, the graph is unrooted, so we are selecting any random node. 
 *
 * @author G31
 * 
 */
public class OddLengthCycle {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static List<Vertex> oddLengthCycle(Graph graph) {
		Queue<Vertex> currentLevelQueue = new LinkedList<Vertex>();
		Random randomVertexSelector = new Random();
		List<Vertex> cycle = null;
		
		resetGraph(graph);

		Vertex randomVertex = graph.verts.get (randomVertexSelector.nextInt(graph.verts.size() - 1) + 1);
		
		System.out.println(" Assuming the root as : " + randomVertex);
		for (Edge edge : randomVertex.Adj) {
			Vertex adjacentVertex = edge.otherEnd(randomVertex);
			adjacentVertex.parent = randomVertex;
			
			currentLevelQueue.add(adjacentVertex);
		}

		Edge oddLengthConnectingEdge = performBFS (currentLevelQueue);
		
		if (oddLengthConnectingEdge != null) {
			cycle =  new ArrayList<Vertex>();

			// determine the cycle.
			findCycle (cycle, oddLengthConnectingEdge.From, oddLengthConnectingEdge.To);
		}

		return cycle;
	}

	/**
	 * Function that create complete and orderly cycle.
	 * Example : 
	 * 			Let the cycle be like 
	 * 
	 * 				a -> b -> c -> d -> a
	 * 
	 * The output would be like :  abcd 
	 * 			
	 * @param cycle
	 * @param fromVertex
	 * @param toVertex
	 */
	private static void findCycle(List<Vertex> cycle, Vertex fromVertex, Vertex toVertex) {
		if (fromVertex == null || toVertex == null)
			return;
		
		cycle.add(fromVertex);
		
		if (fromVertex.parent == toVertex.parent)
			cycle.add (fromVertex.parent);
		else
			findCycle(cycle, fromVertex.parent, toVertex.parent);
		
		cycle.add(toVertex);
	}
	 
	/**
	 * A BFS program, which breaks the execution whenever there is a cycle identified amongst the same level nodes.
	 * 
	 * @param currentLevelQueue
	 * @return The edge which is the connecting edge between the same level nodes.
	 */
	public static Edge performBFS (Queue<Vertex> currentLevelQueue) {
		Edge oddLengthConnectingEdge = null;
		Queue<Vertex> nextLevelQueue = new LinkedList<Vertex>();

		List<Vertex> currentLevelProcessedList = new ArrayList<Vertex>();
		
		while (currentLevelQueue.size() != 0) {
			Vertex levelVertex = currentLevelQueue.poll();
			currentLevelProcessedList.add (levelVertex);

			for (Edge edge : levelVertex.Adj) {
				Vertex adjacentVertex = edge.otherEnd (levelVertex);
				
				if (! (currentLevelProcessedList.contains(adjacentVertex) || currentLevelQueue.contains(adjacentVertex))) {
					nextLevelQueue.add (edge.otherEnd(levelVertex));
				} else {
					oddLengthConnectingEdge = edge;
					break;
				}
				
				adjacentVertex.parent = levelVertex;
			}
			
			if (oddLengthConnectingEdge != null)
				break;
		}

		if (nextLevelQueue.size() > 0 && oddLengthConnectingEdge == null)
			oddLengthConnectingEdge = performBFS (nextLevelQueue);
		
		return oddLengthConnectingEdge;
	}
	
	/* resetting the graph for fresh usage */
	private static void resetGraph(Graph graph) {
		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null) {
				graphVertex.parent = null;
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
				List<Vertex> cycle = oddLengthCycle(graph);
				timer();
				
				if (cycle == null || cycle.size() == 0) {
					System.out.println(" No cycle detected !");
				} else {
					System.out.println(" Cycle detected : ");
					
					for (Vertex cycleVertex : cycle)
						System.out.print(cycleVertex + " ");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Graph is empty, as no connection file location provided !");
		}
	}

}
