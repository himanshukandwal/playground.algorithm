package dev.research.himanshu.algorithm.assignments.assignment5;

/**
 * A simple class to generate hashcode for any specific class.
 * 
 * This class generates polynomially distributed hash code 
 * depending in the features/data elements of the object.
 * 
 * hashValue = value * (hashcodeFactor)^n + value * (hashcodeFactor) ^ (n-1) + value * (hashcodeFactor) ^ (n-2)
 *  
 * @author G31
 *
 */
public class HashCodeGenerator {

	private final int hashcodeFactor = 37;
	private int hashValue;
	
	public HashCodeGenerator() {}

	public int getHashValue() {
		return hashValue;
	}
	
	/**
     * build hashcode with specified value. 
     *
     *@param value
     * @return this
     */
    public HashCodeGenerator withValue(char value) {
    	hashValue = hashValue * hashcodeFactor + value;
        return this;
    }
    
	/**
     * build hashcode with specified value. 
     *
     *@param value
     * @return this
     */
    public HashCodeGenerator withValue(int value) {
    	hashValue = hashValue * hashcodeFactor + value;
        return this;
    }
    
	/**
     * build hashcode with specified value. 
     *
     *@param value
     * @return this
     */
    public HashCodeGenerator withValue(String value) {
    	for (int index = 0; index < value.length(); index++)
    		withValue(value.charAt(index));
    	
        return this;
    }
    
    /**
     * build hashcode with specified value. 
     *
     *@param value
     * @return this
     */
	public HashCodeGenerator ifNotNullThenWithValue(String value) {
		if (value != null) {
			withValue(value.hashCode());
		}
		return this;
	}
	
}
