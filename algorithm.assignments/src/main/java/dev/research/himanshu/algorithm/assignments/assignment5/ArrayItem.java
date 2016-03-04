package dev.research.himanshu.algorithm.assignments.assignment5;

/**
 * A simple bean class representing one dummy element with properly
 * overridden hashcode and equals methods.  
 * 
 * @author G31
 */
public class ArrayItem implements Comparable<ArrayItem> {

	private int rank;
	private String description;
	private String propertyOne;
	private String propertyTwo;
	
	/**
	 * default constructor
	 */
	public ArrayItem() {}
	
	/**
	 * base constructor
	 */
	public ArrayItem(int rank) {
		this.rank = rank;
	}
	
	/**
	 * 2 parameter constructor
	 */
	public ArrayItem(int rank, String description) {
		this(rank);
		this.description = description;
	}
	
	
	/**
	 * overridden hashcode method, creating the hashcode of the object based on the content of the 
	 * object.
	 * 
	 * @return
	 */
	@Override
	public int hashCode() {
		return new HashCodeGenerator()
				.withValue(getRank())
				.ifNotNullThenWithValue(getDescription())
				.ifNotNullThenWithValue(getPropertyOne())
				.ifNotNullThenWithValue(getPropertyTwo())
				.getHashValue();
	}
	
	/**
	 * overridden equals method, checking equals on the basis of compareTo method.
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ArrayItem))
			return false;
	
		return (this.compareTo((ArrayItem) obj) == 0 ? true : false);
	}
	
	/**
	 * overridden compare to method, comparing the objects on the basis of the 
	 * content of the object.
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(ArrayItem o) {
		return 0;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPropertyOne() {
		return propertyOne;
	}

	public void setPropertyOne(String propertyOne) {
		this.propertyOne = propertyOne;
	}

	public String getPropertyTwo() {
		return propertyTwo;
	}

	public void setPropertyTwo(String propertyTwo) {
		this.propertyTwo = propertyTwo;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.rank);
	}

}
