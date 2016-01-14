package dev.research.himanshu.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you
 * may move to adjacent numbers on the row below. For example, given the
 * following triangle
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

	public MinPathTriangleArrayStack() {
	}

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
		int topElement = triangleListIterator.next().get(0);

		List<Integer> finalPathCalculationList = new ArrayList<Integer>();
		finalPathCalculationList.add(topElement);

		List<Integer> pathCalculationList;
		int lowestMin = topElement;

		for (;triangleListIterator.hasNext();) {
			List<Integer> dataList = triangleListIterator.next();

			pathCalculationList = new ArrayList<Integer>();
			Integer runMinima = null;
			for (int dataIndex = 0; dataIndex < dataList.size(); dataIndex ++) {
				int left, right, nodeMinimum;

				if (dataIndex + 1 == dataList.size()) {
					nodeMinimum = left = finalPathCalculationList.get(dataIndex - 1) + dataList.get(dataIndex);
					pathCalculationList.add(left);
				}
				else if (dataIndex == 0) {
					nodeMinimum = right = finalPathCalculationList.get(dataIndex) + dataList.get(dataIndex);
					pathCalculationList.add(right);
				} else {
					left = finalPathCalculationList.get(dataIndex - 1) + dataList.get(dataIndex);
					right = finalPathCalculationList.get(dataIndex) + dataList.get(dataIndex);
					
					nodeMinimum = Math.min(left, right);
					pathCalculationList.add(Math.min(left, right));
				}
				
				if (runMinima == null || runMinima > nodeMinimum) {
					runMinima = nodeMinimum;
				}
			}
			finalPathCalculationList = pathCalculationList;
			lowestMin = runMinima;
		}

		return lowestMin;
	}

}
