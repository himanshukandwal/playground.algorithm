package dev.research.himanshu.algorithm.dynamicprogramming;

import java.util.Iterator;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * 
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 *
 * @author Himanshu Kandwal
 *
 */
public class MinPathTriangleArrayStack {
	
	private List<List<Integer>> triangle;
	
	public MinPathTriangleArrayStack() {}

	public MinPathTriangleArrayStack(List<List<Integer>> triangle) {
		this.triangle = triangle;
	}
	
	public void setTriangle(List<List<Integer>> triangle) {
		this.triangle = triangle;
	}
	
	public List<List<Integer>> getTriangle() {
		return triangle;
	}
	
	public int minimumTotal() {
        Iterator<List<Integer>> triangleListIterator = triangle.iterator();
        
        int runningIndex = 0;
        int path = triangleListIterator.next().get(0);
        
        for (;triangleListIterator.hasNext();) {
            List<Integer> list = triangleListIterator.next();
            
            if (list.get(runningIndex) > list.get(runningIndex + 1)) {
                path += list.get(runningIndex + 1);
                runningIndex ++; 
            } else {
                path += list.get(runningIndex);
            }
        }
        
        return path;
    }
	
}
