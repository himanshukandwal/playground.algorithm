package dev.research.himanshu.algorithm.assignments.assignment4;

/**
 * Compare the running times of the O(n) and O(log n) algorithms for computing f_n % p, where f_n is the nth Fibonacci number. 
 * 
 * Write the following functions and compare their running times for large values of n.
 * 
 * @author G31
 *
 */
public class FibonacciNumbers {

	private static long startTime, endTime, elapsedTime;
	private static long phase = 0;

	static long[][] identity = { { 1, 1 }, { 1, 0 } };

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	public static long linearFibonacci(long n, long p) {
		long a = 1;
		long b = 1;
		long c = 0;
		for (int i = 3; i <= n; i++) {
			c = (a + b) % p;
			a = b % p;
			b = c;
		}
		return c;
	}

	public static long logFibonacci(long n, long p) {
		long[][] temp = identity;
		if (n == 0)
			return 0;
		else if (n == 1 || n == 2)
			return 1;
		else {
			long[][] res = matPower(temp, n - 1, p);

			return res[0][0];
		}
	}

	private static long[][] matPower(long[][] temp, long l, long p) {
		if (l == 1 || l == 0)
			return identity;
		else {
			long[][] half = matPower(temp, l / 2, p);
			long[][] res = matMulti(half, p);
			if (l % 2 == 0)
				return res;
			else {
				long[][] ret = new long[2][2];
				long a = (res[0][0] + res[0][1]) % p;
				long b = res[0][0] % p;
				long c = (res[1][0] + res[1][1]) % p;
				long d = (res[1][0]) % p;
				ret[0][0] = a;
				ret[0][1] = b;
				ret[1][0] = c;
				ret[1][1] = d;
				return ret;
			}
		}
	}

	private static long[][] matMulti(long[][] res, long p) {
		long[][] ret = new long[2][2];
		long a = (res[0][0] * res[0][0] + res[0][1] * res[1][0]) % p;
		long b = (res[0][0] * res[0][1] + res[0][1] * res[1][1]) % p;
		long c = (res[1][0] * res[0][0] + res[1][1] * res[1][0]) % p;
		long d = (res[1][0] * res[0][1] + res[1][1] * res[1][1]) % p;

		ret[0][0] = a;
		ret[0][1] = b;
		ret[1][0] = c;
		ret[1][1] = d;
		return ret;
	}

	public static void main(String[] args) {		
		long p = 999953;

		runFibonacci(8000000l, p);
		runFibonacci(80000000l, p);
		runFibonacci(800000000l, p);
		runFibonacci(8000000000000l, p);
	}
	
	private static void runFibonacci (long range, long p) {
		System.out.println("------------------------------------------------------------------------");
		
		System.out.println("\nDetecting (linear) fibonacci value for position " + range);
		timer();
		System.out.println(linearFibonacci(range, p));
		timer();

		System.out.println("\nDetecting (log) fibonacci value for position " + range);
		timer();
		System.out.println(logFibonacci(range, p));
		timer();
	
	}
	

}