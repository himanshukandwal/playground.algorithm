package dev.research.himanshu.algorithm.assignments.longproject0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

public class HierholzerAlgorithmImplTest extends TestCase {

	public void testCheckSampleInput1() throws Exception {
		Graph g = prepareGraph(System.getProperty("user.dir") + "/src/main/resources/lp0-HierholzerAlgorithm-1.txt");
		
		assertTrue(
				HierholzerAlgorithmImpl.verifyTour (
						g, 
						HierholzerAlgorithmImpl.findEulerTour (g), 
						g.verts.get(1)
				)
		);
		System.out.println();
	}

	public void testCheckSampleInput2() throws Exception {
		Graph g = prepareGraph(System.getProperty("user.dir") + "/src/main/resources/lp0-HierholzerAlgorithm-2.txt");
		
		assertTrue(
				HierholzerAlgorithmImpl.verifyTour (
						g, 
						HierholzerAlgorithmImpl.findEulerTour (g), 
						g.verts.get(1)
				)
		);
		System.out.println();
	}
	
	public void testCheckSampleInput3() throws Exception {
		Graph g = prepareGraph(System.getProperty("user.dir") + "/src/main/resources/lp0-HierholzerAlgorithm-3.txt");
		
		assertTrue(
				HierholzerAlgorithmImpl.verifyTour (
						g, 
						HierholzerAlgorithmImpl.findEulerTour (g), 
						g.verts.get(4)
				)
		);
	}
	
	public void testCheckSampleInput4() throws Exception {
		Graph g = prepareGraph(System.getProperty("user.dir") + "/src/main/resources/lp0-HierholzerAlgorithm-big-1.txt");
		
		assertTrue(
				HierholzerAlgorithmImpl.verifyTour (
						g, 
						HierholzerAlgorithmImpl.findEulerTour (g), 
						g.verts.get(1)
				)
		);
	}
	
	private static Graph prepareGraph(String filelocation) {
		Graph g = null;
		try {
			Scanner in;
			File inputFile = new File (filelocation);
			in = new Scanner(inputFile);
			g = Graph.readGraph(in, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return g;
	}

}
