package dev.research.himanshu.algorithm;

public class LargestGlasshour {

	public static void main(String[] args) {
		
		int arr[][] = new int[][] {
			{1, 1, 1, 0, 0, 0},
			{0, 1, 0, 0, 0, 0},
			{1, 1, 1, 0, 0, 0},
			{0, 9, 2, -4, -4, 0},
			{0, 0, 0, -2, 0, 0},
			{0, 0, -1, -2, -4, 0}
		};
		
		
		int horizontalsum = arr[2][0] + arr[2][1] + arr[2][2];
		int verticalsum = arr[0][0] + arr[0][1] + arr[0][2];
		int diagonalsum = arr[0][0] + arr[1][1] + arr[2][2];

		int xaxis = 3;
		int yaxis = 3;

		int xcoordinate = 3;
		int ycoordinate = 3;

		while (xaxis < 5 || yaxis < 5) {
			Integer tophorizontalDiff, bottomhorizontalDiff, rightverticalDiff, leftverticalDiff;
			tophorizontalDiff = bottomhorizontalDiff = rightverticalDiff = leftverticalDiff = null;
			
			if (xcoordinate - 2 >= 0) {
				tophorizontalDiff = (arr[xcoordinate-2][ycoordinate-1] + arr[xcoordinate-2][ycoordinate] + arr[xcoordinate-2][ycoordinate+1])
						- (arr[xcoordinate-1][ycoordinate-1] + arr[xcoordinate-1][ycoordinate] + arr[xcoordinate-1][ycoordinate+1]);
			}
			if (xcoordinate + 2 >= 0) {
				tophorizontalDiff = (arr[xcoordinate+2][ycoordinate-1] + arr[xcoordinate+2][ycoordinate] + arr[xcoordinate+2][ycoordinate+1])
						- (arr[xcoordinate+1][ycoordinate-1] + arr[xcoordinate+1][ycoordinate] + arr[xcoordinate+1][ycoordinate+1]);
			}
			
			if (ycoordinate - 2 >= 0) {
				tophorizontalDiff = (arr[xcoordinate-2][ycoordinate-1] + arr[xcoordinate-2][ycoordinate] + arr[xcoordinate-2][ycoordinate+1])
						- (arr[xcoordinate-1][ycoordinate-1] + arr[xcoordinate-1][ycoordinate] + arr[xcoordinate-1][ycoordinate+1]);
			}
			if (ycoordinate + 2 >= 0) {
				tophorizontalDiff = (arr[xcoordinate+2][ycoordinate-1] + arr[xcoordinate+2][ycoordinate] + arr[xcoordinate+2][ycoordinate+1])
						- (arr[xcoordinate+1][ycoordinate-1] + arr[xcoordinate+1][ycoordinate] + arr[xcoordinate+1][ycoordinate+1]);
			}
			
			int possibleverticlesum = arr[xaxis - 3][yaxis] + arr[xaxis - 2][yaxis] + arr[xaxis - 1][yaxis];
			int possiblehorizontalalsum = arr[xaxis][yaxis - 3] + arr[xaxis][yaxis - 2] + arr[xaxis][yaxis - 1];
			int nextdiagonalsum = arr[xaxis - 3][yaxis - 3] + arr[xaxis - 2][yaxis - 2] + arr[xaxis - 1][yaxis - 1];

			int horizontalDiff = (arr[xaxis-1][yaxis-1] + arr[xaxis-1][yaxis] + arr[xaxis-1][yaxis+1])
					- (arr[xcoordinate-1][ycoordinate-1] + arr[xcoordinate-1][ycoordinate] + arr[xcoordinate-1][ycoordinate+1]);
			int verticalDiff = possibleverticlesum - verticalsum;
			int diagonalDiff = nextdiagonalsum - diagonalsum;

			if (horizontalDiff > 0 || verticalDiff > 0 || diagonalDiff > 0) {
				if (horizontalDiff > verticalDiff && horizontalDiff > diagonalDiff) {
					xcoordinate++;
					xaxis++;
				} else if (verticalDiff > horizontalDiff && verticalDiff > diagonalDiff) {
					ycoordinate++;
					yaxis++;
				} else if (diagonalDiff > horizontalDiff && diagonalDiff > verticalDiff) {
					xcoordinate++;
					xaxis++;
					ycoordinate++;
					yaxis++;
				}
			} else {
				break;
			}
		}

		int sum = 0;
		sum += arr[xcoordinate - 1][ycoordinate - 1] + arr[xcoordinate - 1][ycoordinate] + arr[xcoordinate - 1][ycoordinate + 1];
        sum += arr[xcoordinate][ycoordinate];
        sum += arr[xcoordinate + 1][ycoordinate - 1] + arr[xcoordinate + 1][ycoordinate] + arr[xcoordinate + 1][ycoordinate + 1];

		System.out.println(sum);
	}
}
