package dev.research.himanshu.algorithm;

import junit.framework.TestCase;

public class MinimumPatchesTest extends TestCase {

	public void testDemoPatch1() throws Exception {
		assertEquals(1, MinimumPatches.minPatches(new int[] { 1, 3 }, 6));
	}

	public void testDemoPatch2() throws Exception {
		assertEquals(1, MinimumPatches.minPatches(new int[] { 1, 5, 10 }, 20));
	}

	public void testDemoPatch3() throws Exception {
		assertEquals(0, MinimumPatches.minPatches(new int[] { 1, 2, 31, 33 }, 2147483647));
	}

	public void testDemoPatch4() throws Exception {
		assertEquals(1, MinimumPatches 
				.minPatches(
						new int[] { 2, 3, 3, 4, 6, 8, 8, 10, 10, 10, 12, 13, 13, 14, 15, 15, 16, 17, 19, 20, 20,
								21, 21, 21, 23, 23, 24, 25, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31, 31, 32, 32,
								32, 36, 36, 38, 41, 41, 41, 43, 44, 46, 46, 46, 48, 48, 49, 50, 51, 51, 52, 52,
								53, 54, 55, 56, 56, 58, 58, 58, 59, 60, 60, 60, 61, 63, 63, 66, 66, 70, 70, 73,
								74, 74, 75, 78, 80, 81, 83, 85, 87, 87, 89, 89, 89, 90, 90, 92, 92, 96, 98 },
						60844));
				
	}

}
