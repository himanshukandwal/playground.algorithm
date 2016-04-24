package dev.research.himanshu.algorithm.assignments.assignment8;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class NPKTest {

	@Test
	public void testAlgorithmRun1() {
		setUserInput(5, 5);
		NPK.main(new String[] {});
	}
	
	@Test
	public void testAlgorithmRun2() {
		setUserInput(6, 2);
		NPK.main(new String[] {});
	}
	
	@Test
	public void testAlgorithmRun3() {
		setUserInput(7, 5);
		NPK.main(new String[] {});
	}

	public void setUserInput(int n, int k) {
		String input = n + " " + k;
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	}

}
