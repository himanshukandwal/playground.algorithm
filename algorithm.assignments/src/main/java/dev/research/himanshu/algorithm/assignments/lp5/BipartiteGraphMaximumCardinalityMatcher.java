package dev.research.himanshu.algorithm.assignments.lp5;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * A class to implement validation checks and maximum cardinality matching logic, for bipartite graph.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class BipartiteGraphMaximumCardinalityMatcher extends AbstractBipartiteMatcher {
	
	private DebugLevel debuglevel;											// Debug level.
	private List<MaximalMatchingWorker> outerLayerNodesWorkers;				// List of matchings-workers for free outer nodes.
	
	/**
	 * private constructor.
	 * 
	 * @param graph
	 */
	private BipartiteGraphMaximumCardinalityMatcher(Graph graph, DebugLevel debugLevel) {
		super(graph);
		this.debuglevel = debugLevel;
	}
	
	/**
	 * static getter method for the singleton instance.  
	 *  
	 * @param graph
	 * @return
	 */
	public static BipartiteGraphMaximumCardinalityMatcher getInstance(Graph graph, DebugLevel debugLevel) {
		BipartiteGraphMaximumCardinalityMatcher instance = new BipartiteGraphMaximumCardinalityMatcher(graph, debugLevel);
		return instance.setGraph(graph);
	}
	
	/**
	 * static getter method for the singleton instance.  
	 *  
	 * @param graph
	 * @return
	 */
	public static BipartiteGraphMaximumCardinalityMatcher getInstance(Graph graph) {
		BipartiteGraphMaximumCardinalityMatcher instance = new BipartiteGraphMaximumCardinalityMatcher(graph, DebugLevel.MINIMAL);
		return instance.setGraph(graph);
	}
	
	/**
	 * setter method for inner graph.
	 * 
	 * @param graph
	 * @return
	 */
	public BipartiteGraphMaximumCardinalityMatcher setGraph(Graph graph) {
		super.graph = graph;
		return this;
	}
	
	/**
	 * getter method for queue 'freeOuterLayerNodesQueue'.
	 * 
	 * @return
	 */
	public List<MaximalMatchingWorker> getOuterLayerNodesWorkers() {
		if (outerLayerNodesWorkers == null)
			outerLayerNodesWorkers = new LinkedList<MaximalMatchingWorker>();
		
		return outerLayerNodesWorkers;
	}
	
	/**
	 * getter method for the enum 'debugLevel'
	 * 
	 * @return
	 */
	public DebugLevel getDebuglevel() {
		return debuglevel;
	}
	
	/**
	 * function to perform maximum cardinality matching in a given bipartite graph.
	 * 
	 * @return
	 */
	public Integer performMaximumMatching() {
		int result = 0;
		
		//since all the basic bipartite conditions are met, then proceed with the matching.
		if (validateGraph()) {
			
			try {
				// triggering concurrent executions and holding on the futures (outcomes)
				List<Future<Pair>> futures = getThreadTasksMaster().invokeAll(getOuterLayerNodesWorkers());
			
				// maximum cardinal path.
				List<Edge> maximalMatchingSeries = (isStoreResults() ? new LinkedList<Edge>() : null);
				
				// loop  through the futures and wait for result to appear and process for maximum cardinality.
				for (Future<Pair> future : futures) {
					if (debuglevel.isErrorEnabled())
						System.out.println(" --> [" + future.get().getLength() + "] ");

					if (isStoreResults()) 
						maximalMatchingSeries.addAll(future.get().getEdges());
					
					// store the result.
					result += future.get().getLength();
				}
								
				// print the maximum cardinality achievable.
				System.out.println(result);
				
				if (isStoreResults()) {

					// print the edges, if the 'debuglevel' has been set to VERBOSE.
					for (Edge edge : maximalMatchingSeries)
						System.out.println(edge);
				}
				
				return result;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("G is not bipartite.");
			System.exit(1);
		}
		
		return result;
	}
	
	private boolean isStoreResults() {
		return debuglevel.isVerboseEnabled();
	}

	/**
	 * method to perform all the core validations requisite for the maximal matching to happen.
	 * 
	 * @return
	 */
	public boolean validateGraph() {
		boolean result = bipartiteBfsChecking();

		if (result) {
			int innerLayerNodes = 0;
			int outerLayerNodes = 0;
			
			// till this point, graph has been properly set.
			for (Vertex vertex : graph) {
				vertex.seen = false;
				
				if (vertex.layer == null)
					result = false;																	// disconnected graph. 
				else {
					if (vertex.layer.isInner()) {
						innerLayerNodes ++;															// count inner-layer nodes.
					} else {
						outerLayerNodes ++;															// count outer-layer nodes.
					}
				}
			}
			
			if (innerLayerNodes + outerLayerNodes != graph.numNodes)
				result = false;
		}

		return result;
	}
	
	/**
	 * an internal BFS traversal to perform bipartite graph checking.
	 *  
	 * @return
	 */
	private boolean bipartiteBfsChecking() {
		Queue<Vertex> processingQueue = new LinkedList<>();
		boolean result = true;
		
		for (Vertex vertex : getGraph()) {
			if (!vertex.seen) {
				vertex.seen = true;
				vertex.layer = Layer.OUTER;
				processingQueue.add(vertex);
				
				Integer hiddenComponentsCount = bipartiteBfsCheckingInternal(processingQueue);
				
				if (hiddenComponentsCount == null) {
					result = false;
					break;
				}
				
				getOuterLayerNodesWorkers().add (new MaximalMatchingWorker(vertex, hiddenComponentsCount, isStoreResults()));
			}
		}
		
		return result;
	}
	
	private Integer bipartiteBfsCheckingInternal(Queue<Vertex> processingQueue) {
		boolean result = true;
		
		int hiddenComponentsCount = 0;
		while (!processingQueue.isEmpty()) {
			Vertex vertex = processingQueue.poll();
			
			if (vertex.layer.isInner())
				hiddenComponentsCount ++;
			
			for (Edge edge : vertex.Adj) {
				if (!edge.seen) {
					Vertex otherVertex = edge.otherEnd(vertex);
					
					// if the vertex is not seen then add it to the queue. (after providing proper layering)
					if (!otherVertex.seen) {
						otherVertex.seen = true;
						otherVertex.layer = vertex.layer.other();
						processingQueue.add(otherVertex);
					} else {
						// else check if the existing layer is in accordance or not.
						if (otherVertex.layer == vertex.layer) {
							if (getDebuglevel().isErrorEnabled())
								System.out.println(otherVertex + " and " + vertex + " are on same layer : " + otherVertex.layer);
							
							result = false;
						}
					}
					
					edge.seen = true;
				} else {
					// if the edge is seen, check the other side vertex (not necessary, however precautionary)
					if (!edge.isBipartiteCompatible()) {
						if (getDebuglevel().isErrorEnabled())
							System.out.println(edge + " is not bipartite compatible");
						
						result = false;					
					}
				}
				
				// if found failure, break out !
				if (!result) 
					break;
			}
		}
		
		// removing the holds (pointers), GC considerations !
		processingQueue.clear();
		return (result ? hiddenComponentsCount : null);
	}
	
	/**
	 * an inner class (Worker thread), implementing logic of maximal matching.  
	 * 
	 * @author G31 (Himanshu Kandwal and Dharmam Buch)
	 */
	public static class MaximalMatchingWorker implements Callable<Pair> {
		
		/* the main source vertex from which matching will be initiated. */
		private final Vertex sourceVertex;
		
		/* total number of vertices present in this cluster (sub-graph) */
		private final int hiddenComponentsCount;
		
		/* the best possible matching present for the sub-graph (starting with vertex) s*/
		private List<Edge> maxEdges;		
		
		/* flag to indicate whether we have to store results or not. */
		private boolean storeResults;
		
		/**
		 * constructor.
		 * 
		 * @param vertex
		 */
		public MaximalMatchingWorker(Vertex vertex, int hiddenComponentsCount, boolean storeResults) {
			this.storeResults = storeResults;
			this.sourceVertex = vertex;
			this.hiddenComponentsCount = hiddenComponentsCount;
		}
		
		/**
		 * getter for 'maxEdges'
		 * 
		 * @return
		 */
		public List<Edge> getMaxEdges() {
			if (maxEdges == null) 
				maxEdges = new LinkedList<>();
			
			return maxEdges;
		}
		
		/**
		 * overridden call method (similar to 'run' method)
		 */
		@Override
		public Pair call() throws Exception {
			sourceVertex.parent = sourceVertex;
			
			return detectLargestCardinalEdge(sourceVertex, 0);
		}
		
		public Pair detectLargestCardinalEdge (Vertex vertex, int seenCount) {	
			boolean coverageComplete = (seenCount == hiddenComponentsCount);
			
			if (coverageComplete)
				return Pair.pairOf (null, 0, coverageComplete);
			
			/* place-holder for max-length and max-edges */
			Pair innerResult = null;
			
			for (int idx = 0; idx < vertex.Adj.size(); idx ++) {
				Edge edge = vertex.Adj.get(idx);
				
				Vertex otherVertex = edge.otherEnd(vertex);
				
				if (otherVertex.parent == null) {
					otherVertex.parent = vertex;
					
					// recurse.
					innerResult = detectLargestCardinalEdge (otherVertex, (otherVertex.layer.isInner() ? seenCount + 1 : seenCount));
					
					// break recursion if full coverage has been reached. (perfect matching case), else clean-up for other possible scenario.
					if (innerResult.isCoverageComplete()) {
						if (vertex.layer.isOuter()) {
							innerResult.length = innerResult.getLength() + 1;
							
							if (storeResults)
								innerResult.getEdges().add(edge);
						}
						break;											
					} else
						otherVertex.parent = null;
				}
			}
			
			return (isNull(innerResult) ? Pair.pairOf (null, 0, coverageComplete) : innerResult);
		}
		
		public boolean isNull (Object object) {
			return object == null;
		}
	}
	
	/**
	 * a container class to store temporal result.
	 *
	 */
	public static class Pair {
	
		private List<Edge> edges;
		private Integer length;
		private boolean coverageComplete;
		
		private Pair(List<Edge> edges, Integer length) {
			this.length = length;
			this.edges = edges;
		}
		
		private Pair(List<Edge> edges, Integer length, boolean coverageComplete) {
			this(edges, length);
			this.coverageComplete = coverageComplete;
		}
		
		public static Pair pairOf(List<Edge> edge, Integer length, boolean coverageComplete) {
			return new Pair(edge, length, coverageComplete);
		}
		
		public List<Edge> getEdges() {
			if (edges == null)
				edges = new LinkedList<>();
			
			return edges;
		}
		
		public Integer getLength() {
			return length;
		}
		
		public boolean isCoverageComplete() {
			return coverageComplete;
		}
		
	}

}