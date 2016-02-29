package dev.research.himanshu.algorithm.assignments.lp1;

import junit.framework.TestCase;

public class ComplexNumberNodeTest extends TestCase {
	
	public void testSum() throws Exception {
		assertEquals(18, ComplexNumberNode.sum(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-22, ComplexNumberNode.sum(new ComplexNumberNode(-20), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-18, ComplexNumberNode.sum(new ComplexNumberNode(-20), new ComplexNumberNode(2)).baseRepresentation());
		
		assertEquals(0, ComplexNumberNode.sum(new ComplexNumberNode(2), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(6, ComplexNumberNode.sum(new ComplexNumberNode(-2), new ComplexNumberNode(8)).baseRepresentation());
		assertEquals(-6, ComplexNumberNode.sum(new ComplexNumberNode(-8), new ComplexNumberNode(2)).baseRepresentation());
		
		assertEquals(-2, ComplexNumberNode.sum(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-192, ComplexNumberNode.sum(new ComplexNumberNode(-200), new ComplexNumberNode(8)).baseRepresentation());
		assertEquals(-200, ComplexNumberNode.sum(new ComplexNumberNode(800), new ComplexNumberNode(-1000)).baseRepresentation());
		
		assertEquals(0, ComplexNumberNode.sum(new ComplexNumberNode(0), new ComplexNumberNode(0)).baseRepresentation());
		assertEquals(-108, ComplexNumberNode.sum(new ComplexNumberNode(-100), new ComplexNumberNode(-8)).baseRepresentation());
		assertEquals(0, ComplexNumberNode.sum(new ComplexNumberNode(20), new ComplexNumberNode(-20)).baseRepresentation());
		
		assertEquals(-50, ComplexNumberNode.sum(new ComplexNumberNode(50), new ComplexNumberNode(-100)).baseRepresentation());
		assertEquals(-900, ComplexNumberNode.sum(new ComplexNumberNode(-100), new ComplexNumberNode(-800)).baseRepresentation());
		assertEquals(5, ComplexNumberNode.sum(new ComplexNumberNode(20), new ComplexNumberNode(-15)).baseRepresentation());
		
		assertEquals(10, ComplexNumberNode.sum(new ComplexNumberNode(2), new ComplexNumberNode(8)).baseRepresentation());
	}
	
	public void testSubstraction() throws Exception {
		assertEquals(22, ComplexNumberNode.subtract(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-18, ComplexNumberNode.subtract(new ComplexNumberNode(-20), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-22, ComplexNumberNode.subtract(new ComplexNumberNode(-20), new ComplexNumberNode(2)).baseRepresentation());
		
		assertEquals(4, ComplexNumberNode.subtract(new ComplexNumberNode(2), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-10, ComplexNumberNode.subtract(new ComplexNumberNode(-2), new ComplexNumberNode(8)).baseRepresentation());
		assertEquals(-10, ComplexNumberNode.subtract(new ComplexNumberNode(-8), new ComplexNumberNode(2)).baseRepresentation());
		
		assertEquals(2, ComplexNumberNode.subtract(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(-208, ComplexNumberNode.subtract(new ComplexNumberNode(-200), new ComplexNumberNode(8)).baseRepresentation());
		assertEquals(1800, ComplexNumberNode.subtract(new ComplexNumberNode(800), new ComplexNumberNode(-1000)).baseRepresentation());
		
		assertEquals(0, ComplexNumberNode.subtract(new ComplexNumberNode(0), new ComplexNumberNode(0)).baseRepresentation());
		assertEquals(-92, ComplexNumberNode.subtract(new ComplexNumberNode(-100), new ComplexNumberNode(-8)).baseRepresentation());
		assertEquals(40, ComplexNumberNode.subtract(new ComplexNumberNode(20), new ComplexNumberNode(-20)).baseRepresentation());
		
		assertEquals(150, ComplexNumberNode.subtract(new ComplexNumberNode(50), new ComplexNumberNode(-100)).baseRepresentation());
		assertEquals(700, ComplexNumberNode.subtract(new ComplexNumberNode(-100), new ComplexNumberNode(-800)).baseRepresentation());
		assertEquals(35, ComplexNumberNode.subtract(new ComplexNumberNode(20), new ComplexNumberNode(-15)).baseRepresentation());
		
		assertEquals(-6, ComplexNumberNode.subtract(new ComplexNumberNode(2), new ComplexNumberNode(8)).baseRepresentation());
		assertEquals(-2, ComplexNumberNode.subtract(new ComplexNumberNode(-1), new ComplexNumberNode(1)).baseRepresentation());
		assertEquals(-1200, ComplexNumberNode.subtract(new ComplexNumberNode(-1000), new ComplexNumberNode(200)).baseRepresentation());
		
		assertEquals(1200, ComplexNumberNode.subtract(new ComplexNumberNode(1000), new ComplexNumberNode(-200)).baseRepresentation());
	}
	
	public void testProduct() throws Exception {
		assertEquals(-40, ComplexNumberNode.product(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(40, ComplexNumberNode.product(new ComplexNumberNode(20), new ComplexNumberNode(2)).baseRepresentation());
		assertEquals(441, ComplexNumberNode.product(new ComplexNumberNode(21), new ComplexNumberNode(21)).baseRepresentation());
		
		assertEquals(0, ComplexNumberNode.product(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(121, ComplexNumberNode.product(new ComplexNumberNode(11), new ComplexNumberNode(11)).baseRepresentation());
		assertEquals(19279000, ComplexNumberNode.product(new ComplexNumberNode(154232), new ComplexNumberNode(125)).baseRepresentation());
		
		assertEquals(773429505000l, ComplexNumberNode.product(new ComplexNumberNode(147855), new ComplexNumberNode(5231000)).baseRepresentation());
		assertEquals(0, ComplexNumberNode.product(new ComplexNumberNode(0), new ComplexNumberNode(0)).baseRepresentation());
		assertEquals(-35642, ComplexNumberNode.product(new ComplexNumberNode(-142), new ComplexNumberNode(251)).baseRepresentation());
	}
	
	public void testDivision() throws Exception {
		assertEquals(-10, ComplexNumberNode.divide(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(10, ComplexNumberNode.divide(new ComplexNumberNode(20), new ComplexNumberNode(2)).baseRepresentation());
		assertEquals(1, ComplexNumberNode.divide(new ComplexNumberNode(21), new ComplexNumberNode(21)).baseRepresentation());
		
		assertEquals(0, ComplexNumberNode.divide(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseRepresentation());
		assertEquals(1, ComplexNumberNode.divide(new ComplexNumberNode(11), new ComplexNumberNode(11)).baseRepresentation());
		assertEquals(125, ComplexNumberNode.divide(new ComplexNumberNode(19279000), new ComplexNumberNode(154232)).baseRepresentation());
		
		assertEquals(5231000, ComplexNumberNode.divide(new ComplexNumberNode(773429505000l), new ComplexNumberNode(147855)).baseRepresentation());
		assertEquals(0, ComplexNumberNode.divide(new ComplexNumberNode(0), new ComplexNumberNode(1)).baseRepresentation());
		assertEquals(251, ComplexNumberNode.divide(new ComplexNumberNode(-35642), new ComplexNumberNode(-142)).baseRepresentation());
	}
	
	public void testPower() throws Exception {
		assertEquals(Double.valueOf(Math.pow(2, 10)).longValue(), ComplexNumberNode.power(new ComplexNumberNode(2), new ComplexNumberNode(10)).baseRepresentation());
		assertEquals(Double.valueOf(Math.pow(3, 5)).longValue(), ComplexNumberNode.power(new ComplexNumberNode(3), new ComplexNumberNode(5)).baseRepresentation());
		assertEquals(Double.valueOf(Math.pow(30, 5)).longValue(), ComplexNumberNode.power(new ComplexNumberNode(30), new ComplexNumberNode(5)).baseRepresentation());
	}

	public void testModulus() throws Exception {
		assertEquals(2, ComplexNumberNode.mod(new ComplexNumberNode(2), new ComplexNumberNode(10)).baseRepresentation());
		assertEquals(20, ComplexNumberNode.mod(new ComplexNumberNode(20), new ComplexNumberNode(30)).baseRepresentation());
		assertEquals(4, ComplexNumberNode.mod(new ComplexNumberNode(34), new ComplexNumberNode(30)).baseRepresentation());
		assertEquals(10, ComplexNumberNode.mod(new ComplexNumberNode(220), new ComplexNumberNode(30)).baseRepresentation());
		assertEquals(1, ComplexNumberNode.mod(new ComplexNumberNode(1), new ComplexNumberNode(30)).baseRepresentation());
		assertEquals(0, ComplexNumberNode.mod(new ComplexNumberNode(30), new ComplexNumberNode(30)).baseRepresentation());
		assertEquals(0, ComplexNumberNode.mod(new ComplexNumberNode(0), new ComplexNumberNode(30)).baseRepresentation());
		assertEquals(3, ComplexNumberNode.mod(new ComplexNumberNode(15), new ComplexNumberNode(4)).baseRepresentation());
	}
	
	public void testSquareRoot() throws Exception {
		for (int number = 0; number < 500; number++)
			assertEquals(Double.valueOf(Math.sqrt(number)).longValue(), ComplexNumberNode.squareRoot(new ComplexNumberNode(number)).baseRepresentation());
	}
	
	public void testFactorial() throws Exception {
		assertEquals("6", ComplexNumberNode.factorial(new ComplexNumberNode(3)).toString());
		assertEquals("120", ComplexNumberNode.factorial(new ComplexNumberNode(5)).toString());
		assertEquals("5040", ComplexNumberNode.factorial(new ComplexNumberNode(7)).toString());
		assertEquals("3628800", ComplexNumberNode.factorial(new ComplexNumberNode(10)).toString());
	}
	
}
