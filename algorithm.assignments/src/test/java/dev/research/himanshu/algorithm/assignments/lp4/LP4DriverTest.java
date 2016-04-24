package dev.research.himanshu.algorithm.assignments.lp4;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.DEFAULT)
public class LP4DriverTest {
	
	private static final String inputDirectory = System.getProperty("user.dir") + "/src/main/resources/lp4/";
	
	@Test
	public void testCustomRun1() throws Exception {
		String file = "lp4-1.txt";
		System.out.println(" >> executing file : " + file + " , expected : 1450.08 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRun2() throws Exception {
		String file = "lp4-2.txt";
		System.out.println(" >> executing file : " + file + " , expected : 4146.32 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRun1k() throws Exception {
		String file = "lp4-3-1k.txt";
		System.out.println(" >> executing file : " + file + " , expected : 52252.36 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRun5k() throws Exception {
		String file = "lp4-4-5k.txt";
		System.out.println(" >> executing file : " + file + " , expected : 490409.01 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}

	@Test
	public void testCustomRun5ck() throws Exception {
		String file = "lp4-5-ck.txt";
		System.out.println(" >> executing file : " + file + " , expected : 173819092858.24 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}

	@Test
	public void testCustomRunSameSame() throws Exception {
		String file = "lp4-same.txt";
		System.out.println(" >> executing file : " + file + " , expected : 22 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test @Ignore
	public void testCustomRunBad() throws Exception {
		String file = "lp4-bad.txt";
		System.out.println(" >> executing file : " + file + " , expected : 1016105017.96 OR 1016105033.26 ");
		LP4VerboseDriver.main(new String[] { inputDirectory + file });
	}
	
	/* test data */
	@Test
	public void testCustomRunT1() throws Exception {
		String file = "lp4-t1.txt";
		System.out.println(" >> executing file : " + file + " , expected : 830.28 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRunT2() throws Exception {
		String file = "lp4-t2.txt";
		System.out.println(" >> executing file : " + file + " , expected : 291968.85 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRunT3() throws Exception {
		String file = "lp4-t3.txt";
		System.out.println(" >> executing file : " + file + " , expected : 660133681.32 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRunT4() throws Exception {
		String file = "lp4-t4.txt";
		System.out.println(" >> executing file : " + file + " , expected : 36158262404720.20 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRunT5() throws Exception {
		String file = "lp4-t5.txt";
		System.out.println(" >> executing file : " + file + " , expected : 10124.00 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
	@Test
	public void testCustomRunT6() throws Exception {
		String file = "lp4-t6.txt";
		System.out.println(" >> executing file : " + file + " , expected : 50966.00 ");
		LP4Driver.main(new String[] { inputDirectory + file });
	}
	
}