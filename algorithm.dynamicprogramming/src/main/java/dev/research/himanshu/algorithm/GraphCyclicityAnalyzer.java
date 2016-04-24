package dev.research.himanshu.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * A class crafted specifically for performing two tasks while DFSing only once through the graph.
 * 
 * Only either of the two conditions are possible and hence provided by the class.
 * 
 * 	a) provides topological order of the graph (if present)
 * 		This information is provided two ways :
 * 			-	isAcyclic()  			(a boolean flag)
 * 			-	getTopologicalOrder()  	(a method to provide the topological order) 
 * 
 * 	b) provides the list of cycles present in the graph (if graph is not acyclic)
 * 		This information is provided in the way :
 * 			-	getCycles()  			(a method to provide the lists of ALL the well-ordered cycles present in the graph)
 * 										 		This includes all the possible permutation of cycles (inner)	
 * 
 * The most important point here to mention is that we are not resetting the graph back to its original state, post estimation.
 * Hence, the graph will be in topological state (i.e. well established parent child hierarchy) if the graph is acyclic.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class GraphCyclicityAnalyzer {

	public boolean acyclic;
	public Stack<Vertex> topologicalOrder;
	public List<Stack<Vertex>> cycles;
	public Stack<Vertex> currentThreadofVertices;
	public Map<Vertex, Set<Stack<Vertex>>> potentialCycleHeads; // potential cycle heads  (to counter issue of DFSing once and to detect inner cycles)
	
	public Stack<Vertex> getTopologicalOrder() {
		if (topologicalOrder == null)
			topologicalOrder = new Stack<Vertex>();

		return topologicalOrder;
	}
	
	public List<Stack<Vertex>> getCycles() {
		if (cycles == null)
			cycles = new ArrayList<>();
		
		return cycles;
	}
	
	public Map<Vertex, Set<Stack<Vertex>>> getPotentialCycleHeads() {
		if (potentialCycleHeads == null)
			potentialCycleHeads = new HashMap<>();
			
		return potentialCycleHeads;
	}
	
	public void setTopologicalOrder(Stack<Vertex> topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
	}
	
	public boolean isAcyclic() {
		return acyclic;
	}
	
	public void setAcyclic(boolean acyclic) {
		this.acyclic = acyclic;
	}
	
	public Stack<Vertex> getCurrentThreadofVertices() {
		if (currentThreadofVertices == null)
			currentThreadofVertices = new Stack<>();
			
		return currentThreadofVertices;
	}
	
	public static Graph readGraph(String filename) {
		Graph graph = null;
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile);
			graph = Graph.readGraph(in, true);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return graph;
	}

	/**
	 * wrapper method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public static GraphCyclicityAnalyzer analyze(Graph graph) {
		return new GraphCyclicityAnalyzer().orderGraph(graph, null);
	}

	/**
	 * wrapper method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public static GraphCyclicityAnalyzer analyze(Graph graph, Integer optionalSource) {
		return new GraphCyclicityAnalyzer().orderGraph(graph, optionalSource);
	}
	
	/**
	 * method to find the topological order (if present) in a graph
	 * 
	 * @param graph
	 * @return
	 */
	public GraphCyclicityAnalyzer orderGraph(Graph graph, Integer optionalSource) {
		Stack<Vertex> topologicalOrderStack = new Stack<>();
		Set<Vertex> openHeads = new HashSet<Vertex>();
		
		if (optionalSource != null) {
			for (Vertex graphVertex : graph.verts) {
				if (graphVertex != null && graphVertex.name == optionalSource) {
					openHeads.add(graphVertex);
					getCurrentThreadofVertices().add(graphVertex);
					performDFSOrdering(topologicalOrderStack, openHeads, false, null, graphVertex);
					getCurrentThreadofVertices().clear();
					break;
				}
			}
		}

		for (Vertex graphVertex : graph.verts) {
			if (graphVertex != null && !graphVertex.seen) {
				openHeads.add(graphVertex);
				getCurrentThreadofVertices().add(graphVertex);
				performDFSOrdering(topologicalOrderStack, openHeads, false, null, graphVertex);
				getCurrentThreadofVertices().clear();
			}
		}

		if (topologicalOrderStack.size() == graph.verts.size() - 1) {
			setTopologicalOrder(topologicalOrderStack);
			setAcyclic(true);
		} else { 
			graph.resetGraph();
			if (topologicalOrderStack.size() > 0 && (topologicalOrderStack.get(0) == topologicalOrderStack.get(topologicalOrderStack.size() -1)))
				getCycles().add(topologicalOrderStack);
		}
		
		return this;
	}

	private void performDFSOrdering(Stack<Vertex> topologicalOrderStack, Set<Vertex> openHeads, boolean isTransitive,
			Vertex parentVertex, Vertex vertex) {

		vertex.seen = true;
		
		for (Iterator<Edge> edgesIterator = vertex.Adj.iterator(); edgesIterator.hasNext();) {
			Edge connectingEdge = edgesIterator.next();
			Vertex otherVertex = connectingEdge.otherEnd(vertex);

			if (!otherVertex.seen) {
				if (otherVertex.Adj.size() == 0) {
					otherVertex.parent = vertex;
					otherVertex.seen = true;

					topologicalOrderStack.add(otherVertex);
				} else {
					getCurrentThreadofVertices().add(otherVertex);
					performDFSOrdering(topologicalOrderStack, openHeads, true, vertex, otherVertex);
				}
			} else {
				if (isTransitive && openHeads.contains(otherVertex) && getCurrentThreadofVertices().contains(otherVertex)) {
					// full-chain cycle
					vertex.topologicallyOrderable = false;
				
					getCycles().add(copyStack(getCurrentThreadofVertices()));
					getCurrentThreadofVertices().clear();
					openHeads.remove(otherVertex);
					
					getCurrentThreadofVertices().add(vertex);
					openHeads.add(vertex);
				} else if (otherVertex.parent == null) {
					if (openHeads.remove(otherVertex)) {
						otherVertex.parent = vertex;
					} else {
						vertex.topologicallyOrderable = false;
						
						// in-chain cycle ( case 1 )
						if (getCurrentThreadofVertices().contains(otherVertex)) {
							// in-chain cycle
							Stack<Vertex> cyclicCopy = copyStack(getCurrentThreadofVertices(), otherVertex);
							getCycles().add(cyclicCopy);
						} else {
							// in-chain cycle ( case 2 )
							if (getPotentialCycleHeads().containsKey(otherVertex)) {
								Set<Stack<Vertex>> potentialCyclesFromVertex = getPotentialCycleHeads().get(otherVertex);
								
								for (Stack<Vertex> potentialCycle : potentialCyclesFromVertex) {
									if (potentialCycle.size() > 0 && getCurrentThreadofVertices().contains(potentialCycle.get(potentialCycle.size() - 1))) {
										// in-chain cycle
										Vertex potentialCycleLastVertex = potentialCycle.get(potentialCycle.size() - 1);
										Stack<Vertex> cyclicCopy = copyStack(getCurrentThreadofVertices(), potentialCycleLastVertex, false);
										cyclicCopy.addAll(potentialCycle);
										getCycles().add(cyclicCopy);
									}
								}
							}
						}
					}
				}
			}
			
			vertex.topologicallyOrderable = vertex.topologicallyOrderable && otherVertex.topologicallyOrderable;
		}
		
		getCurrentThreadofVertices().remove(vertex);

		if (vertex.topologicallyOrderable) {
			vertex.parent = parentVertex;
			topologicalOrderStack.push(vertex);
		}
	}

	
	/**
	 * method to fully copy source stack
	 */
	public Stack<Vertex> copyStack(Stack<Vertex> source) {
		return copyStack(source, null, null, false, true);
	}
	
	/**
	 * method to fully copy source stack, excluding the terminal vertex 
	 */
	public Stack<Vertex> copyStack(Stack<Vertex> source, boolean includeTerminal) {
		return copyStack(source, null, null, false, includeTerminal);
	}
	
	/**
	 * overloaded method to fully copy source stack, from the mentioned source vertex
	 */
	public Stack<Vertex> copyStack(Stack<Vertex> source, Vertex sourceVertex) {
		return copyStack(source, sourceVertex, null, false, true);
	}
	
	/**
	 * method to fully copy source stack, from the mentioned source vertex and excluding the terminal vertex
	 */
	public Stack<Vertex> copyStack(Stack<Vertex> source, Vertex sourceVertex, boolean includeTerminal) {
		return copyStack(source, sourceVertex, null, false, includeTerminal);
	}
	
	/**
	 * method to selectively (starting from initalSourceVertex or transitiveSourceVertex) copy source stack
	 * with or without excluding the terminal vertex.
	 */	
	public Stack<Vertex> copyStack(Stack<Vertex> source, Vertex initalSourceVertex, Vertex transitiveSourceVertex, boolean isTransitive, boolean includeTerminal) {
		Stack<Vertex> copy = new Stack<Vertex>();
		
		boolean copynow = false;
		for (int index = 0; index < source.size(); index ++) {
			
			// prioritizing transitiveSourceVertex then initalSourceVertex
			if ((isTransitive 
						 && (transitiveSourceVertex != null && source.get(index) == transitiveSourceVertex)) 
				 || (!isTransitive
						 && ((initalSourceVertex == null) || (initalSourceVertex != null && source.get(index) == initalSourceVertex))) 
				 || (copynow)) {
				
				copy.push(source.get(index));
				
				// create potential cycle heads  (to counter issue of DFSing once and to detect inner cycles)
				if (copynow)  {
					Stack<Vertex> innerCopy = copyStack(source, initalSourceVertex, source.get(index), true, includeTerminal);
					
					if (getPotentialCycleHeads().containsKey(source.get(index))) {
						getPotentialCycleHeads().get(source.get(index)).add(innerCopy);
					} else {
						Set<Stack<Vertex>> listOfPotentialCycles = new HashSet<>();
						listOfPotentialCycles.add(innerCopy);
						
						getPotentialCycleHeads().put(source.get(index), listOfPotentialCycles);
					}
				}
				copynow = true;
			}	
		}
		
		if (initalSourceVertex != null && includeTerminal)
			copy.push(initalSourceVertex);
		
		return copy;
	}
	
}
