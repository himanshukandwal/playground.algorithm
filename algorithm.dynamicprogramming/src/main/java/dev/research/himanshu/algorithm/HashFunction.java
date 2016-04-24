package dev.research.himanshu.algorithm;

import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

/**
 * A simple collection class implementing linear hashing for storage.
 * 
 * h(x) = (a*p + b)
 * 
 * @author G31
 *
 */
public class HashFunction<T> {
	
    /**
     * hashPrime to use in building the hashCode.
     */
    private final int hashPrime;
    
    /**
     * Running total of the hashCode.
     */
    private int hash = 0;
    
    /**
     * a weak hashmap keeping reference of the pre-computed hashes. 
     */
	private Map<T, Integer> cachedHashcodes;
	
    public HashFunction() {
    	hashPrime = 37;
        hash = 17;
    }
    
	public Map<T, Integer> getCachedHashcodes() {
		if (cachedHashcodes == null)
			cachedHashcodes = new WeakHashMap<T, Integer>();

		return cachedHashcodes;
	}
	
	public HashFunction(int hashPrime, int hash) {
		this.hashPrime = hashPrime;
		this.hash = hash;
	}
	
	
    /**
     * Append a hashCode for a long.
     *
     * @param value  the long to add to the hashCode
     * @return this
     */
    public HashFunction<T> append(long value) {
        hash = hash * hashPrime + ((int) (value ^ (value >> 32)));
        return this;
    }

    /**
     * Append a hashCode for a boolean.
     *
     * @param value  the long to add to the hashCode
     * @return this
     */
    public HashFunction<T> append(boolean value) {
    	hash = hash * hashPrime + (value ? 0 : 1);
        return this;
    }
    
    /**
     * Append a hashCode for a long array.
     *
     * @param array  the array to add to the <code>hashCode</code>
     * @return this
     */
    public HashFunction<T> append(long[] array) {
        if (array == null) {
        	hash = hash * hashPrime;
        } else {
            for (int i = 0; i < array.length; i++) {
                append(array[i]);
            }
        }
        return this;
    }

    /**
     * Return the computed hashcode.
     *
     * @return <code>hashCode</code> based on the fields appended
     */
    public int toHashCode() {
  
        return hash;
    }
    
    
	public int hash(T object) {
		if (!getCachedHashcodes().containsKey(object)) {
			hash = hash * hashPrime + object.hashCode();
			getCachedHashcodes().put(object, hash);
		}
		return getCachedHashcodes().get(object);
	}

}
