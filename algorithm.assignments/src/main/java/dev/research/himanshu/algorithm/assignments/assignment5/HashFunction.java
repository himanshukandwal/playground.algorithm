package dev.research.himanshu.algorithm.assignments.assignment5;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * A simple collection class implementing linear hashing for storage.
 * 
 * h(x) = ((a*x + b) mod p) mod size
 * 
 * @author G31
 *
 */
public class HashFunction<T> {
	
	private final int hashPrime = 31;
	private int weight;
	private int bias;
	private Map<T, Integer> cachedHashcodes;
	
	public Map<T, Integer> getCachedHashcodes() {
		if (cachedHashcodes == null)
			cachedHashcodes = new WeakHashMap<T, Integer>();

		return cachedHashcodes;
	}
	
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
		if (!getCachedHashcodes().containsKey(object)) {
			int hashValue = (hashPrime * weight * object.hashCode()) + bias;
			getCachedHashcodes().put(object, hashValue);
		}
		return getCachedHashcodes().get(object);
	}

}
