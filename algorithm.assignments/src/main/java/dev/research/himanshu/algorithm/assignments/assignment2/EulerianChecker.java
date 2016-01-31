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
 * A graph G is called Eulerian if it is connected and the degree of
 * every vertex is an even number.  
 * 
 * It is known that such graphs have a cycle (not simple) that goes through 
 * every edge of the graph exactly once.  A connected graph that has exactly 
 * 2 vertices of odd degree has an Eulerian path.  
 * 
 * Write a function that outputs one of the messages that applies to the 
 * given graph.
 * 
 * @author G31
 *
 */
public class EulerianChecker {
	
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;

	/**
	 * Possible outputs :
	 * 		Graph is Eulerian.
	 * 		Graph has an Eulerian Path between vertices ?? and ??.
	 * 		Graph is not connected.
	 * 		Graph is not Eulerian.  It has ?? vertices of odd degree.
	 * 
	 * @param graph
	 */
	public static void testEulerian(Graph graph) { 
		resetGraph(graph);
		
		/* case 1 : check whether graph is connected or not for connectivity */
		if (checkGraphConnectivity (graph)) { 
			resetGraph(graph);
			
			/* case 2 : check for vertices degree */
			List<Vertex> oddDegreeVertices = checkVerticesWithOddDegree(graph);
			if (oddDegreeVertices.size() != 0 && oddDegreeVertices.size() != 2) {
				System.out.println("Graph is not Eulerian.  It has " + oddDegreeVertices.size() + " vertices of odd degree.");
				return;
			}
			
			/* case 2 : check for eularian path condition*/
			if (oddDegreeVertices.size() == 2) {
				System.out.println("Graph has an Eulerian Path between vertices " + oddDegreeVertices.get(0) + " and " + oddDegreeVertices.get(1));
				return;
			}
			
			System.out.println("Graph is Eulerian");
		}
		
	}
	
	private static List<Vertex> checkVerticesWithOddDegree(Graph graph) {
		List<Vertex> oddDegreeVertices = new ArrayList<>();
		
		for (Vertex graphvertex : graph.verts) {
			if (graphvertex != null && (graphvertex.Adj.size() + graphvertex.revAdj.size()) % 2 != 0)
				oddDegreeVertices.add(graphvertex);
		}
		
		return oddDegreeVertices;
	}

	/*
	 *  Logic to check the connectivity of the graph.
	 */
	private static boolean checkGraphConnectivity(Graph graph) {
		Stack<Vertex> topologicalOrderStack = new Stack<>();
		Vertex entryVertex = null;
		
		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null && !graphVertex.seen) {
				entryVertex = graphVertex;
				break;
			}
		}
		
		performDFS(topologicalOrderStack, entryVertex);
		
		if (topologicalOrderStack.size() != graph.verts.size() -1) {
			System.out.println("Graph is not connected.");
			return false;
		}
		return true; 
	}
	
	private static void performDFS(Stack<Vertex> topologicalOrderStack, Vertex vertex) {
		vertex.seen = true;
		
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
					testEulerian(graph);
					timer();

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Graph is empty, as no connection file location provided !");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
