package dev.research.himanshu.algorithm.assignments.lp1;

import junit.framework.TestCase;

public class NumberNodeTest extends TestCase {
	
	public void testSum() throws Exception {
<<<<<<< HEAD
		assertEquals(22, NumberNode.sum(new NumberNode(20), new NumberNode(2)).baseValue());
		assertEquals(202, NumberNode.sum(new NumberNode(200), new NumberNode(2)).baseValue());
		assertEquals(10, NumberNode.sum(new NumberNode(2), new NumberNode(8)).baseValue());
		assertEquals(22, NumberNode.sum(new NumberNode(2), new NumberNode(20)).baseValue());
		assertEquals(202, NumberNode.sum(new NumberNode(2), new NumberNode(200)).baseValue());
		assertEquals(400, NumberNode.sum(new NumberNode(200), new NumberNode(200)).baseValue());
	}
	
	public void testSubstraction() throws Exception {
		assertEquals(18, NumberNode.subtract(new NumberNode(20), new NumberNode(2)).baseValue());
		assertEquals(198, NumberNode.subtract(new NumberNode(200), new NumberNode(2)).baseValue());
		assertEquals(-6, NumberNode.subtract(new NumberNode(2), new NumberNode(8)).baseValue());
		assertEquals(-18, NumberNode.subtract(new NumberNode(2), new NumberNode(20)).baseValue());
		assertEquals(-198, NumberNode.subtract(new NumberNode(2), new NumberNode(200)).baseValue());
		assertEquals(0, NumberNode.subtract(new NumberNode(200), new NumberNode(200)).baseValue());
	}
	
	public void testProduct() throws Exception {
		assertEquals(40, NumberNode.product(new NumberNode(20), new NumberNode(2)).baseValue());
		assertEquals(441, NumberNode.product(new NumberNode(21), new NumberNode(21)).baseValue());
		assertEquals(0, NumberNode.product(new NumberNode(0), new NumberNode(2)).baseValue());
		assertEquals(121, NumberNode.product(new NumberNode(11), new NumberNode(11)).baseValue());
		assertEquals(19279000, NumberNode.product(new NumberNode(154232), new NumberNode(125)).baseValue());
		assertEquals(773429505000l, NumberNode.product(new NumberNode(147855), new NumberNode(5231000)).baseValue());
		assertEquals(0, NumberNode.product(new NumberNode(0), new NumberNode(0)).baseValue());
	}
	
	public void testDivision() throws Exception {
		assertEquals(10, NumberNode.divide(new NumberNode(20), new NumberNode(2)).baseValue());
		assertEquals(1, NumberNode.divide(new NumberNode(21), new NumberNode(21)).baseValue());
		assertEquals(1, NumberNode.divide(new NumberNode(11), new NumberNode(11)).baseValue());
		assertEquals(125, NumberNode.divide(new NumberNode(19279000), new NumberNode(154232)).baseValue());
		assertEquals(5231000, NumberNode.divide(new NumberNode(773429505000l), new NumberNode(147855)).baseValue());
		assertEquals(0, NumberNode.divide(new NumberNode(0), new NumberNode(1)).baseValue());
=======
		assertEquals(18, NumberNode.sum(new NumberNode(20), new NumberNode(-2)).baseValue());
		assertEquals(-22, NumberNode.sum(new NumberNode(-20), new NumberNode(-2)).baseValue());
		assertEquals(-18, NumberNode.sum(new NumberNode(-20), new NumberNode(2)).baseValue());
		
		assertEquals(0, NumberNode.sum(new NumberNode(2), new NumberNode(-2)).baseValue());
		assertEquals(6, NumberNode.sum(new NumberNode(-2), new NumberNode(8)).baseValue());
		assertEquals(-6, NumberNode.sum(new NumberNode(-8), new NumberNode(2)).baseValue());
		
		assertEquals(-2, NumberNode.sum(new NumberNode(0), new NumberNode(-2)).baseValue());
		assertEquals(-192, NumberNode.sum(new NumberNode(-200), new NumberNode(8)).baseValue());
		assertEquals(-200, NumberNode.sum(new NumberNode(800), new NumberNode(-1000)).baseValue());
		
		assertEquals(0, NumberNode.sum(new NumberNode(0), new NumberNode(0)).baseValue());
		assertEquals(-108, NumberNode.sum(new NumberNode(-100), new NumberNode(-8)).baseValue());
		assertEquals(0, NumberNode.sum(new NumberNode(20), new NumberNode(-20)).baseValue());
		
		assertEquals(-50, NumberNode.sum(new NumberNode(50), new NumberNode(-100)).baseValue());
		assertEquals(-900, NumberNode.sum(new NumberNode(-100), new NumberNode(-800)).baseValue());
		assertEquals(5, NumberNode.sum(new NumberNode(20), new NumberNode(-15)).baseValue());
		
		assertEquals(10, NumberNode.sum(new NumberNode(2), new NumberNode(8)).baseValue());
	}
	
	public void testSubstraction() throws Exception {
		assertEquals(22, NumberNode.subtract(new NumberNode(20), new NumberNode(-2)).baseValue());
		assertEquals(-18, NumberNode.subtract(new NumberNode(-20), new NumberNode(-2)).baseValue());
		assertEquals(-22, NumberNode.subtract(new NumberNode(-20), new NumberNode(2)).baseValue());
		
		assertEquals(4, NumberNode.subtract(new NumberNode(2), new NumberNode(-2)).baseValue());
		assertEquals(-10, NumberNode.subtract(new NumberNode(-2), new NumberNode(8)).baseValue());
		assertEquals(-10, NumberNode.subtract(new NumberNode(-8), new NumberNode(2)).baseValue());
		
		assertEquals(2, NumberNode.subtract(new NumberNode(0), new NumberNode(-2)).baseValue());
		assertEquals(-208, NumberNode.subtract(new NumberNode(-200), new NumberNode(8)).baseValue());
		assertEquals(1800, NumberNode.subtract(new NumberNode(800), new NumberNode(-1000)).baseValue());
		
		assertEquals(0, NumberNode.subtract(new NumberNode(0), new NumberNode(0)).baseValue());
		assertEquals(-92, NumberNode.subtract(new NumberNode(-100), new NumberNode(-8)).baseValue());
		assertEquals(40, NumberNode.subtract(new NumberNode(20), new NumberNode(-20)).baseValue());
		
		assertEquals(150, NumberNode.subtract(new NumberNode(50), new NumberNode(-100)).baseValue());
		assertEquals(700, NumberNode.subtract(new NumberNode(-100), new NumberNode(-800)).baseValue());
		assertEquals(35, NumberNode.subtract(new NumberNode(20), new NumberNode(-15)).baseValue());
		
		assertEquals(-6, NumberNode.subtract(new NumberNode(2), new NumberNode(8)).baseValue());
		assertEquals(-2, NumberNode.subtract(new NumberNode(-1), new NumberNode(1)).baseValue());
		assertEquals(-1200, NumberNode.subtract(new NumberNode(-1000), new NumberNode(200)).baseValue());
		
		assertEquals(1200, NumberNode.subtract(new NumberNode(1000), new NumberNode(-200)).baseValue());
	}
	
	public void testProduct() throws Exception {
		assertEquals(-40, NumberNode.product(new NumberNode(20), new NumberNode(-2)).baseValue());
		assertEquals(40, NumberNode.product(new NumberNode(20), new NumberNode(2)).baseValue());
		assertEquals(441, NumberNode.product(new NumberNode(21), new NumberNode(21)).baseValue());
		
		assertEquals(0, NumberNode.product(new NumberNode(0), new NumberNode(-2)).baseValue());
		assertEquals(121, NumberNode.product(new NumberNode(11), new NumberNode(11)).baseValue());
		assertEquals(19279000, NumberNode.product(new NumberNode(154232), new NumberNode(125)).baseValue());
		
		assertEquals(773429505000l, NumberNode.product(new NumberNode(147855), new NumberNode(5231000)).baseValue());
		assertEquals(0, NumberNode.product(new NumberNode(0), new NumberNode(0)).baseValue());
		assertEquals(-35642, NumberNode.product(new NumberNode(-142), new NumberNode(251)).baseValue());
	}
	
	public void testDivision() throws Exception {
		assertEquals(-10, NumberNode.divide(new NumberNode(20), new NumberNode(-2)).baseValue());
		assertEquals(10, NumberNode.divide(new NumberNode(20), new NumberNode(2)).baseValue());
		assertEquals(1, NumberNode.divide(new NumberNode(21), new NumberNode(21)).baseValue());
		
		assertEquals(0, NumberNode.divide(new NumberNode(0), new NumberNode(-2)).baseValue());
		assertEquals(1, NumberNode.divide(new NumberNode(11), new NumberNode(11)).baseValue());
		assertEquals(125, NumberNode.divide(new NumberNode(19279000), new NumberNode(154232)).baseValue());
		
		assertEquals(5231000, NumberNode.divide(new NumberNode(773429505000l), new NumberNode(147855)).baseValue());
		assertEquals(0, NumberNode.divide(new NumberNode(0), new NumberNode(1)).baseValue());
		assertEquals(251, NumberNode.divide(new NumberNode(-35642), new NumberNode(-142)).baseValue());
>>>>>>> 56b822adb529f12b546b095f51f51e77f89566c2
	}
	
	public void testPower() throws Exception {
		assertEquals(4, NumberNode.power(new NumberNode(2), 2).baseValue());
		assertEquals(8, NumberNode.power(new NumberNode(2), 3).baseValue());
		assertEquals(16, NumberNode.power(new NumberNode(2), 4).baseValue());
		assertEquals(32, NumberNode.power(new NumberNode(2), 5).baseValue());
		assertEquals(64, NumberNode.power(new NumberNode(2), 6).baseValue());
		assertEquals(128, NumberNode.power(new NumberNode(2), 7).baseValue());
		
	}
	
}
