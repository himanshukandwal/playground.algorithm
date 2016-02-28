package dev.research.himanshu.algorithm.assignments.lp1;

public class LongNumber {

	public static void main(String[] args) {
		Long number1 =  20l;
		String number2 = "-2";

		// Add numbers to linked list.Z=X+Y;
		System.out.println("Numbers :");
		NumberNode x = new NumberNode(number1);
		NumberNode y = new NumberNode(number2);
		x.printList();
		y.printList();
		
		System.out.println("Addition :");
		NumberNode z = NumberNode.sum(x, y);
		z.printList();

		System.out.println("Substraction :");
		z = NumberNode.subtract(x, y);
		z.printList();

		System.out.println("Multiplication :");
		z = NumberNode.product(x, y);
		z.printList();

		System.out.println("Division :");
		z = NumberNode.divide(x, y);
		z.printList();
		
//
//		System.out.println("Modulo : ");
//		NumberNode divisor = NumberNode.div(x, y);
//		NumberNode product = NumberNode.multi(divisor, y);
//		NumberNode reminder = NumberNode.diff(x, product);
//		NumberNode.showList(reminder.num);
//		System.out.println(number1 % (Long.parseLong(number2)));

//		System.out.println("Power Function :");
//		z = NumberNode.power(x, 5);
//		z.printList();
	}

}
