package dev.research.himanshu.algorithm.assignments.assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Diameter of a tree. In this problem, you are given an unrooted tree as input.
 * Since the tree may not be a binary tree, we will represent it with an
 * adjacency list (i.e., it is a graph that happens to be a tree). The following
 * algorithm can be used to find its diameter:
 * 
 * 1. Run BFS from an arbitrary node as root. 
 * 2. Select a node Z with maximum distance from the first BFS. 
 * 3. Run a second BFS with Z as root. 
 * 4. The diameter of the tree is the maximum distance to any node from Z in BFS 2.
 * 
 * @author G31
 *
 */
public class TreeDiameter {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	public static int diameter(Graph graph) {
		Queue<Vertex> currentLevelQueue = new LinkedList<Vertex>();
		Random randomVertexSelector = new Random();
		
		// clearing the distance information
		for (Vertex vertex : graph.verts) {
			if (vertex != null)
				vertex.distance = 0;
		}
		
		Vertex randomVertex = graph.verts.get(randomVertexSelector.nextInt(graph.verts.size() -1) + 1);
		
		System.out.println(" -- [BFS 1] starting BFS with (Random node) [" + randomVertex + "]");
		
		for (Edge edge : randomVertex.Adj)
			currentLevelQueue.add(edge.otherEnd(randomVertex));
		
		Vertex lastProcessedVertex = performBFS(currentLevelQueue, 1, false);
		if (lastProcessedVertex == null)
			lastProcessedVertex = randomVertex;
		
		System.out.println(" -- performed [BFS 1], distance found : " + lastProcessedVertex.distance + " for node [" + lastProcessedVertex + "]");
		
		// clearing the distance information again to recompute
		for (Vertex vertex : graph.verts) {
			if (vertex != null)
				vertex.distance = 0;
		}
		
		for (Edge edge : lastProcessedVertex.revAdj)
			currentLevelQueue.add(edge.otherEnd(lastProcessedVertex));
		
		System.out.println("\n -- performing [BFS 2], starting with node [" + lastProcessedVertex + "]");
		lastProcessedVertex = performBFS(currentLevelQueue, 1, true);
		
		if (lastProcessedVertex == null)
			lastProcessedVertex = randomVertex;
		
		System.out.println(" -- performed [BFS 2], distance found : " + lastProcessedVertex.distance + " for node [" + lastProcessedVertex + "]");
		return lastProcessedVertex.distance;
	}

	public static Vertex performBFS(Queue<Vertex> previousLevelQueue, int distance, boolean isReverse) {
		Vertex levelsLastProcessedVertex = null;
		Queue<Vertex> currentLevelQueue = new LinkedList<Vertex>();

		while (previousLevelQueue.size() != 0) {
			Vertex levelVertex = levelsLastProcessedVertex = previousLevelQueue.poll();
			levelVertex.distance = distance;

			for (Edge edge : (isReverse ? levelVertex.revAdj : levelVertex.Adj))
				currentLevelQueue.add(edge.otherEnd(levelVertex));
		}

		if (levelsLastProcessedVertex != null) {
			Vertex successorsLastProcessedVertex = null;
			
			levelsLastProcessedVertex = 
					((successorsLastProcessedVertex = performBFS(currentLevelQueue, distance + 1, isReverse)) == null 
						? levelsLastProcessedVertex : successorsLastProcessedVertex);
		}
		
		return levelsLastProcessedVertex;
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
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
				System.out.println(diameter(graph));
				timer();
				
				} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Graph is empty, as no connection file location provided !");
		}
	}
}
