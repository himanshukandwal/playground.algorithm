package dev.research.himanshu.algorithm.assignments.longproject0;

import dev.research.himanshu.algorithm.assignments.longproject0.EularEdgeLinkedList.Node;

/**
 * A metamap file, that contains quick vertex-nodes entries
 * 
 * @author G31
 *
 */
public class VerticesNodesMetaMap {
	
	public int size;
	public Object[][] verticesEdgeArray;
	public int entriesCount;
	
	public VerticesNodesMetaMap(int size) {
		this.size = size;
		this.verticesEdgeArray = new Object[size][2];
		this.entriesCount = 1; // for the null vertex node
	}
	
	@SuppressWarnings("unchecked")
	public Node<Edge> getEdgeNodeByVertex(Vertex vertex) {
		return (Node<Edge>) verticesEdgeArray[vertex.name][1];
	}
	
	public void	addEntry(Edge edge, Node<Edge> edgeNode) {
		if (verticesEdgeArray[edge.From.name][0] == null) {
			verticesEdgeArray[edge.From.name][0] = edge.From;
			verticesEdgeArray[edge.From.name][1] = edgeNode;
			entriesCount ++;
		}
		if (verticesEdgeArray[edge.To.name][0] == null) {
			verticesEdgeArray[edge.To.name][0] = edge.To;
			verticesEdgeArray[edge.To.name][1] = edgeNode;
			entriesCount ++;
		}
	}
	
	public boolean isComplete() {
		return (entriesCount == size);
	}
}
