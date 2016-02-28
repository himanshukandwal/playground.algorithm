package dev.research.himanshu.algorithm.assignments.lp1;

<<<<<<< HEAD
import java.util.Iterator;
import java.util.List;

=======
>>>>>>> 56b822adb529f12b546b095f51f51e77f89566c2
/**
 * Class implementing level 2 features.
 * 
 * @author G31
 *
 */
public class ComplexNumberNode extends NumberNode {
<<<<<<< HEAD
	
	/**
	 * Default Constructor
	 */
	public ComplexNumberNode() {
		super();
	}

	/**
	 * Constructor with the long Parameter
	 * 
	 * Convert to required base and calls add to "this" List method(fillNumber).
	 * 
	 * @param stringVal
	 */
	public ComplexNumberNode(long longVal) {
		super(longVal);
	}

	/**
	 * Constructor with the String Parameter
	 * 
	 * @param stringVal
	 */
	public ComplexNumberNode(String stringVal) {
		super(stringVal);
	}
	
	/**
	 * Constructor with the Direct value
	 * 
	 * @param stringVal
	 */
	public ComplexNumberNode(List<Integer> value) {
		super(value);
	}
	
	/**
	 * wrapper function that performs the sum of two NumberNode(s).
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
			c = NumberNode.sum(a.negate(), b.negate()).negate();
		} 
		else if (a.isNegative() || b.isNegative()) {
			boolean isBiggerNumberNegative = (a.abs().compareTo(b.abs()) < 0 ? b.isNegative() : a.isNegative());
			
			c = NumberNode.subtract(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
			
			if ((isBiggerNumberNegative && !c.isNegative()) || (!isBiggerNumberNegative && c.isNegative()))
				c.negate();
		}
		else {
			c = NumberNode.sum(a, b);
		}
		
		return c;
	}
	
	/**
	 * wrapper function that performs the difference of two NumberNode(s).
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
			c = NumberNode.subtract(a.negate(), b.negate()).negate();
		} 
		else if (a.isNegative() || b.isNegative()) {
			boolean toNegate = a.isNegative();
			
			c = NumberNode.sum(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
			
			if (toNegate)
				c.negate();
		}
		else {
			c = NumberNode.subtract(a, b);
		}
		
		return c;
	}
	
	
	/**
	 * wrapper function that performs the multiplication of two NumberNode(s).
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
			c = NumberNode.product(a.negate(), b.negate());
		} 
		else if (a.isNegative() || b.isNegative()) {
			c = NumberNode.product(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b).negate();
		}
		else {
			c = NumberNode.product(a, b);
		}
		
		return c;
	}
	
	/**
	 * wrapper function that performs the division operation on the two NumberNode(s).
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
		
		NumberNode c = NumberNode.divide(a.isNegative() ? a.negate() : a, b.isNegative() ? b.negate() : b);
		
		if (toNegate) 
			c.negate();
		
		return c;
	}

	/**
	 * function that performs the power operation on two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a ^ NumberNode b
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode power(NumberNode a, NumberNode b) {
		boolean toNegate = a.isNegative();
		
		a = (a.isNegative() ? a.negate() : a);
		NumberNode c = new NumberNode(1);
		
		Iterator<Integer> powerNumberNodeIterator = b.getValue().iterator();
		int loopcounter = 0;
		while (powerNumberNodeIterator.hasNext()) {
			Integer powerVal = powerNumberNodeIterator.next();
			if (loopcounter > 0) 
				powerVal *= (loopcounter *= BASE);
			else 
				loopcounter = 1;
			
			// c *= a ^ powerVal 
			c = NumberNode.product(c, NumberNode.power(a, powerVal));
		}
		
		if (toNegate) 
			c.negate();
		
		return c;
	}
	
	/**
	 * function that performs the modulus operation on two NumberNode(s).
	 * 
	 * NumberNode c = NumberNode a % NumberNode b
	 * 		
	 * @param a
	 * @param b
	 * @return NumberNode
	 */
	public static NumberNode mod(NumberNode a, NumberNode b) {
		if ((b.getValue().size() == 1 && b.getValue().get(0) == 0))
			throw new IllegalArgumentException("Argument 'divisor' is 0");
		
		if (a.compareTo(b) == 0 || (a.getValue().size() == 1 && a.getLast() == 0))
			return new NumberNode(0);
		
		NumberNode intermediateDifference = a; 
		while (!intermediateDifference.isNegative())
			intermediateDifference = subtract(intermediateDifference, b);
		
		NumberNode c = (intermediateDifference.isNegative() && (intermediateDifference.abs().compareTo(b.abs()) != 0) ? sum(b, intermediateDifference) : new NumberNode(0));		
		return c;
	}
	
=======

>>>>>>> 56b822adb529f12b546b095f51f51e77f89566c2
}
