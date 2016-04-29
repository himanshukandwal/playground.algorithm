package dev.research.himanshu.algorithm.assignments.lp5;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class BipartiteGraphMaximumCardinalityMatcherTest {

	private String testsDirectory = System.getProperty("user.dir") + "/src/main/resources/lp5/tests/";
	
	/*
	 * business logic tests. (using framework for automated testing)
	 */
	@Test
	public void testMaximalMatchingBasic() throws Exception {
		
		FileFilter matchingFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith("test-case-") && pathname.getName().endsWith(".txt"))
					return true;
				return false;
			}
		};
		
		for (File testFile : new File(testsDirectory).listFiles(matchingFileFilter)) {
			
			System.out.println(" working on file : " + testFile.getName());
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(testFile));
			
			String newline = null;
			String prevLine = null;
			
			while ((newline = bufferedReader.readLine()) != null) { prevLine = newline; }
			bufferedReader.close();
			
			assertEquals(Integer.valueOf(prevLine.split(" ")[0]), 
					BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(testFile), false), DebugLevel.ERROR).performMaximumMatching());
		}
		
	}
	
	@Test
	public void testMaximalMatchingComplex() throws Exception {
		
		FileFilter matchingFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith("bip") && pathname.getName().endsWith(".txt"))
					return true;
				return false;
			}
		};
		
		for (File testFile : new File(testsDirectory).listFiles(matchingFileFilter)) {
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(testFile));
			
			String newline = null;
			String prevLine = null;
			
			while ((newline = bufferedReader.readLine()) != null) { prevLine = newline; }
			bufferedReader.close();
			
			assertEquals(Integer.valueOf(prevLine.split(" ")[0]), 
					BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(testFile), false), DebugLevel.ERROR).performMaximumMatching());
		}
	
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
//		File file = new File (testsDirectory + "bip2.txt");
		File file = new File (testsDirectory + "test-case-5.txt");
		assertEquals(Integer.valueOf(35), 
				BipartiteGraphMaximumCardinalityMatcher.getInstance(Graph.readGraph(new Scanner(file), false), DebugLevel.ERROR).performMaximumMatching());
	}
	
	@Test
	public void testWaitForEntrySetup() throws Exception {
		 Set<WaitForEntry> waitingForResourcesSet = new HashSet<>();
		 WaitForEntry entry = new WaitForEntry(1, 2);
		 
		 waitingForResourcesSet.add(entry);
		 entry = new WaitForEntry(2, 1);
		 assertTrue(waitingForResourcesSet.contains(entry));
		 
		 entry = new WaitForEntry(1, 2);
		 assertTrue(waitingForResourcesSet.contains(entry));
		 
		 System.out.println(waitingForResourcesSet.remove(entry));
	}
	
	private class WaitForEntry implements Comparable<WaitForEntry> {
		
		private int requestingResourceId;
		private int requestedResourceId;
		
		/**
		 * constructor.
		 */
		public WaitForEntry(int requestingResourceId, int requestedResourceId) {
			this.requestingResourceId = requestingResourceId;
			this.requestedResourceId = requestedResourceId;
		}

		@Override
		public int compareTo(WaitForEntry otherWaitForEntry) {
			int result = -1;
			if ((otherWaitForEntry.requestingResourceId == requestingResourceId && otherWaitForEntry.requestedResourceId == requestedResourceId) 
					|| (otherWaitForEntry.requestingResourceId == requestedResourceId && otherWaitForEntry.requestedResourceId == requestingResourceId)) {
				result = 0; 
			}
			return result;
		}
		
		@Override
		public String toString() {
			return requestingResourceId + " : " + requestedResourceId;
		}
		
		@Override
		public int hashCode() {
			/* this is a very crude way of computing hashcode, however, resolting to this and 
			 * not using : Arrays.hashcode(new int[] {requestingResourceId, requestedResourceId}) as we have preserve the same value if 
			 * the values of requestingResourceId and requestedResourceId gets swapped. i.e (3,2) should be same as (2,3)
			 */
			return Integer.valueOf(requestingResourceId).hashCode() + Integer.valueOf(requestedResourceId).hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof WaitForEntry)) 
				return false;
			return (this.compareTo((WaitForEntry) obj) == 0 ? true : false);
		}
		
	}

}
