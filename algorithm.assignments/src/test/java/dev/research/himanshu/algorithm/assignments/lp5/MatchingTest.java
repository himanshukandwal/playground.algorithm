package dev.research.himanshu.algorithm.assignments.lp5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class MatchingTest {

	@Test
	public void testAddMateOk() {
		Vertex one = new Vertex(1);
		one.layer = Layer.OUTER;
		
		Vertex two = new Vertex(2);
		two.layer = Layer.INNER;
		
		Matching matching = new Matching(one);
		assertTrue(matching.addMate(two));
		assertEquals(1, matching.getCardinality());
	}
	
	@Test
	public void testAddMateNOk() {
		Vertex one = new Vertex(1);
		one.layer = Layer.OUTER;
		
		Vertex two = new Vertex(2);
		two.layer = Layer.OUTER;
		
		Matching matching = new Matching(one);
		assertFalse(matching.addMate(two));
		assertEquals(0, matching.getCardinality());
		
		one.layer = two.layer = Layer.INNER;
		assertFalse(matching.addMate(two));
		assertEquals(0, matching.getCardinality());
	}

	@Test
	public void testWeightCheckNOk() throws IOException {
		FileFilter matchingFileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith("matching-") && pathname.getName().endsWith(".txt"))
					return true;
				return false;
			}
		};
		
		BufferedReader bufferedReader = null;
		for (File file : new File(System.getProperty("user.dir") + "/src/main/resources/lp5/validations/").listFiles(matchingFileFilter)) {
			Graph graph = Graph.readGraph(new Scanner(file), false);
			
			Vertex sourceVertex = graph.verts.get(1);
			sourceVertex.layer = Layer.OUTER;
			
			Matching matching = new Matching (sourceVertex);
			
			Vertex previousVertex = sourceVertex;
			for (Vertex vertex : graph) {
				if (vertex != sourceVertex) {
					vertex.layer = previousVertex.layer.other();
					matching.addMate(vertex);
					previousVertex = vertex;
				}
			}
			
			bufferedReader = new BufferedReader(new FileReader(file));
			String newline = null;
			String prevLine = null;
			
			while ((newline = bufferedReader.readLine()) != null) { prevLine = newline; }
			bufferedReader.close();
			
			assertEquals(Integer.valueOf(prevLine.split(" ")[0]), Integer.valueOf(matching.getCardinality()));
			assertEquals(Integer.valueOf(prevLine.split(" ")[1]), Integer.valueOf(matching.getWeight()));
		}
		
	}

	@Test
	public void testMerge() throws IOException {
		Vertex one = new Vertex(1);
		one.layer = Layer.OUTER;
		
		Vertex two = new Vertex(2);
		two.layer = Layer.INNER;
		
		Matching matching = new Matching(one);
		assertTrue(matching.addMate(two));
		assertEquals(1, matching.getCardinality());
		assertEquals(2, matching.getTailNode().name);
		
		Vertex three = new Vertex(3);
		three.layer = Layer.OUTER;
		
		Vertex four = new Vertex(4);
		four.layer = Layer.INNER;
		
		Edge connectingEdge = new Edge(two, three, 2);
		matching.getTailNode().Adj.add(connectingEdge);
		three.Adj.add(connectingEdge);
		
		Matching otherMatching = new Matching(three);
		assertTrue(otherMatching.addMate(four));
		assertEquals(1, otherMatching.getCardinality());
		assertEquals(4, otherMatching.getTailNode().name);
		
		// merge.
//		assertTrue(matching.merge(otherMatching));
//		assertEquals(2, matching.getCardinality());
//		assertEquals(1, matching.getStartingNode().name);
//		assertEquals(4, matching.getTailNode().name);
	}

}
