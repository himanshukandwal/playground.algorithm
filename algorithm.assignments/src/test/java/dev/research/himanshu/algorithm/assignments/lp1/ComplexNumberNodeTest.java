package dev.research.himanshu.algorithm.assignments.lp1;

import junit.framework.TestCase;

public class ComplexNumberNodeTest extends TestCase {
	
	public void testSum() throws Exception {
		assertEquals(18, ComplexNumberNode.sum(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-22, ComplexNumberNode.sum(new ComplexNumberNode(-20), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-18, ComplexNumberNode.sum(new ComplexNumberNode(-20), new ComplexNumberNode(2)).baseValue());
		
		assertEquals(0, ComplexNumberNode.sum(new ComplexNumberNode(2), new ComplexNumberNode(-2)).baseValue());
		assertEquals(6, ComplexNumberNode.sum(new ComplexNumberNode(-2), new ComplexNumberNode(8)).baseValue());
		assertEquals(-6, ComplexNumberNode.sum(new ComplexNumberNode(-8), new ComplexNumberNode(2)).baseValue());
		
		assertEquals(-2, ComplexNumberNode.sum(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-192, ComplexNumberNode.sum(new ComplexNumberNode(-200), new ComplexNumberNode(8)).baseValue());
		assertEquals(-200, ComplexNumberNode.sum(new ComplexNumberNode(800), new ComplexNumberNode(-1000)).baseValue());
		
		assertEquals(0, ComplexNumberNode.sum(new ComplexNumberNode(0), new ComplexNumberNode(0)).baseValue());
		assertEquals(-108, ComplexNumberNode.sum(new ComplexNumberNode(-100), new ComplexNumberNode(-8)).baseValue());
		assertEquals(0, ComplexNumberNode.sum(new ComplexNumberNode(20), new ComplexNumberNode(-20)).baseValue());
		
		assertEquals(-50, ComplexNumberNode.sum(new ComplexNumberNode(50), new ComplexNumberNode(-100)).baseValue());
		assertEquals(-900, ComplexNumberNode.sum(new ComplexNumberNode(-100), new ComplexNumberNode(-800)).baseValue());
		assertEquals(5, ComplexNumberNode.sum(new ComplexNumberNode(20), new ComplexNumberNode(-15)).baseValue());
		
		assertEquals(10, ComplexNumberNode.sum(new ComplexNumberNode(2), new ComplexNumberNode(8)).baseValue());
	}
	
	public void testSubstraction() throws Exception {
		assertEquals(22, ComplexNumberNode.subtract(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-18, ComplexNumberNode.subtract(new ComplexNumberNode(-20), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-22, ComplexNumberNode.subtract(new ComplexNumberNode(-20), new ComplexNumberNode(2)).baseValue());
		
		assertEquals(4, ComplexNumberNode.subtract(new ComplexNumberNode(2), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-10, ComplexNumberNode.subtract(new ComplexNumberNode(-2), new ComplexNumberNode(8)).baseValue());
		assertEquals(-10, ComplexNumberNode.subtract(new ComplexNumberNode(-8), new ComplexNumberNode(2)).baseValue());
		
		assertEquals(2, ComplexNumberNode.subtract(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseValue());
		assertEquals(-208, ComplexNumberNode.subtract(new ComplexNumberNode(-200), new ComplexNumberNode(8)).baseValue());
		assertEquals(1800, ComplexNumberNode.subtract(new ComplexNumberNode(800), new ComplexNumberNode(-1000)).baseValue());
		
		assertEquals(0, ComplexNumberNode.subtract(new ComplexNumberNode(0), new ComplexNumberNode(0)).baseValue());
		assertEquals(-92, ComplexNumberNode.subtract(new ComplexNumberNode(-100), new ComplexNumberNode(-8)).baseValue());
		assertEquals(40, ComplexNumberNode.subtract(new ComplexNumberNode(20), new ComplexNumberNode(-20)).baseValue());
		
		assertEquals(150, ComplexNumberNode.subtract(new ComplexNumberNode(50), new ComplexNumberNode(-100)).baseValue());
		assertEquals(700, ComplexNumberNode.subtract(new ComplexNumberNode(-100), new ComplexNumberNode(-800)).baseValue());
		assertEquals(35, ComplexNumberNode.subtract(new ComplexNumberNode(20), new ComplexNumberNode(-15)).baseValue());
		
		assertEquals(-6, ComplexNumberNode.subtract(new ComplexNumberNode(2), new ComplexNumberNode(8)).baseValue());
		assertEquals(-2, ComplexNumberNode.subtract(new ComplexNumberNode(-1), new ComplexNumberNode(1)).baseValue());
		assertEquals(-1200, ComplexNumberNode.subtract(new ComplexNumberNode(-1000), new ComplexNumberNode(200)).baseValue());
		
		assertEquals(1200, ComplexNumberNode.subtract(new ComplexNumberNode(1000), new ComplexNumberNode(-200)).baseValue());
	}
	
	public void testProduct() throws Exception {
		assertEquals(-40, ComplexNumberNode.product(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseValue());
		assertEquals(40, ComplexNumberNode.product(new ComplexNumberNode(20), new ComplexNumberNode(2)).baseValue());
		assertEquals(441, ComplexNumberNode.product(new ComplexNumberNode(21), new ComplexNumberNode(21)).baseValue());
		
		assertEquals(0, ComplexNumberNode.product(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseValue());
		assertEquals(121, ComplexNumberNode.product(new ComplexNumberNode(11), new ComplexNumberNode(11)).baseValue());
		assertEquals(19279000, ComplexNumberNode.product(new ComplexNumberNode(154232), new ComplexNumberNode(125)).baseValue());
		
		assertEquals(773429505000l, ComplexNumberNode.product(new ComplexNumberNode(147855), new ComplexNumberNode(5231000)).baseValue());
		assertEquals(0, ComplexNumberNode.product(new ComplexNumberNode(0), new ComplexNumberNode(0)).baseValue());
		assertEquals(-35642, ComplexNumberNode.product(new ComplexNumberNode(-142), new ComplexNumberNode(251)).baseValue());
	}
	
	public void testDivision() throws Exception {
		assertEquals(-10, ComplexNumberNode.divide(new ComplexNumberNode(20), new ComplexNumberNode(-2)).baseValue());
		assertEquals(10, ComplexNumberNode.divide(new ComplexNumberNode(20), new ComplexNumberNode(2)).baseValue());
		assertEquals(1, ComplexNumberNode.divide(new ComplexNumberNode(21), new ComplexNumberNode(21)).baseValue());
		
		assertEquals(0, ComplexNumberNode.divide(new ComplexNumberNode(0), new ComplexNumberNode(-2)).baseValue());
		assertEquals(1, ComplexNumberNode.divide(new ComplexNumberNode(11), new ComplexNumberNode(11)).baseValue());
		assertEquals(125, ComplexNumberNode.divide(new ComplexNumberNode(19279000), new ComplexNumberNode(154232)).baseValue());
		
		assertEquals(5231000, ComplexNumberNode.divide(new ComplexNumberNode(773429505000l), new ComplexNumberNode(147855)).baseValue());
		assertEquals(0, ComplexNumberNode.divide(new ComplexNumberNode(0), new ComplexNumberNode(1)).baseValue());
		assertEquals(251, ComplexNumberNode.divide(new ComplexNumberNode(-35642), new ComplexNumberNode(-142)).baseValue());
	}
	
	public void testPower() throws Exception {
		assertEquals(Double.valueOf(Math.pow(2, 10)).longValue(), ComplexNumberNode.power(new ComplexNumberNode(2), new ComplexNumberNode(10)).baseValue());
		assertEquals(Double.valueOf(Math.pow(3, 1219)).longValue(), ComplexNumberNode.power(new ComplexNumberNode(3), new ComplexNumberNode(1219)).baseValue());
		assertEquals(Double.valueOf(Math.pow(32121, 1219)).longValue(), ComplexNumberNode.power(new ComplexNumberNode(32121), new ComplexNumberNode(1219)).baseValue());
	}
	
	public void testModulus() throws Exception {
		assertEquals(2, ComplexNumberNode.mod(new ComplexNumberNode(2), new ComplexNumberNode(10)).baseValue());
		assertEquals(20, ComplexNumberNode.mod(new ComplexNumberNode(20), new ComplexNumberNode(30)).baseValue());
		assertEquals(4, ComplexNumberNode.mod(new ComplexNumberNode(34), new ComplexNumberNode(30)).baseValue());
		assertEquals(10, ComplexNumberNode.mod(new ComplexNumberNode(220), new ComplexNumberNode(30)).baseValue());
		assertEquals(1, ComplexNumberNode.mod(new ComplexNumberNode(1), new ComplexNumberNode(30)).baseValue());
		assertEquals(0, ComplexNumberNode.mod(new ComplexNumberNode(30), new ComplexNumberNode(30)).baseValue());
		assertEquals(0, ComplexNumberNode.mod(new ComplexNumberNode(0), new ComplexNumberNode(30)).baseValue());
		assertEquals(3, ComplexNumberNode.mod(new ComplexNumberNode(15), new ComplexNumberNode(4)).baseValue());
	}
	
}
