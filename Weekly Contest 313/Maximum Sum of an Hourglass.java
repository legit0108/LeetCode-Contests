// TC : O(rows*cols)
// SC : O(1)

class Solution {
    public int maxSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxSum =0;
    
        for(int row=0; row<rows-2; row++){
            for(int col=0; col<cols-2; col++){
                    int currSum = grid[row][col] +grid[row][col+1]  +grid[row][col+2]
                                                 +grid[row+1][col+1]+
                                 grid[row+2][col]+grid[row+2][col+1]+grid[row+2][col+2];
                    
                    maxSum = Math.max(maxSum, currSum);
            }
        }
        
        return maxSum;
    }
}