package dev.research.himanshu.algorithm.assignments.lp1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author G31
 */
public class NumberNode extends AbstractNumberNode implements Comparable<NumberNode> {

	// Required base in which operations to be performed.
	private static final Integer BASE = 10;

	/**
	 * Default Constructor
	 */
	public NumberNode() {
	}

	/**
	 * Constructor with the long Parameter
	 * 
	 * Convert to required base and calls add to "this" List method(fillNumber).
	 * 
	 * @param stringVal
	 */
	public NumberNode(long longVal) {
		setValue(ConversionUtils.convertToBase(longVal, BASE));
	}

	/**
	 * Constructor with the String Parameter
	 * 
	 * @param stringVal
	 */
	public NumberNode(String stringVal) {
		setValue(ConversionUtils.convertToBase(stringVal, BASE));
	}
	
	/**
	 * overridden abstract method
	 */
	@Override
	public int getBaseValue() {
		return BASE;
	}
	
	/**
	 * Constructor with the Direct value
	 * 
	 * @param stringVal
	 */
	public NumberNode(List<Integer> value) {
		setValue(value);
	}
	
	/**
	 * helper function providing the copy with the Direct value
	 * 
	 * @param stringVal
	 */
	public NumberNode copy() {
		return new NumberNode(copyValue());
	}
	
	/**
	 * helper function returning the negative value of a number node.
	 * 
	 * @param stringVal
	 */
	public NumberNode negate() {
		negateValue();
		return this;
	}
	
	/**
	 * helper function to remove the trailing zeroes.
	 * 
	 * @return
	 */
	public NumberNode removeTrailingZeros() {
		removeTrailingZerosFromValue();
		return this;
	}
	
	/**
	 * helper function to know whether a number is negative or not.
	 * 
	 * @return
	 */
	public boolean isNegative() {
		return getValue().get(getValue().size() - 1) < 0;
	}
	
	/**
	 * compares two NumberNodes
	 */
	@Override
	public int compareTo(NumberNode o) {
		Integer returnVal = 0;
		
		if (getValue().size() > o.getValue().size()) {
			returnVal =  1;
		} else if (getValue().size() < o.getValue().size()) {
			returnVal = -1;	
		} else {
			returnVal = compareValues(o.getValue());
		}
		
		return returnVal; 
	}
	
	/**
	 * function to print the list.
	 */
	public void printList() {
		StringBuffer sb = new StringBuffer();
		sb.append(BASE + " : ");

		for (Integer element : getValue())
			sb.append(element).append(" ");
			
		System.out.println(sb.toString());
	}

	/**
	 * helper function that computes the absolute list.
	 * 
	 * @param list
	 * @return
	 */
	public NumberNode abs() {
		if (!isNegative()) {
			return this;
		} else {
			List<Integer> copyList = new LinkedList<>(getValue());
			NumberNode node = new NumberNode(copyList);
			return node.negate();
		}
	}
	
	/**
	 * function that performs the sum of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a + NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode sum(NumberNode a, NumberNode b) {
		NumberNode c = null;
		
		if (a.isNegative() && b.isNegative()) {
			c = positiveSum(a.negate(), b.negate()).negate();
		} 
		else if (a.isNegative() || b.isNegative()) {
			boolean isBiggerNumberNegative = (a.abs().compareTo(b.abs()) < 0 ? b.isNegative() : a.isNegative());
			
			c = positiveSubtract(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
			
			if ((isBiggerNumberNegative && !c.isNegative()) || (!isBiggerNumberNegative && c.isNegative()))
				c.negate();
		}
		else {
			c = positiveSum(a, b);
		}
		
		return c;
	}
	
	/**
	 * inner function that performs the sum of two positive NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a + NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	private static NumberNode positiveSum(NumberNode a, NumberNode b) {
		List<Integer> x = a.getValue();
		List<Integer> y = b.getValue();

		NumberNode c = new NumberNode();
		List<Integer> z = c.getValue();
		
		int carry = 0;
		
		Iterator<Integer> xIterator = x.iterator();
		Iterator<Integer> yIterator = y.iterator();
		
		while (xIterator.hasNext() || yIterator.hasNext()) {
			
			int addedSum = getNext(xIterator) + getNext(yIterator) + carry;
			
			if (addedSum > 0) {
				List<Integer> innerIntermediateSumList = ConversionUtils.convertToBase(addedSum, BASE);
				if (innerIntermediateSumList.size() > 1) {
					carry = innerIntermediateSumList.get(1);
					addedSum = innerIntermediateSumList.get(0);
				} else {
					carry = 0;
				}
			}
			
			z.add(addedSum);
		}
		
		if (carry != 0)
			z.add(carry);
		
		return c;
	}

	/**
	 * function that performs the difference of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a - NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode subtract(NumberNode a, NumberNode b) {
		NumberNode c = null;
		
		if (a.isNegative() && b.isNegative()) {
			c = positiveSubtract(a.negate(), b.negate()).negate();
		} 
		else if (a.isNegative() || b.isNegative()) {
			boolean toNegate = a.isNegative();
			
			c = positiveSum(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
			
			if (toNegate)
				c.negate();
		}
		else {
			c = positiveSubtract(a, b);
		}
		
		return c;
	}
	
	/**
	 * inner function that performs the difference of two positive NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a - NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	private static NumberNode positiveSubtract(NumberNode a, NumberNode b) {
		a.removeTrailingZeros();
		b.removeTrailingZeros();
		
		if (a.getValue().size() == 1 && a.getValue().get(0) == 0)
			return b.negate();
		else if (b.getValue().size() == 1 && b.getValue().get(0) == 0)
			return a;
		
		boolean negative  = (a.compareTo(b) < 0);
		
		List<Integer> x = (negative ? b.getValue() : a.getValue());
		List<Integer> y = (negative ? a.getValue() : b.getValue());
		
		NumberNode c = new NumberNode();
		List<Integer> z = c.getValue();
		
		int borrow = 0;
		
		Iterator<Integer> xIterator = x.iterator();
		Iterator<Integer> yIterator = y.iterator();
		
		while (xIterator.hasNext()) {
			Integer firstValue = getNext(xIterator);
			if (borrow > 0) {
				firstValue = firstValue - borrow;
				borrow = 0;
			}

			Integer secondValue = getNext(yIterator);
			if (secondValue > firstValue) {
				borrow ++;
				firstValue = firstValue + BASE;
			}
			
			Integer intermediateResult = firstValue - secondValue;
			
			if (intermediateResult > 0)
				intermediateResult = ConversionUtils.convertToBase(intermediateResult, BASE).get(0);
			
			z.add(intermediateResult);
		}
		
		c.removeTrailingZeros();
		return (negative ? c.negate() : c);
	}

	/**
	 * function that performs the multiplication of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a * NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode product(NumberNode a, NumberNode b) {
		NumberNode c = null;
		
		if (a.isNegative() && b.isNegative()) {
			c = positiveProduct(a.negate(), b.negate());
		} 
		else if (a.isNegative() || b.isNegative()) {
			c = positiveProduct(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b).negate();
		}
		else {
			c = positiveProduct(a, b);
		}
		
		return c;
	}
	
	/**
	 * inner function that performs the multiplication of two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a * NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	private static NumberNode positiveProduct(NumberNode a, NumberNode b) {
		
		List<Integer> x = a.getValue();
		List<Integer> y = b.getValue();

		NumberNode c = new NumberNode(0);
		
		Iterator<Integer> xIterator = x.iterator();
		
		int globalCarry = 0;
		int rowOffset = 0;
		
		while (xIterator.hasNext()) {
			int carry = globalCarry;
			
			NumberNode productRowRepresentation = new NumberNode();

			Integer xvalue = xIterator.next();
			Iterator<Integer> yIterator = y.iterator();
			
			int currentRowOffset = rowOffset;
			
			// add the row offset
			while (currentRowOffset != 0) {
				productRowRepresentation.getValue().add(0);
				currentRowOffset --;
			}
			
			while (yIterator.hasNext()) {
				int rowIntermediateProduct = yIterator.next() * xvalue + carry;
			
				if (rowIntermediateProduct > 0) {
					List<Integer> rowIntermediateBaseProductList = ConversionUtils.convertToBase(rowIntermediateProduct, BASE);	
					if (rowIntermediateBaseProductList.size() > 1) {
						carry = rowIntermediateBaseProductList.get(1);
						rowIntermediateProduct = rowIntermediateBaseProductList.get(0);
					} else {
						carry = 0;
					}
				}
				
				productRowRepresentation.getValue().add(rowIntermediateProduct);
			}
			
			if (carry > 0)
				productRowRepresentation.getValue().add(carry);
			
			/* c += productRowRepresentation */
			c = NumberNode.sum (c, productRowRepresentation);
			rowOffset ++;
		}
		
		return c.removeTrailingZeros();
	}
	
	/**
	 * function that performs the division operation on the two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a / NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode divide(NumberNode a, NumberNode b) {
		boolean toNegate = false;
		
		if ((a.isNegative() || b.isNegative()) && !(a.isNegative() && b.isNegative()))
			toNegate = true;
		
		NumberNode c = positiveDivide(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
		
		if (toNegate) 
			c.negate();
		
		return c;
	}
	
	/**
	 * inner function that performs the division operation on the two positive NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a / NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	private static NumberNode positiveDivide(NumberNode a, NumberNode b) {

		NumberNode c = null;
		
		List<Integer> x = a.removeTrailingZeros().getValue();
		List<Integer> y = b.removeTrailingZeros().getValue();
		
		boolean negative = a.isNegative() || a.isNegative();
		
		if (y.isEmpty() || (y.size() == 1 && y.get(0) == 0)) {
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		}
		else if (x.isEmpty() || (x.size() == 1 && x.get(0) == 0)) {
			c = new NumberNode(0);
		} 
		else {
			int count = 0;
			NumberNode intermediateDifference = a; 
				
			while (!intermediateDifference.isNegative()) {
				count++;
				intermediateDifference = subtract(intermediateDifference, b);
			}
			
			if (intermediateDifference.isNegative())
				count --;

			c = new NumberNode(count);
		}
		
		return (negative ? c.negate() : c);
	}
	
	/**
	 * function that performs the power operation on the two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a ^ NumberNode b
	 * 
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode power(NumberNode x, int i) {
		if (i <= 0)
			return new NumberNode(1);
		else if (i == 1)
			return x;
		else {
			// Logic to get half of the list.
			NumberNode half = power (x, i / 2);
			NumberNode res = product(half, half);
			
			if ((i % 2) == 0)
				return res;
			else
				return (product(res, x)).removeTrailingZeros();
		}
	}
	
	/**
	 * Helper function to Iterate and return the next value.
	 * 
	 * Returns 0 if list has ended.
	 * 
	 * @param iterator
	 * @return
	 */
	private static Integer getNext(Iterator<Integer> iterator) {
		return (iterator.hasNext() ? iterator.next() : 0);
	}


}
