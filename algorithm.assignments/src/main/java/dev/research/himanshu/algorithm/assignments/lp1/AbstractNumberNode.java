package dev.research.himanshu.algorithm.assignments.lp1;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class AbstractNumberNode {
	
	private List<Integer> value;
	
	/**
	 * getter function for input list
	 * 
	 * lazy initialization of value.
	 * 
	 */
	protected List<Integer> getValue() {
		if (value == null)
			value = new LinkedList<>();
		return value;
	}

	/**
	 * setter function for input list
	 */
	protected void setValue(List<Integer> input) {
		this.value = input;
	}
	
	/**
	 * helper function to fetch the last element of a list.
	 * 
	 * @return
	 */
	protected Integer getLast() {
		return (getValue() != null && getValue().size() > 0 ? getValue().get(getValue().size() - 1) : null);
	}
	
	/**
	 * Constructor with the Direct value
	 * 
	 * @param stringVal
	 */
	protected List<Integer> copyValue() {
		return new LinkedList<>(getValue());
	}
	
	/**
	 * helper function returning the negative value of a number node.
	 * 
	 * @param stringVal
	 */
	protected void negateValue() {
		ListIterator<Integer> valueListIterator = getValue().listIterator();
		while (valueListIterator.hasNext())
			valueListIterator.next();
		
		valueListIterator.set(getLast() * -1);
	}
	
	/**
	 * helper function to remove the trailing zeroes.
	 * 
	 * @return
	 */
	protected void removeTrailingZerosFromValue() {
		ListIterator<Integer> valueListIterator = getValue().listIterator();
		while (valueListIterator.hasNext())
			valueListIterator.next();
		
		while (valueListIterator.hasPrevious() && (valueListIterator.previous() == 0)) 
			valueListIterator.remove();
		
		if (getValue().size() == 0)
			getValue().add(0);
	}

	public abstract int getBaseValue();
	
	/**
	 * helper function that computes the long value of the numberNode representation.
	 * 
	 * @return
	 */
	public long baseValue() {
		long resultingNumber = 0;
		int counter = 0;
		
		boolean foundNegative = false;
		for (Integer integer : getValue()) {
			if (integer < 0) {
				foundNegative = true;
				integer *= -1;
			}
			resultingNumber += integer * Math.pow(getBaseValue(), counter ++);
		}
		
		return (foundNegative ? resultingNumber * -1 : resultingNumber) ; 
	}
	
	/**
	 * overridden toString method.
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		
		for (Integer element : getValue())
			sb.append(element).append(" ");
		
		sb.append("] ");
		return sb.toString();
	}
	
	/**
	 * helper function to compare two values.
	 *  
	 * @param otherValue
	 * @return
	 */
	protected Integer compareValues(List<Integer> otherValue) {
		Integer returnVal = 0;
		ListIterator<Integer> firstValueListIterator = getValue().listIterator();
		ListIterator<Integer> secondValueListIterator = otherValue.listIterator();
		
		while (firstValueListIterator.hasNext())
			firstValueListIterator.next();
		
		while (secondValueListIterator.hasNext())
			secondValueListIterator.next();

		while (firstValueListIterator.hasPrevious() && secondValueListIterator.hasPrevious()) {
			Integer firstValue = firstValueListIterator.previous();
			Integer secondValue = secondValueListIterator.previous();
			
			if (firstValue > secondValue) { 
				returnVal = 1;
				break;
			} else if (firstValue < secondValue) { 
				returnVal = -1;
				break;
			}
		}
		
		return returnVal;
	}
	
}
