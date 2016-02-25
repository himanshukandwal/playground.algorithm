package dev.research.himanshu.algorithm.assignments.assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class to represent a graph
 */
public class Graph<V extends Vertex, E extends Edge> implements Iterable<V> {

	public V[] verts; // array of vertices
	public int numNodes; // number of vertices in the graph

	private final Class<V> vertexClazz;
	private final Class<E> edgeClazz;

	/**
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	public Graph(int size, Class<V> vertexClazz, Class<E> edgeClazz) {
		this.numNodes = size;
		this.vertexClazz = vertexClazz;
		this.edgeClazz = edgeClazz;
		
		verts = (V[]) Array.newInstance(this.vertexClazz, size + 1);

		// create an array of Vertex objects
		for (int i = 1; i <= size; i++) {
			try {
				verts[i] = (V) ((Vertex) this.vertexClazz.newInstance()).setName(i);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to add an edge to the graph
	 * 
	 * @param a
	 *            : int - one end of edge
	 * @param b
	 *            : int - other end of edge
	 * @param weight
	 *            : int - the weight of the edge
	 */
	void addEdge(int a, int b, int weight) {
		try {
			V u = verts[a];
			V v = verts[b];
			E e = (E) ((Edge) edgeClazz.newInstance()).set(u, v, weight);

			((Vertex) u).Adj.add(e);
			((Vertex) v).Adj.add(e);
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Method to add an arc (directed edge) to the graph
	 * 
	 * @param a
	 *            : int - the head of the arc
	 * @param b
	 *            : int - the tail of the arc
	 * @param weight
	 *            : int - the weight of the arc
	 */
	void addDirectedEdge(int a, int b, int weight) {
		try {
			V head = verts[a];
			V tail = verts[b];
			E e = (E) ((Edge) edgeClazz.newInstance()).set(head, tail, weight);

			((Vertex) head).Adj.add(e);
			((Vertex) tail).Adj.add(e);
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Method to create an instance of VertexIterator
	 */
	public Iterator<V> iterator() {
		return new VertexIterator<V>();
	}

	/**
	 * A Custom Iterator Class for iterating through the vertices in a graph
	 * 
	 *
	 * @param <Vertex>
	 */
	private class VertexIterator<T extends Vertex> implements Iterator<T> {

		private int index;

		/**
		 * Constructor for VertexIterator
		 * 
		 * @param v
		 *            : Array of vertices
		 * @param n
		 *            : int - Size of the graph
		 */
		private VertexIterator() {
			index = 1; // Index 0 is not used. Skip it.
		}

		/**
		 * Method to check if there is any vertex left in the iteration
		 * Overrides the default hasNext() method of Iterator Class
		 */
		public boolean hasNext() {
			return (index + 1) < numNodes;
		}

		/**
		 * Method to return the next Vertex object in the iteration Overrides
		 * the default next() method of Iterator Class
		 */
		public T next() {
			return (T) verts [index++];
		}

		/**
		 * Throws an error if a vertex is attempted to be removed
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static <V extends Vertex, E extends Edge> Graph<V, E> readGraph(Class<V> vertexClazz, Class<E> edgeClazz, Scanner in, boolean directed) {
		// read the graph related parameters
		int n = in.nextInt(); // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph

		// create a graph instance
		Graph<V, E> g = new Graph<V, E>(n, vertexClazz, edgeClazz);

		for (int i = 0; i < m; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			if (directed) {
				g.addDirectedEdge(u, v, w);
			} else {
				g.addEdge(u, v, w);
			}
		}

		in.close();
		return g;
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

			Graph<EulerVertex, EulerEdge> g = Graph.readGraph(EulerVertex.class, EulerEdge.class, in, false);

			for (EulerVertex eulerVertex : g) {
				System.out.println(eulerVertex.name);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}