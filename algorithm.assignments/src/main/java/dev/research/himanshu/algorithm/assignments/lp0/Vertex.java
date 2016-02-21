package dev.research.himanshu.algorithm.assignments.lp0;

/**
 * Class to represent a vertex of a graph
 * 
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Vertex {
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or
									// ArrayList
<<<<<<< HEAD
	public List<Edge> processedEdges;
	
=======
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); /* only for directed graphs */
<<<<<<< HEAD
		processedEdges = new ArrayList<Edge>();
=======
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
	}
	
	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
}