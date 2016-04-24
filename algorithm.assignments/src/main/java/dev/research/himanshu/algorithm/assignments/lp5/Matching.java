package dev.research.himanshu.algorithm.assignments.lp5;

/**
 * a class depicting the behavior of matching.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 */
public class Matching {
	
	private int id;					// matching clas id.
	private Vertex startingNode;	// starting point of the matching.
	private Vertex tailNode;		// last attached point of the matching.
	private int weight;				// current size of the matching.
	private boolean isAugmenting; 	// flag to hold information whether this matching is augmenting or not.
	private boolean active;       	// flag to hold information whether Matching is currently actively building or not.
	
	/**
	 * constructor.
	 * 
	 * @param startingNode
	 */
	public Matching (Vertex startingNode) {		
		this.active = (startingNode.parent == null);
		this.startingNode = startingNode;
		this.tailNode = startingNode;
		this.id = startingNode.name;
	}

	/**
	 * 'id' getter method
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 'startingNode' getter method
	 * 
	 * @return
	 */
	public Vertex getStartingNode() {
		return startingNode;
	}

	/**
	 * 'tailNode' getter method
	 * 
	 * @return
	 */
	public Vertex getTailNode() {
		return tailNode;
	}

	/**
	 * 'length' getter method
	 * 
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * 'active' setter method
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * 'active' getter method
	 * 
	 * @return
	 */
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
	
	/**
	 * method to add a vertex to the matching. 
	 * 
	 * @param vertex
	 * @return
	 */
	public boolean addMate(Vertex vertex) {
		// precautionary check : the incoming node should have a valid layer value.
		if (vertex.layer == null || (vertex.layer != null && vertex.layer == tailNode.layer))
			return false;
		
		tailNode.mate = vertex;
		vertex.parent = tailNode;
		
		System.out.println("adding mate of : " + tailNode + " -> " + vertex);
		
		// make edge 'matching' if the connection is getting made from inner node to outer node.
		if (tailNode.layer.isInner()) {
			for (Edge edge : tailNode.Adj) {
				if (edge.otherEnd(tailNode) == vertex) {
					edge.matched = true;			
					weight += edge.Weight;			// increment weight of the matching.
					break;
				}
			}
			
			isAugmenting = true;					// matching augmenting, hence accepting inner nodes at the moment.
		} else
			isAugmenting = false;					// case : addition of an inner node, matching not augmenting at the moment.
		
		tailNode = vertex;							// update the tail node.
		return true;
	}
	
	/**
	 * method to merge two matchings.
	 * 
	 * @param withMatching
	 * @return
	 */
	public boolean merge (Matching withMatching) {
		Edge connectingEdge = null;
		
		// check to ensure that we can append the current matching with the 'withMatching' matching. 
		for (Edge edge : tailNode.Adj) {
			if (edge.otherEnd(tailNode) == withMatching.startingNode) {
				connectingEdge = edge;
				break;
			}
		}
		
		// successful : proceed with the merge.
		if (connectingEdge != null) {
			if (!isAugmenting)
				weight += connectingEdge.Weight;
			
			weight += withMatching.weight;
			
			withMatching.startingNode.parent = tailNode;
			tailNode = withMatching.tailNode;
			isAugmenting = withMatching.isAugmenting;
		}
		
		return (connectingEdge == null ? false : true);
	}
	
	@Override
	public String toString() {
		return "[" + id + " : " + weight + "]";
	}
	
	/**
	 * method to print the matching sequence.
	 */
	public void print() {
		Vertex traversingNode = startingNode;
		
		StringBuffer buffer = new StringBuffer();
		while (traversingNode != tailNode) {
			buffer.append(traversingNode.name).append(" ");
			
			if (traversingNode.layer.isInner())
				buffer.append("\n");
			
			traversingNode = traversingNode.mate;
		}
		
		System.out.println(buffer.toString());
	}
	
	/**
	 * function to make the current matching augmenting by reversing the layering information.
	 
	public void makeAugmenting() {
		Vertex vertex = tailNode;
		while (vertex != null) {
			vertex.layer = vertex.layer.other();
			vertex = vertex.parent;
		}
	}
	*/
	
}
