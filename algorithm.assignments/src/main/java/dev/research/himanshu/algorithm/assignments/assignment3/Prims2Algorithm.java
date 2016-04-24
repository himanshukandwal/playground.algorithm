package dev.research.himanshu.algorithm.assignments.assignment3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * A prim1 algorithm implementation class. This class makes use of binary heap
 * for priority queue.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class Prims2Algorithm {

	private Integer minimumCost;
	private List<Edge> orderedEdgesList;

	private final Comparator<Vertex> vertexComparator = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex vertex1, Vertex vertex2) {
			int result;

			if (vertex1 != null && vertex2 != null) {
				if (vertex1.distance != null && vertex2.distance != null) {
					result = (vertex1.distance > vertex2.distance) ? 1
							: ((vertex1.distance == vertex2.distance) ? 0 : -1);
				} else {
					if (vertex1.distance == null && vertex2.distance != null)
						result = 1;
					else if (vertex1.distance != null && vertex2.distance == null)
						result = -1;
					else
						result = 0;
				}
			} else {
				if (vertex1 == null && vertex2 != null)
					result = 1;
				else if (vertex1 != null && vertex2 == null)
					result = -1;
				else
					result = 0;
			}

			return result;
		}
	};

	public Integer getMinimumCost() {
		return minimumCost;
	}

	public void setMinimumCost(Integer minimumCost) {
		this.minimumCost = minimumCost;
	}

	public List<Edge> getOrderedEdgesList() {
		if (orderedEdgesList == null)
			orderedEdgesList = new LinkedList<>();

		return orderedEdgesList;
	}

	/**
	 * Prims algorithm using Indexed heap Priority Queue.
	 * 
	 */
	public void PrimMST(Graph graph) {
		clear();

		int wmst = 0;
		Vertex sourceVertex = graph.verts.get(1);

		IndexedHeap<Vertex> priorityQueue = new IndexedHeap<Vertex>(graph.numNodes, vertexComparator);

		sourceVertex.seen = true;
		sourceVertex.distance = 0;

		for (Vertex vertex : graph)
			if (vertex != null)
				priorityQueue.add(vertex);

		while (priorityQueue.size() > 1) {
			Vertex u = priorityQueue.deleteMin();

			if (u == null)
				continue;

			u.seen = true;
			wmst += u.distance;
			
			for (Edge edge : u.Adj) {
				Vertex v = edge.otherEnd(u);
				if (!v.seen && (v.distance == null ? true : edge.Weight < v.distance)) {
					v.distance = edge.Weight;
					v.parent = u;
					priorityQueue.decreaseKey(v);
				}
			}
		}

		setMinimumCost(wmst);
	}

	private void clear() {
		minimumCost = null;
		getOrderedEdgesList().clear();
	}
	
}
