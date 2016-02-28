package dev.research.himanshu.algorithm.assignments.lp1;

import junit.framework.TestCase;

public class NumberNodeTest extends TestCase {
	
	public void testSum() throws Exception {
		assertEquals(22, NumberNode.sum(new NumberNode(20), new NumberNode(2)).baseRepresentation());
		assertEquals(202, NumberNode.sum(new NumberNode(200), new NumberNode(2)).baseRepresentation());
		assertEquals(10, NumberNode.sum(new NumberNode(2), new NumberNode(8)).baseRepresentation());
		assertEquals(22, NumberNode.sum(new NumberNode(2), new NumberNode(20)).baseRepresentation());
		assertEquals(202, NumberNode.sum(new NumberNode(2), new NumberNode(200)).baseRepresentation());
		assertEquals(400, NumberNode.sum(new NumberNode(200), new NumberNode(200)).baseRepresentation());
	}
	
	public void testSubstraction() throws Exception {
		assertEquals(18, NumberNode.subtract(new NumberNode(20), new NumberNode(2)).baseRepresentation());
		assertEquals(198, NumberNode.subtract(new NumberNode(200), new NumberNode(2)).baseRepresentation());
		assertEquals(-6, NumberNode.subtract(new NumberNode(2), new NumberNode(8)).baseRepresentation());
		assertEquals(-18, NumberNode.subtract(new NumberNode(2), new NumberNode(20)).baseRepresentation());
		assertEquals(-198, NumberNode.subtract(new NumberNode(2), new NumberNode(200)).baseRepresentation());
		assertEquals(0, NumberNode.subtract(new NumberNode(200), new NumberNode(200)).baseRepresentation());
	}
	
	public void testProduct() throws Exception {
		assertEquals(40, NumberNode.product(new NumberNode(20), new NumberNode(2)).baseRepresentation());
		assertEquals(441, NumberNode.product(new NumberNode(21), new NumberNode(21)).baseRepresentation());
		
		assertEquals(0, NumberNode.product(new NumberNode(0), new NumberNode(2)).baseRepresentation());
		assertEquals(121, NumberNode.product(new NumberNode(11), new NumberNode(11)).baseRepresentation());
		assertEquals(19279000, NumberNode.product(new NumberNode(154232), new NumberNode(125)).baseRepresentation());
		
		assertEquals(773429505000l, NumberNode.product(new NumberNode(147855), new NumberNode(5231000)).baseRepresentation());
		assertEquals(0, NumberNode.product(new NumberNode(0), new NumberNode(0)).baseRepresentation());
	}
	
	public void testDivision() throws Exception {
		assertEquals(10, NumberNode.divide(new NumberNode(20), new NumberNode(2)).baseRepresentation());
		assertEquals(1, NumberNode.divide(new NumberNode(21), new NumberNode(21)).baseRepresentation());
		
		assertEquals(1, NumberNode.divide(new NumberNode(11), new NumberNode(11)).baseRepresentation());
		assertEquals(125, NumberNode.divide(new NumberNode(19279000), new NumberNode(154232)).baseRepresentation());
		
		assertEquals(5231000, NumberNode.divide(new NumberNode(773429505000l), new NumberNode(147855)).baseRepresentation());
		assertEquals(0, NumberNode.divide(new NumberNode(0), new NumberNode(1)).baseRepresentation());
	}
	
	public void testPower() throws Exception {
		assertEquals(4, NumberNode.power(new NumberNode(2), 2).baseRepresentation());
		assertEquals(8, NumberNode.power(new NumberNode(2), 3).baseRepresentation());
		assertEquals(16, NumberNode.power(new NumberNode(2), 4).baseRepresentation());
		assertEquals(32, NumberNode.power(new NumberNode(2), 5).baseRepresentation());
		assertEquals(64, NumberNode.power(new NumberNode(2), 6).baseRepresentation());
		assertEquals(128, NumberNode.power(new NumberNode(2), 7).baseRepresentation());
		
	}
	
}
