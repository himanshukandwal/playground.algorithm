package dev.research.himanshu.algorithm.assignments.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author G31
 */
public class MST {
	static final int Infinity = Integer.MAX_VALUE;
	
	static final Comparator<Edge> edgeComparator = new Comparator<Edge>() {
		@Override
		public int compare(Edge edge1, Edge edge2) {
			return (edge1.Weight < edge2.Weight) ? -1 : ((edge1.Weight == edge2.Weight) ? 0 : 1);
		}
	};
	
	static final Comparator<Vertex> verticiesComparator = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex vertex1, Vertex vertex2) {
			if (vertex1 == null && vertex2 != null)
				return -1;
			else if (vertex1 != null && vertex2 == null)
				return 1;
			else if (vertex1 == null && vertex2 == null)
				return 0;
			else
				return (vertex1.distance < vertex2.distance) ? -1 : ((vertex1.distance == vertex2.distance) ? 0 : 1);
		}
	};
	
	/**
	 * Prims algorithm using Binary heap Priority Queue.
	 * 
	 */
	static int PrimMST(Graph graph) {
		int totaledges = 0;
		int wmst = 0;
		Vertex sourceVertex = graph.verts.get(1);
		
		// calculate the total count of unique edges, needed for the size of the Priority Queue.
		for (Vertex vertex : graph.verts) {
			if (vertex != null && !vertex.seen) {
				for (Edge edge : vertex.Adj)
					if (!edge.otherEnd(vertex).seen)
						totaledges ++;
				vertex.seen = true;
			}
		}
		
		// reset the vertices to false
		for (Vertex vertex : graph.verts)
			if (vertex != null)
				vertex.seen = false;
		
		sourceVertex.seen = true;
		BinaryHeap<Edge> priorityQueue = new BinaryHeap<>(totaledges, edgeComparator);
		
		for (Edge edge : sourceVertex.Adj)
			if (!edge.otherEnd(sourceVertex).seen)
				priorityQueue.add(edge);
		
		Edge activeEdge = null;
		while ((activeEdge = priorityQueue.deleteMin()) != null) {
			if (activeEdge.From.seen && activeEdge.To.seen)
				continue;
			
			wmst += activeEdge.Weight;
			Vertex discoveredVertex = (activeEdge.From.seen ? activeEdge.To : activeEdge.From);
			discoveredVertex.seen = true;
			
			for (Edge edge : discoveredVertex.Adj)
				if (!edge.otherEnd(discoveredVertex).seen)
					priorityQueue.add(edge);
		}
		
		System.out.println("The total cost (MST) of the graph using Binary heap is : " + wmst);
		return wmst;
	}
	
	/**
	 * Prims algorithm using Indexed heap Priority Queue.
	 * 
	 */
	static int PrimMSTIndexed(Graph graph) {
		int wmst = 0;
	
		IndexedHeap<Vertex> indexedPriorityQueue = new IndexedHeap<Vertex>(graph.verts.size(), verticiesComparator);
		
		Vertex sourceVertex = graph.verts.get(1);
		
		for (int index = 0; index < graph.verts.size(); index ++) {
			Vertex vertex = graph.verts.get(index);
			
			if (vertex != null) {
				if (vertex == sourceVertex)
					vertex.distance = 0;
				else
					vertex.distance = Infinity;
				vertex.seen = false;
				vertex.parent = null;
				indexedPriorityQueue.add(vertex);
			}
		}
		
		while (indexedPriorityQueue.peek() != null) {
			Vertex vertex = indexedPriorityQueue.deleteMin();
			vertex.seen = true;
			
			wmst += vertex.distance;
			
			for (Edge edge : vertex.Adj) {
				Vertex otherVertex = edge.otherEnd(vertex);
				
				if (!otherVertex.seen && edge.Weight < otherVertex.distance) {
					otherVertex.distance = edge.Weight;
					otherVertex.parent = vertex;
					indexedPriorityQueue.decreaseKey(otherVertex);
				}
			}
		}
		
		System.out.println("The total cost (MST) of the graph using Indexed heap is : " + wmst);
		return wmst;
	}
	
	/**
	 * main method 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// execute PRIMS (non - indexed)
		try {
			Graph graph = null;
			if (args.length > 1) {
				int nodes = Integer.valueOf(args[0]);
				String filelocation = args[1];
				
				File graphfile = new File(filelocation);

				if (graphfile.exists()) {
					BufferedReader graphfilereader = new BufferedReader(new InputStreamReader(new FileInputStream(graphfile)));
					String line = null;
					graph = new Graph(nodes);
					
					// reading graph data
					while ((line = graphfilereader.readLine()) != null) {
						if (line.startsWith("#") || line.isEmpty())
							continue;
						
						String[] edge = line.split("\t");
						graph.addEdge(Integer.valueOf(edge[0]), Integer.valueOf(edge[1]), Integer.valueOf(edge[2]));
					}
					graphfilereader.close();
					
					System.out.println(PrimMSTIndexed(graph));
					return;
				}
			}
			
			Scanner in = new Scanner(System.in);
			graph = Graph.readGraph(in, false);
			System.out.println(PrimMST(graph));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
