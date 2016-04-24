package dev.research.himanshu.algorithm.assignments.lp5;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A class to implement validation checks and maximum cardinality matching logic, for bipartite graph.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class BipartiteGraphMaximumCardinalityMatcher {
	
	private static BipartiteGraphMaximumCardinalityMatcher instance;				// singleton instance.
	private Graph graph;															// inner graph.
	private ExecutorService threadTasksMaster = Executors.newFixedThreadPool(10); 	// executor service : acting as a managed thread-pool agent.
	private List<MaximalMatchingWorker> freeOuterLayerNodesQueue;					// queue of matchings of free outer nodes.
	
	/* concurrentHashMap is implemented for higher throughput, for case where high concurrency is expected. */
	private ConcurrentHashMap<Integer, Matching> idMatchingMap;		
	
	/**
	 * private constructor.
	 * 
	 * @param graph
	 */
	private BipartiteGraphMaximumCardinalityMatcher(Graph graph) {
		this.graph = graph;
	}
	
	/**
	 * static getter method for the singleton instance.  
	 *  
	 * @param graph
	 * @return
	 */
	public static BipartiteGraphMaximumCardinalityMatcher getInstance(Graph graph) {
		if (instance == null)
			instance = new BipartiteGraphMaximumCardinalityMatcher(graph);
		return instance.setGraph(graph);
	}
	
	/**
	 * getter method for inner graph.
	 * 
	 * @return
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * setter method for inner graph.
	 * 
	 * @param graph
	 * @return
	 */
	public BipartiteGraphMaximumCardinalityMatcher setGraph(Graph graph) {
		this.graph = graph;
		return this;
	}
	
	/**
	 * getter method for managed thread-pool 'threadTasksMaster'.
	 * @return
	 */
	public ExecutorService getThreadTasksMaster() {
		return threadTasksMaster;
	}
	
	/**
	 * getter method for queue 'freeOuterLayerNodesQueue'.
	 * 
	 * @return
	 */
	public List<MaximalMatchingWorker> getFreeOuterLayerNodesQueue() {
		if (freeOuterLayerNodesQueue == null)
			freeOuterLayerNodesQueue = new LinkedList<MaximalMatchingWorker>();
		
		return freeOuterLayerNodesQueue;
	}
	
	/**
	 * getter method for map 'idMatchingMap'.
	 * 
	 * @return
	 */
	public ConcurrentHashMap<Integer, Matching> getIdMatchingMap() {
		if (idMatchingMap == null) 
			idMatchingMap = new ConcurrentHashMap<>();
		
		return idMatchingMap;
	}
	
	/**
	 * function to perform maximum cardinality matching in a given bipartite graph.
	 * 
	 * @return
	 */
	public int performMaximumMatching() {
		int result = 0;
		
		//since all the basic bipartite conditions are met, then proceed with the matching.
		if (validateGraph()) {
			try {
				// triggering concurrent executions.
				List<Future<Void>>	futures = getThreadTasksMaster().invokeAll(getFreeOuterLayerNodesQueue());
				
				for (Future<Void> future : futures)
					future.get();										// wait for all processes to finish.
				
				// find maximum weight and return result.
				Matching maximalMatching = null;
				
				for (Map.Entry<Integer, Matching> idMatchingEntry : getIdMatchingMap().entrySet()) {
					if (maximalMatching == null || (maximalMatching != null && idMatchingEntry.getValue().getWeight() > maximalMatching.getWeight()))
						maximalMatching = idMatchingEntry.getValue();
				}
				
				// print the maximal matching.
				maximalMatching.print();
				
				return (maximalMatching != null ? maximalMatching.getWeight() : result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("G is not bipartite.");
			System.exit(1);
		}
		
		return result;
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
					result = false;								// disconnected graph. 
				else {
					if (vertex.layer.isInner()) {
						innerLayerNodes ++;						// count inner-layer nodes.
					} else {
						outerLayerNodes ++;						// count outer-layer nodes.
						getFreeOuterLayerNodesQueue().add(new MaximalMatchingWorker(vertex));
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
		boolean result = true;
		
		Queue<Vertex> processingQueue = new LinkedList<>();
		
		Vertex vertex = getGraph().verts.get(1);
		vertex.seen = true;
		vertex.layer = Layer.OUTER;
		processingQueue.add(vertex);
				
		while (!processingQueue.isEmpty()) {
			vertex = processingQueue.poll();
			vertex.sortEdges();
			
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
						if (otherVertex.layer == vertex.layer)
							result = false;
					}
					
					edge.seen = true;
				} else {
					// if the edge is seen, check the other side vertex (not necessary, however precautionary)
					if (!edge.isBipartiteCompatible())
						result = false;
				}
				
				// if found failure, break out !
				if (!result) 
					break;
			}
		}
		
		// removing the holds (pointers), GC considerations !
		processingQueue.clear();
		return result;
	}
	
	/**
	 * an inner class (Worker thread), implementing logic of maximal matching.  
	 * 
	 * @author G31 (Himanshu Kandwal and Dharmam Buch)
	 */
	private class MaximalMatchingWorker implements Callable<Void> {
		
		private final Matching myMatching;
		
		public MaximalMatchingWorker(Vertex vertex) {
			this.myMatching = new Matching(vertex);
			
			// register matching.
			getIdMatchingMap().put(vertex.name, myMatching);
		}
		
		@Override
		public Void call() throws Exception {
			Vertex vertex = myMatching.getTailNode();
			boolean matchDetected = true;
			Matching mergableMatching = null;
			
			/* build matching, with all possibilities. */
			while (matchDetected) {
				matchDetected = false;
				Edge maximalWeightedUnseenVertexEdge = null;
				
				for (Edge edge : myMatching.getTailNode().Adj) {
					Vertex otherVertex = edge.otherEnd(vertex);
					
					// case : found vertex (outer) which is used in matching somewhere else.
					if (getIdMatchingMap().containsKey(otherVertex.name)) {
						mergableMatching = getIdMatchingMap().remove(otherVertex.name); 		// unregister the matching for future merging.
						
						// System.out.println(myMatching.getId() + " waiting for : " + mergableMatching.getId());
						while (mergableMatching.isActive()) { 
							/* wait till the mergable matching is still building up. */ 
						}
					
						// merge.
						if (!mergableMatching.isActive())
							matchDetected = myMatching.merge(mergableMatching);
						
						break;
					} 
					// case : found vertex (outer) which is not yet used in matching.
					else {
						if (otherVertex.parent == null && maximalWeightedUnseenVertexEdge == null) {	// as edges are sorted already for a vertex, so no other checks needed.							 
							matchDetected = myMatching.addMate(otherVertex);
							break;
						}
					}				
				}
			}

			// check if there is any potential extension are possible.
			mergableMatching = null;
			for (Edge edge : myMatching.getTailNode().Adj) {
				Vertex otherVertex = edge.otherEnd(vertex);
				
				// note the next possible maximal matching.
				if (getIdMatchingMap().containsKey(otherVertex.name) && !getIdMatchingMap().get(otherVertex.name).isActive()) {
					if ((mergableMatching == null) ||  (mergableMatching != null
							&& (getIdMatchingMap().get(otherVertex.name).getWeight() > mergableMatching.getWeight()))) {
						
						mergableMatching = getIdMatchingMap().get(otherVertex.name);
					}
				}
			}
			
			if (mergableMatching != null) {
				// unregister matching from idMatching map, so that no one else accesses it.
				getIdMatchingMap().remove(mergableMatching.getId());
				
				// merge.
				myMatching.merge(mergableMatching);
			}
		
			myMatching.setActive(false); 				// match building not active anymore, available for merging.
			return null;
		}
	}

}