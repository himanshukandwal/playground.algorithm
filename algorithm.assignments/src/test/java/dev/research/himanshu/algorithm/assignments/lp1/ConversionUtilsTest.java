package dev.research.himanshu.algorithm.assignments.lp1;

import junit.framework.TestCase;

public class ConversionUtilsTest extends TestCase {

	public void testConversion() throws Exception {
		assertEquals(true, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase(1562322, 12), 1562322, 12));
		assertEquals(true, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase(1562322, 576), 1562322, 576));
		assertEquals(true, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase(0, 576), 0, 576));
		assertEquals(false, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase(-10, 10), 10, 10));
		assertEquals(true, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase(-10, 10), -10, 10));
		assertEquals(true, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase("100", 70), 100, 70));
		assertEquals(false, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase("100", 70), 100, 72));
		assertEquals(true, ConversionUtils.verifyBaseRepresentation(ConversionUtils.convertToBase("1234567890123456789012345678901234567890", 10), "1234567890123456789012345678901234567890", 10));
	}
	
}
