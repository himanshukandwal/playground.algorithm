package dev.research.himanshu.algorithm.assignments.assignment3;

/**
 * Class to represent a vertex of a graph
 * 
 *
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Vertex implements Index, Comparator<Vertex>{
	
	public int name; 				// name of the vertex
	public int index;
	public boolean seen;		 	// flag to check if the vertex has already been visited
	public Vertex parent; 			// parent of the vertex
	public int distance; 			// distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; 	// adjacency list; use LinkedList or ArrayList

	/**
	 * Constructor for the vertex
	 * 
	 * @param n : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); /* only for directed graphs */
	}

	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}

	@Override
	public int compare(Vertex vertex1, Vertex vertex2) {
		return (vertex1.distance < vertex2.distance) ? -1 : ((vertex1.distance == vertex2.distance) ? 0 : 1);
	}
	
	@Override
	public void putIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}
}
