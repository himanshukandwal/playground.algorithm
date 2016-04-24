package dev.research.himanshu.algorithm;

public class BellmanFordAlgorithm extends AbstractGraphShortestPath {

	public BellmanFordAlgorithm() {
		super();
	}

	public BellmanFordAlgorithm(Graph graph) {
		super(graph);
	}

	@Override
	public void arrangeShortestPath() {
		// sets parent, distance to null. seen to false.
		getInnerGraph().resetGraph();
		
		for (Vertex vertex : getInnerGraph())
			if (vertex != null)
				vertex.setShortestPathDistanceArr(getInnerGraph().numNodes);
		
		Vertex sourceVertex = getInnerGraph().source;
		sourceVertex.seen = true;
		sourceVertex.getShortestPathDistanceArr()[0] = 0;
		
		for (int vertexIndex = 1; vertexIndex <= getInnerGraph().numNodes; vertexIndex++) {
			boolean nochange = true;	
				
			for (Vertex vertex : getInnerGraph()) {
				if (vertex != null) {
					vertex.getShortestPathDistanceArr()[vertexIndex] = vertex.getShortestPathDistanceArr()[vertexIndex -1];
					
					for (Edge edge : vertex.revAdj) {
						Vertex parentVertex = edge.otherEnd(vertex);
						
						if ((parentVertex.getShortestPathDistanceArr() [vertexIndex -1] != null) 
								&& (vertex.getShortestPathDistanceArr()[vertexIndex] == null 
								|| vertex.getShortestPathDistanceArr()[vertexIndex] > parentVertex.getShortestPathDistanceArr() [vertexIndex -1] + edge.Weight)) {
							
							vertex.getShortestPathDistanceArr()[vertexIndex] = parentVertex.getShortestPathDistanceArr() [vertexIndex -1] + edge.Weight;
							vertex.parent = parentVertex;
							nochange = false;
						}
					}
				}
			}
			
			if (nochange) {
				for (Vertex vertex : getInnerGraph())
					if (vertex != null)
						vertex.distance = vertex.getShortestPathDistanceArr() [vertexIndex];
				
				setHasNegativeCycle(false);
				return;
			}
		}
		
		setHasNegativeCycle(true);
	}
	
}
