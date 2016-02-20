package dev.research.himanshu.algorithm.assignments.lp0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HierholzerAlgorithmImpl {

	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	
	public static List<Edge> findEulerTour(Graph g) {
		
		/* checking Eular preconditions */
		
		int oddEdgeNodesCount = 0;
		Vertex eularPathStartNode = null;
		Vertex eularPathEndNode = null;
		List<Edge> totalEdges = new ArrayList<>();
		
		for (Vertex vertex : g.verts) {
			if (vertex != null) {
				if (vertex.Adj.size() % 2 != 0) {
					if (eularPathStartNode == null)
						eularPathStartNode = vertex;
					else
						eularPathEndNode = vertex;
					oddEdgeNodesCount ++;
				}
				
				for (Edge edge : vertex.Adj) {
					if (!edge.isUsed) {
						totalEdges.add(edge);
						edge.isUsed = true;
					}
				}
			}
		}
		
		if (oddEdgeNodesCount > 0 && oddEdgeNodesCount != 2) {
			System.out.println("Graph is not Eulerian");
			return null;
		}
		
		resetGraph(g);
		
		/* if the graph has no eular path then, considering eular tour and setting start node as : node 1 */
		eularPathStartNode = (eularPathStartNode == null ? g.verts.get(1) : eularPathStartNode);
		
		List<Edge> eularEdges = FindEulerTourInternal (g, totalEdges, eularPathStartNode, eularPathEndNode);
		
		/* print the eular tour/circuit */
		Vertex traversingVertex = eularPathStartNode;
		for (Iterator<Edge> eularEdgesIterator = eularEdges.iterator(); eularEdgesIterator.hasNext();) {
			Edge eularEdge = eularEdgesIterator.next();
			
			System.out.print(traversingVertex.name);
			traversingVertex = (eularEdge.From == traversingVertex ? eularEdge.To : eularEdge.From);
			System.out.print(" -> ");	
		}
		System.out.print((eularPathEndNode != null ? eularPathEndNode.name : eularPathStartNode.name));
		System.out.println();
		
		return eularEdges;
	}

	private static List<Edge> FindEulerTourInternal(Graph g, List<Edge> totalEdges, Vertex startNode, Vertex endNode) {
		List<Edge> eularEdges = new ArrayList<>();
		Vertex futureVertex = startNode;
		boolean foundway = true;

		while (foundway) {
			foundway = false;
			for (Edge edge : futureVertex.Adj) {
				if (!edge.isUsed) {
					edge.isUsed = true;
					futureVertex.seen = true;
					futureVertex = edge.otherEnd(futureVertex);
					eularEdges.add(edge);
					foundway = true;
					break;
				}
			}
		}

		/*
		 * If all the edges are not yet covered, find eular path again, however,
		 * this time starting with node which is wihtin the current Eular path
		 * and has an edge which is not yet covered yet.
		 * 
		 * Then patch all the results together.
		 */
		if (eularEdges.size() != totalEdges.size()) {
			for (Edge edge : totalEdges) {
				if (!edge.isUsed) {
					Vertex branchedStartNode = (edge.From.seen ? edge.From : edge.To);
					List<Edge> leftoverEdges = FindEulerTourInternal(g, totalEdges, branchedStartNode, endNode);
					
					int eularEdgeIndex = 0;
					
					/* choosing the start point from the present circuit, for the leftover circuit discovery ! */ 
					for (eularEdgeIndex = 0; eularEdgeIndex < eularEdges.size(); eularEdgeIndex ++) {
						if (eularEdges.get(eularEdgeIndex).From == branchedStartNode || eularEdges.get(eularEdgeIndex).To == branchedStartNode) {
							break;
						}
					}
					eularEdgeIndex ++;
					
					/* patching with the global Structure, Expansion of nodes */
					for (Edge leftoverEdge : leftoverEdges)
						eularEdges.add(eularEdgeIndex, leftoverEdge);
					
					break;
				}
			}
		}
		return eularEdges;
	}

	// resetting the state of the edges to original state : isUsed = false;
	private static void resetGraph(Graph g) {
		for (Vertex vertex : g.verts) {
			if (vertex != null) {
				for (Edge edge : vertex.Adj)
					edge.isUsed = false;
				vertex.seen = false;
			}
		}
	}

	public static boolean checkEularPrecondition(Graph g) {
		int oddEdgeNodesCount = 0;

		for (Vertex vertex : g.verts) {
			if (vertex.Adj.size() % 2 != 0)
				oddEdgeNodesCount++;
		}

		if (oddEdgeNodesCount != 2)
			return false;
		else
			return true;
	}

	public static boolean verifyTour(Graph g, List<Edge> tour, Vertex start) {

		/* check : tour must contain all the edges */
		if (tour.size() != g.numEdges) {
			return false;
		}
		
		/* check : tour must start from the start node */
		if (!(tour.get(0).From == start) && !(tour.get(0).To == start)) {
			return false;
		}
		
		Vertex traversingVertex = start;
		for (Edge edge : tour) {
			if (edge.From == traversingVertex || edge.To == traversingVertex) {
				traversingVertex = edge.otherEnd(traversingVertex);
			} else {
				return false;
			}
		}
		
		return true;
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

	public static void main(String[] args) {
		try {
			Scanner in;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
			} else {
				in = new Scanner(System.in);
			}

			// read undirected graph from stream "in"
			Graph g = Graph.readGraph(in, false);
			
			timer();
			findEulerTour (g);
			timer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
