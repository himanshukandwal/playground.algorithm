package dev.research.himanshu.algorithm.assignments.lp0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.TestCase;

public class HierholzerAlgorithmImplTest extends TestCase {

<<<<<<< HEAD
	public void testCheckSampleInput1() throws Exception {
=======
	public void testCheckSampleInput() throws Exception {
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
		Graph g = prepareGraph(System.getProperty("user.dir") + "/src/main/resources/lp0-HierholzerAlgorithm-1.txt");
		
		assertTrue(
				HierholzerAlgorithmImpl.verifyTour (
						g, 
						HierholzerAlgorithmImpl.findEulerTour (g), 
						g.verts.get(1)
				)
		);
<<<<<<< HEAD
		System.out.println();
=======
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
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
<<<<<<< HEAD
		System.out.println();
=======
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
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
<<<<<<< HEAD
		System.out.println();
=======
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
	}
	
	public void testCheckSampleInput4() throws Exception {
		Graph g = prepareGraph(System.getProperty("user.dir") + "/src/main/resources/lp0-HierholzerAlgorithm-big-1.txt");
		
		assertTrue(
				HierholzerAlgorithmImpl.verifyTour (
						g, 
						HierholzerAlgorithmImpl.findEulerTour (g), 
<<<<<<< HEAD
						g.verts.get(1)
=======
						g.verts.get(4)
>>>>>>> bd478faf2ec6754d33e0844d01f01a2227d88b9c
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
