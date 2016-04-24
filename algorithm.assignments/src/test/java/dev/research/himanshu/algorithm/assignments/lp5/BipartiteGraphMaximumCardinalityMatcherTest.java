package dev.research.himanshu.algorithm.assignments.lp5;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class BipartiteGraphMaximumCardinalityMatcherTest {

	/*
	 * business logic tests. 
	 */
	@Test
	public void testmaximalMatching() throws Exception {
		String testsDirectory = System.getProperty("user.dir") + "/src/main/resources/lp5/tests/";
		
		File file = new File(testsDirectory + "test-case-1.txt");
		assertEquals(2, BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(file), false)).performMaximumMatching());
		
	}
	
	/*
	 * validations
	 */
	@Test
	public void testBipartite() throws FileNotFoundException {
		FileFilter okFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith("ok-") && pathname.getName().endsWith(".txt"))
					return true;
				return false;
			}
		};
		
		FileFilter nokFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith("nok-") && pathname.getName().endsWith(".txt"))
					return true;
				return false;
			}
		};
		
		for (File file : new File(System.getProperty("user.dir") + "/src/main/resources/lp5/validations/").listFiles(okFileFilter)) {
			System.out.println("validating file : " + file.getName());
			assertTrue (BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(file), false)).validateGraph());
		}
		
		for (File file : new File(System.getProperty("user.dir") + "/src/main/resources/lp5/validations/").listFiles(nokFileFilter)) {
			System.out.println("validating file : " + file.getName());
			assertFalse (BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(file), false)).validateGraph());
		}
	}
	
	@Test
	public void testBipartiteSpecific() throws FileNotFoundException {
		File file = new File (System.getProperty("user.dir") + "/src/main/resources/lp5/validations/nok-small-bipartite-1001.txt");
		assertFalse (BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(file), false)).validateGraph());
	}

}
