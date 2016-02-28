package dev.research.himanshu.algorithm.assignments.lp1;

public class LevelOneDriver {

	public static void main(String[] args) {
		String contentOffset = "\n\t";
		NumberNode a = null;
		NumberNode b = null;
		
		System.out.println(" This is Level 1 driver program, illustrating our implementation ! ");
		System.out.println(" We have used the class 'NumberNode' to store the numbers in a list fashion and in format "
				+ contentOffset + "Least significant digit -> Most significant digit");
		System.out.println("\n\n let's review the working of the basic arithmetic operations !");
		System.out.println(" a. Addition ");
		 
		a = new NumberNode(20);
		b = new NumberNode(-2);
		
		System.out.println(" adding : 20, -2 "); 
		a.printList(); 
		b.printList(); 
		System.out.println("result is : (18) ");
		NumberNode.sum(new NumberNode(20), new NumberNode(-2)).printList();
		

	}

}
