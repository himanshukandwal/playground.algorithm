package dev.research.himanshu.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class MinPathTriangleArrayStackTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	@SuppressWarnings("serial")
	public void testMinimumTotal() {
		MinPathTriangleArrayStack arrayStack = new MinPathTriangleArrayStack();
		arrayStack.setTriangle(new ArrayList<List<Integer>>() {{
			add(new ArrayList<Integer>() {{ add(1); }});
			add(new ArrayList<Integer>() {{ add(2); add(3);}});
		}});
		
		assertEquals(3, arrayStack.minimumTotal());
	}

	@SuppressWarnings("serial")
	public void testMinimumTotal2() {
		MinPathTriangleArrayStack arrayStack = new MinPathTriangleArrayStack();
		arrayStack.setTriangle(new ArrayList<List<Integer>>() {{
			add(new ArrayList<Integer>() {{ add(-10); }});
		}});
		
		assertEquals(-10, arrayStack.minimumTotal());
	}
	
	@SuppressWarnings("serial")
	public void testMinimumTotal3() {
		MinPathTriangleArrayStack arrayStack = new MinPathTriangleArrayStack();
		arrayStack.setTriangle(new ArrayList<List<Integer>>() {{
			add(new ArrayList<Integer>() {{ add(-1); }});
			add(new ArrayList<Integer>() {{ add(3); add(2); }});
			add(new ArrayList<Integer>() {{ add(1); add(-2); add(-1); }});
		}});
		
		assertEquals(-1, arrayStack.minimumTotal());
	}
	
}
