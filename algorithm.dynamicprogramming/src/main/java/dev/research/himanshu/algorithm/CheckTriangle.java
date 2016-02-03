package dev.research.himanshu.algorithm;

public class CheckTriangle {
	
	public static void main(String... args) {
	
		int number = Integer.valueOf(args[0]);
		int[] edges = new int[3];
		
		if (args.length == number + 1) {
			for (int index = 1; index < args.length; index ++) {
				String[] edgesArr = args[index].split(" ");
				
				for (int strIndex = 0; strIndex < edgesArr.length; strIndex++)
					edges[strIndex] = Integer.valueOf(edgesArr[strIndex]);					
				
				if (edges[0] + edges[1] > edges[2] && edges[1] + edges[2] > edges[3] 
						&& edges[0] + edges[2] > edges[1]) {
					if (edges[0] == edges[1] && edges[1] == edges[2]) {
						System.out.println("Equilateral triangle");
					} else {
						if (edges[0] == edges[1] || edges[2] == edges[1] || edges[0] == edges[2]) {
							System.out.println("Isoceles triangle");
						} else {
							System.out.println("Scalene triangle");
						}
					}
				} else {
					System.out.println("None of these.");
				}	
			}
		}
	}
}