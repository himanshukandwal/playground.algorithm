package dev.research.himanshu.algorithm.assignments.lp4;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test cases for MDS (multi-dimensional data structure)
 * 
 * @author Heman
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MDSTest {

	private static MDS mds = new MDS();
	
	public MDS getMds() {
		return mds;
	}
	
	@Test
	public void a_testInsertOperation() throws Exception {
		
		/* adding 1st product */
		{
			assertEquals(1, getMds().insert(22, 19.97, new long[] { 475, 1238, 9742 }, 3));
			assertEquals(0, getMds().insert(22, 19.90, new long[] { 475, 1238, 100 }, 3));
			
			Item item = getMds().findItem(22l);
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.97));
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.90).size());
			assertTrue(getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.90).contains(item));
			
			assertEquals(0, getMds().insert(22, 19.97, new long[] { 475, 1238, 100 }, 3));
			
			// check description data-set
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(9742l));

			// check prices data-set(s)
			assertEquals(1, getMds().getStorage().getPriceIdsMap().get(19.97d).size());
			{
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(475l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1238l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(100l).size());
				
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.90));
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.97).size());
				assertTrue(getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.97).contains(item));
				
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(1238l).get(19.90));
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1238l).get(19.97).size());
				assertTrue(getMds().getStorage().getDescriptionIdsMap().get(1238l).get(19.97).contains(item));
				
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(100l).get(19.90));
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(100l).get(19.97).size());
				assertTrue(getMds().getStorage().getDescriptionIdsMap().get(100l).get(19.97).contains(item));
			}
		}

		/* adding 2nd product */
		{
			assertEquals(1, getMds().insert(21, 22.97, new long[] { 475, 1238, 17 }, 3));
			
			assertEquals(0, getMds().insert(21, 21.97, null, 0));
			assertFalse(getMds().getStorage().getDescriptionIdsMap().get(475l).containsKey(22.97));
			assertTrue(getMds().getStorage().getDescriptionIdsMap().get(475l).containsKey(21.97));
			assertFalse(getMds().getStorage().getDescriptionIdsMap().get(1238l).containsKey(22.97));
			assertTrue(getMds().getStorage().getDescriptionIdsMap().get(1238l).containsKey(21.97));
			
			assertEquals(0, getMds().insert(21, 22.97, new long[] { 142 }, 1));
			
			Item item = getMds().findItem(21l);
			
			// check description data-set
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(17l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(9742l));
			
			// check prices data-set(s)
			assertEquals(1, getMds().getStorage().getPriceIdsMap().get(19.97d).size());
			assertEquals(1, getMds().getStorage().getPriceIdsMap().get(22.97d).size());
			{
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(475l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1238l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(100l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(142l).size());
				
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(475l).get(22.97));
				assertFalse(getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.97).contains(item));
				
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(1238l).get(22.97));
				assertFalse(getMds().getStorage().getDescriptionIdsMap().get(1238l).get(19.97).contains(item));
				
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97).size());
				assertTrue(getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97).contains(item));
			}
		}

		/* adding 3rd product */
		{
			assertEquals(1, getMds().insert(20, 22.97, new long[] { 142, 9742, 17 }, 3));
			assertEquals(0, getMds().insert(20, 22.97, new long[] { 142, 9742, 17, 1, 19, 45, 34 }, 7));
			assertEquals(0, getMds().insert(20, 22.97, new long[] { 142, 9742, 17, 1, 19, 45, 34 }, 7));

			Item item = getMds().findItem(21l);
			
			// check description data-set
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(475l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1238l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(100l).size());
			assertEquals(2, getMds().getStorage().getDescriptionIdsMap().get(142l).get(item.getPrice()).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(9742l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(17l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(19l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(45l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(34l).size());
			
			// check prices data-set(s)
			assertEquals(1, getMds().getStorage().getPriceIdsMap().get(19.97d).size());
			assertEquals(2, getMds().getStorage().getPriceIdsMap().get(22.97d).size());
			{
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(475l).size());
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(475l).get(22.97));
				assertFalse(getMds().getStorage().getDescriptionIdsMap().get(475l).get(19.97).contains(item));
				
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1238l).size());
				assertNull(getMds().getStorage().getDescriptionIdsMap().get(1238l).get(22.97));
				assertFalse(getMds().getStorage().getDescriptionIdsMap().get(1238l).get(19.97).contains(item));
				
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(100l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(142l).size());
				assertEquals(2, getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97d).size());
				assertTrue(getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97d).contains(item));
				assertTrue(getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97d).contains(getMds().findItem(21l)));
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(9742l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(17l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(1l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(19l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(45l).size());
				assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(34l).size());
			}
		}
	}
	
	@Test
	public void b_testFindOperation() throws Exception {
		// find price
		assertEquals(Double.valueOf(19.97d), Double.valueOf(getMds().find(22)));
		assertEquals(Double.valueOf(22.97d), Double.valueOf(getMds().find(21)));
		assertEquals(Double.valueOf(0d), Double.valueOf(getMds().find(1)));
		
		// find item
		assertNotNull(getMds().findItem(22));
		assertNotNull(getMds().findItem(21));
		assertNull(getMds().findItem(1));
	}
	
	@Test
	public void c_testDeleteOperation() throws Exception {
		// create and delete first item
		{
			// create
			assertEquals(1, getMds().insert(1, 100.97, new long[] { 2, 3, 4 }, 3));
			assertEquals(0, getMds().insert(1, 100.97, new long[] { 2, 20, 3 }, 3));
			
			// delete
			assertEquals(25, getMds().delete(1l));
			
			//confirm deletion
			assertNull(getMds().getStorage().getIdItemsMap().get(2l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(2l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(3l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(4l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(20l));
			
			assertNull(getMds().getStorage().getPriceIdsMap().get(100.97d));
		}
		
		// create and delete second item
		{
			// create
			assertEquals(1, getMds().insert(2, 15.00, new long[] { 1, 2, 3 }, 3));
			assertEquals(0, getMds().insert(2, 15.55, new long[] { 300, 25, 5 }, 3));
			Item item = getMds().findItem(2l);
			
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(5l).get(15.55).size());
			assertTrue(getMds().getStorage().getDescriptionIdsMap().get(5l).get(15.55).contains(item));
			
			// delete
			assertEquals(330, getMds().delete(2l));
			
			//confirm deletion (here description - 1 has been already used (insert)
			assertNull(getMds().getStorage().getIdItemsMap().get(2l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(2l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(3l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(5l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(300l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(25l));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(1l).get(item.getPrice()));
			assertNull(getMds().getStorage().getDescriptionIdsMap().get(1l).get(15.55d));
			
			assertNull(getMds().getStorage().getPriceIdsMap().get(15.55d));
		}
		
		// delete existing item
		{
			Item item = getMds().findItem(21l);
			assertEquals(2, getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97).size());
			assertTrue(getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97).contains(item));
						
			// delete
			assertEquals(142, getMds().delete(21l));
			
			// check description data-set
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(142l).size());
			assertEquals(1, getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97).size());
			assertFalse(getMds().getStorage().getDescriptionIdsMap().get(142l).get(22.97).contains(item));
		}
	}
	
	@Test
	public void d_testCreateSampleData() throws Exception {
		// creating some more records
		assertEquals(1, getMds().insert(1, 15.00, new long[] { 1, 2, 3 }, 3));
		assertEquals(1, getMds().insert(2, 16.62, new long[] { 4, 5, 6 }, 3));
		assertEquals(1, getMds().insert(3, 17.79, new long[] { 7, 8, 9 }, 3));
		assertEquals(1, getMds().insert(4, 18.85, new long[] { 10, 11, 12 }, 3));
		assertEquals(1, getMds().insert(5, 20.20, new long[] { 142 }, 1));
		assertEquals(1, getMds().insert(6, 23.36, new long[] { 142 }, 1));
		assertEquals(1, getMds().insert(7, 24.48, new long[] { 142, 1, 5 }, 3));
		assertEquals(1, getMds().insert(8, 27.00, new long[] { 1, 5, 7, 2 }, 4));
		assertEquals(0, getMds().insert(8, 27.50, new long[] { 1, 5, 7, 2 }, 4));	
		assertEquals(1, getMds().insert(9, 20.20, new long[] { 142, 5, 7, 2 }, 4));
		assertEquals(1, getMds().insert(10, 27.50, new long[] { 142, 5, 7, 2 }, 4));
		assertEquals(1, getMds().insert(11, 28.80, new long[] { 3, 5, 7 }, 3));
		assertEquals(0, getMds().insert(11, 28.98, new long[] { 1, 9 }, 2));
		assertEquals(1, getMds().insert(12, 17.79, new long[] { 142, 1, 9 }, 3));
		assertEquals(1, getMds().insert(13, 22.97, new long[] { 11, 12, 34, 45 }, 4));
		assertEquals(1, getMds().insert(14, 16.62, new long[] { 12, 4, 5, 34, 45, 142 }, 6));
		
		System.out.println(getMds().getStorage().getDescriptionIdsMap());
		System.out.println(getMds().getStorage().getPriceIdsMap());
	}
	
	/* data at this point :
	 * {
	 * 		1={ 
	 * 			{1=1, 7=7, 8=8, 11=11, 12=12, 20=20} , 
	 * 			{15.0=[1], 17.79=[12], 22.97=[20], 24.48=[7], 27.5=[8], 28.98=[11]} 
	 * 		  } , 
	 * 		2={ {1=1, 8=8, 9=9, 10=10} , {15.0=[1], 20.2=[9], 27.5=[8, 10]} } , 
	 * 		3={ {1=1} , {15.0=[1]} } , 
	 * 		4={ {2=2, 14=14} , {16.62=[2, 14]} } , 
	 * 		5={ {2=2, 7=7, 8=8, 9=9, 10=10, 14=14} , {16.62=[2, 14], 20.2=[9], 24.48=[7], 27.5=[8, 10]} } , 
	 * 		6={ {2=2} , {16.62=[2]} } , 
	 * 		7={ {3=3, 8=8, 9=9, 10=10} , {17.79=[3], 20.2=[9], 27.5=[8, 10]} } , 
	 * 		8={ {3=3} , {17.79=[3]} } , 
	 * 		9={ {3=3, 11=11, 12=12} , {17.79=[12, 3], 28.98=[11]} } , 
	 * 		10={ {4=4} , {18.85=[4]} } , 
	 * 		11={ {4=4, 13=13} , {18.85=[4], 22.97=[13]} } , 
	 * 		12={ {4=4, 13=13, 14=14} , {16.62=[14], 18.85=[4], 22.97=[13]} } , 
	 * 		17={ {20=20} , {22.97=[20]} } , 
	 * 		19={ {20=20} , {22.97=[20]} } , 
	 * 		34={ {13=13, 14=14, 20=20} , {16.62=[14], 22.97=[20, 13]} } , 
	 * 		45={ {13=13, 14=14, 20=20} , {16.62=[14], 22.97=[20, 13]} } , 
	 * 		100={ {22=22} , {19.97=[22]} } , 
	 * 		142={ 
	 * 				{5=5, 6=6, 7=7, 9=9, 10=10, 12=12, 14=14, 20=20} , 
	 * 				{16.62=[14], 17.79=[12], 20.2=[5, 9], 22.97=[20], 23.36=[6], 24.48=[7], 27.5=[10]} 
	 * 			} , 
	 * 		475={ {22=22} , {19.97=[22]} } , 
	 * 		1238={ {22=22} , {19.97=[22]} } , 
	 * 		9742={ {20=20} , {22.97=[20]} } 
	 * }
	 */
	
	@Test
	public void d_testFindMinPriceOperation() throws Exception {
		assertEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMinPrice(142)));
		assertEquals(Double.valueOf(15.0d), Double.valueOf(getMds().findMinPrice(1)));
		assertEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMinPrice(4)));
		assertEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMinPrice(5)));
		assertEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMinPrice(6)));
		assertNotEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMinPrice(8)));
		assertEquals(Double.valueOf(0d), Double.valueOf(getMds().findMinPrice(143)));
		assertEquals(Double.valueOf(0d), Double.valueOf(getMds().findMinPrice(144)));
	}

	@Test
	public void e_testFindMaxPriceOperation() throws Exception {
		assertEquals(Double.valueOf(27.5d), Double.valueOf(getMds().findMaxPrice(142)));
		assertEquals(Double.valueOf(28.98d), Double.valueOf(getMds().findMaxPrice(1)));
		assertEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMaxPrice(4)));
		assertEquals(Double.valueOf(27.5d), Double.valueOf(getMds().findMaxPrice(5)));
		assertEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMaxPrice(6)));
		assertNotEquals(Double.valueOf(16.62d), Double.valueOf(getMds().findMaxPrice(8)));
		assertNotEquals(Double.valueOf(19.97d), Double.valueOf(getMds().findMaxPrice(9)));
		assertEquals(Double.valueOf(28.98d), Double.valueOf(getMds().findMaxPrice(9)));
		assertEquals(Double.valueOf(0d), Double.valueOf(getMds().findMaxPrice(143)));
	}

	@Test
	public void f_testFindPriceRangeOperation() throws Exception {
		assertEquals(4, getMds().findPriceRange(142, 0d, 21d));
		assertEquals(4, getMds().findPriceRange(142, 0d, 22d));
		assertEquals(5, getMds().findPriceRange(142, 0d, 23d));
		assertEquals(6, getMds().findPriceRange(142, 0d, 23.5d));
		assertEquals(0, getMds().findPriceRange(142, 22d, 22d));
		assertEquals(0, getMds().findPriceRange(142, 21d, 22d));
		assertEquals(2, getMds().findPriceRange(142, 21d, 23.5d));
		assertEquals(2, getMds().findPriceRange(142, 20d, 22d));
		assertEquals(0, getMds().findPriceRange(142, 23d, 0d));
		assertEquals(3, getMds().findPriceRange(142, 23d, 27.5d));
	}
	
	/* price map details 
	 * {
	 * 		15.0=[1], 
	 * 		16.62=[2, 14], 
	 * 		17.79=[3, 12], 
	 * 		18.85=[4], 
	 * 		19.97=[22], 
	 * 		20.2=[5, 9], 
	 * 		22.97=[13, 20], 
	 * 		23.36=[6], 
	 * 		24.48=[7], 
	 * 		27.5=[8, 10],
	 * 		28.98=[11]
	 * }
	 */
	
	@Test
	public void g_testRangeOperation() throws Exception {
		assertEquals(9, getMds().range(0d, 21d));
		assertEquals(1, getMds().range(0d, 15d));
		assertEquals(0, getMds().range(0d, 10d));
		assertEquals(3, getMds().range(19d, 21d));
		assertEquals(1, getMds().range(28d, 29d));
		assertEquals(0, getMds().range(29d, 30d));
		assertEquals(0, getMds().range(29d, 29.98d));
		assertEquals(1, getMds().range(28d, 28.98d));
	}
	
	@Test
	public void h_testPriceHikeOperation() throws Exception {
		assertEquals(Double.valueOf(2.33d), Double.valueOf(getMds().priceHike(6, 6, 10d)));
		assertFalse(getMds().getStorage().getDescriptionIdsMap().get(142l).containsKey(23.36d));
//		assertTrue(getMds().getStorage().getDescriptionIdsMap().get(142l).containsKey(25.69d));
	}
}
