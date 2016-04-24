package dev.research.himanshu.algorithm.assignments.lp4;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

public class MDSItemStorageTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testComputeDelta() throws Exception {
		List<Long> oldInput;
		List<Long> newInput;
		
		oldInput = Arrays.asList(1l, 2l, 3l);
		newInput = null;
		assertNull(callComputeDelta(newInput, oldInput));
		
		oldInput = null;
		newInput = Arrays.asList(1l, 2l, 3l);
		assertNull(callComputeDelta(newInput, oldInput));
		
		oldInput = Arrays.asList(1l, 2l, 3l);
		newInput = Arrays.asList(2l, 3l);
		assertEquals(1, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(0).size());
		assertEquals(0, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(1).size());
		
		oldInput = Arrays.asList(2l, 3l);
		newInput = Arrays.asList(2l, 3l);
		assertEquals(0, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(0).size());
		assertEquals(0, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(1).size());
		
		oldInput = Arrays.asList(2l, 3l);
		newInput = Arrays.asList(4l, 5l, 6l);
		assertEquals(2, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(0).size());
		assertEquals(3, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(1).size());
		
		oldInput = Arrays.asList(2l, 3l);
		newInput = Arrays.asList(-1l, 2l, 0l, 4l, 6l);
		assertEquals(1, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(0).size());
		assertEquals(4, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(1).size());
		
		oldInput = null;
		newInput = Arrays.asList(-1l, 2l, 0l, 4l, 6l);
		assertNull(callComputeDelta(newInput, oldInput));
		
		oldInput = Arrays.asList(475l, 1238l, 9742l);
		newInput = Arrays.asList(0l, 475l, 1238l);
		assertEquals(1, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(0).size());
		assertEquals(1, ((List<List<Long>>) callComputeDelta(newInput, oldInput)).get(1).size());
	}
	
	@Test
	public void testTreeMapProperties() throws Exception {
		TreeMap<Integer, Integer> sampleMap = new TreeMap<>();
		
		// populate the map.
		for (int val = 4; val <= 20; val ++) 
			sampleMap.put(val, val);
		
		assertEquals(Integer.valueOf(4), sampleMap.firstKey());
		assertEquals(Integer.valueOf(20), sampleMap.lastKey());
		assertEquals(9, sampleMap.subMap(7, true, 15, true).size());
		assertEquals(8, sampleMap.subMap(7, true, 15, false).size());
		assertEquals(7, sampleMap.subMap(7, false, 15, false).size());
		assertEquals(8, sampleMap.subMap(7, 15).size()); 					// same as : sampleMap.subMap(7, true, 15, false)
		assertEquals(Integer.valueOf(4), sampleMap.ceilingKey(2));
		assertNull(sampleMap.ceilingKey(21));
		assertNull(sampleMap.floorKey(2));
		assertEquals(Integer.valueOf(20), sampleMap.floorKey(25));
		assertEquals(0, sampleMap.subMap(2, false, 4, false).size());
		assertEquals(1, sampleMap.subMap(2, false, 4, true).size());
		assertEquals(1, sampleMap.subMap(19, false, 25, true).size());
		assertEquals(2, sampleMap.subMap(19, true, 25, true).size());
		assertEquals(0, sampleMap.subMap(20, false, 25, false).size());
		assertEquals(0, sampleMap.subMap(21, false, 25, false).size());	
	}
	
	private Object callComputeDelta(List<Long> newInput, List<Long> oldInput) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Object result = null;
		try {
			Method method = AbstractItemStorage.class.getDeclaredMethod("computeDelta", new Class[] {List.class, List.class});
			method.setAccessible(true);
			result = method.invoke(new MDS().getStorage(), newInput, oldInput);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}