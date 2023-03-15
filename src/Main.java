/**
 * Author: Edwin Savelson
 *
 * QUESTION:
 * Given a 6x6 matrix
 * starting with 100 energy,
 * Each square in the matrix has a value, and when you travel to that square, you spend that much energy.
 * What is the least amount of energy spent traveling from one end to the other(top to bottom) possible
 * You may only travel diagonally.
 *
 * COMMENTS:
 * What I remember during the assessment is attempting to visit each value in the first row and
 * checking the diagonals for which was lower. I wasn't keeping track of a way to move to the lesser of the
 * values and I also don't think one of the variables was initializing because I kept getting an undefined error.
 *
 * This solution still isn't as good as a depth first search, but I couldn't figure out a way to take the data and
 * make it searchable.  I thought to make each of the starting values in the top row a binary search tree, but I'm
 * still not sure how to do that in this situation.
 *
 * This solution also does not account for situations where the immediate path of lesser value is not ultimately the
 * better path.
 * For example:
 * if you travel to 10 then it compares 10's diagonals 20 and 100; it will move
 * 20 no matter what 20's subsequent diagonals.
 *
 * I'm not particularly wild about this solution, but I wanted to figure it out and show that I could get a solution in
 * Java.

 */


public class Main {

    // [row][col]
    // Diagonals are:
// left: [[i+1],[j-1]]
// right:[[i+1],[j+1]]

    public static int matrixSolution(int[][] mat) {
        int maxEnergy = -600;
        int count = 0;//keep track of index in the first row it begins from each loop
        for (int j = 0; j < mat.length && count < mat.length; j++) { //for each cell in the row
            j = count;
            int i = 0; //begin at first row
            int currentEnergy = 100;//starting energy
            currentEnergy -= mat[i][j];

            while (i < mat.length - 1 ) {//until on the last row
                if (j == 0) {//move to the right if on the left edge of matrix
                    j++;
                    i++;
                } else if (j == mat.length - 1) { //if on right border move left
                    j--;
                    i++;
                } else if  (i +1 < mat.length && (mat[i + 1][j - 1] < mat[i + 1][j + 1])  ) {//if left less than right, move to the left
                    j--;
                    i++;
                } else {//move to the right
                    j++;
                    i++;
                }
            }
            count++;
            currentEnergy -= mat[i][j];
            maxEnergy = Math.max(maxEnergy, currentEnergy);//take the highest amount of energy left at the end of each loop
        }

        return maxEnergy;
    }

    public static void main(String[] args) {

        int[][] mat = new int[][]{
                {100, 100, 100, 40, 100, 100},
                {70, 80, 90, 10, 100, 60},
                {50, 60, 10, 70, 80, 90},
                {70, 80, 50, 10, 90, 60},
                {70, 50, 60, 80, 10, 90},
                {70, 80, 50, 10, 90, 100}
        };

        System.out.println(matrixSolution(mat));
    }
}