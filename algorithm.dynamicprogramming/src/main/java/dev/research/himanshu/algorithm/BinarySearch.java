package dev.research.himanshu.algorithm;

public class BinarySearch {

	private int[] integerArr;
	
	public BinarySearch(int[] intArr) {
		this.integerArr = intArr;
	}
	
	public void binarySearch(int number) {
		if (integerArr == null || integerArr.length ==0) {
			System.out.println(" No elements in the array !");
			return;
		}
		
		System.out.println("Index found at : " +  binaryRecursion(number, 0, integerArr.length -1));	
	}
	
	private int binaryRecursion(int number, int beginIndex, int endIndex) {
		int indexFound = -1;
		
		if (beginIndex == endIndex) {
			return indexFound;
		}
		
		int midIndex = (beginIndex + endIndex) / 2;
		
		if (integerArr[midIndex] == number) {
			indexFound = midIndex;
		} else if (integerArr[midIndex] > number) {
			indexFound = binaryRecursion(number, beginIndex, midIndex -1);
		} else {
			indexFound = binaryRecursion(number, midIndex + 1, endIndex);
		} 
		
		return indexFound ;
	}
	
	public static void main(String[] args) {
		int[] intArr = new int[10];
		
		for (int i = 0; i < 10; i++) {
			intArr[i] = i;
		}
		
		BinarySearch binarySearch = new BinarySearch(intArr);
		
		binarySearch.binarySearch(11);

	}

}
