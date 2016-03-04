package dev.research.himanshu.algorithm.assignments.assignment5;

/**
 * A simple collection class implementing linear hashing for storage.
 * 
 * h(x) = ((a*x + b) mod p) mod size
 * 
 * @author G31
 *
 */
public class HashFunction<T> {
	
	private final int hashPrime = 37;
	private int weight;
	private int bias;
	
	public HashFunction(int weight, int bias) {
		this.weight = weight;
		this.bias = bias;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getBias() {
		return bias;
	}

	public void setBias(int bias) {
		this.bias = bias;
	}
	
	public int hash(T object) {
		int hashValue = ((weight * object.hashCode() + bias) % hashPrime);
		return (hashValue ^ hashValue >>> 16);
	}

}
