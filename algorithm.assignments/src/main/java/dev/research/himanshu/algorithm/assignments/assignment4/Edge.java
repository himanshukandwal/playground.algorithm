package dev.research.himanshu.algorithm.assignments.assignment4;

/**
 * Class that represents an arc in a Graph
 *
 */
public class Edge {
	public Vertex From; // head vertex
	public Vertex To; // tail vertex
	public int Weight;// weight of the arc

	/**
	 * Default Constructor for Edge
	 */
	public Edge() {}
	
	/**
	 * Constructor for Edge
	 * 
	 * @param u
	 *            : Vertex - The head of the arc
	 * @param v
	 *            : Vertex - The tail of the arc
	 * @param w
	 *            : int - The weight associated with the arc
	 */
	Edge(Vertex u, Vertex v, int w) {
		From = u;
		To = v;
		Weight = w;
	}

	public Edge set(Vertex u, Vertex v, int w) {
		setFrom(u);
		setTo(v);
		setWeight(w);
		
		return this;
	}
	
	public void setFrom(Vertex from) {
		From = from;
	}
	
	public Vertex getFrom() {
		return From;
	}
	
	public void setTo(Vertex to) {
		To = to;
	}
	
	public Vertex getTo() {
		return To;
	}
	
	public int getWeight() {
		return Weight;
	}
	
	public void setWeight(int weight) {
		Weight = weight;
	}
	
	/**
	 * Method to find the other end end of the arc given a vertex reference
	 * 
	 * @param u
	 *            : Vertex
	 * @return
	 */
	public Vertex otherEnd(Vertex u) {
		// if the vertex u is the head of the arc, then return the tail else
		// return the head
		if (From == u) {
			return To;
		} else {
			return From;
		}
	}

	/**
	 * Method to represent the edge in the form (x,y) where x is the head of the
	 * arc and y is the tail of the arc
	 */
	public String toString() {
		return "(" + From + "," + To + ")";
	}
}