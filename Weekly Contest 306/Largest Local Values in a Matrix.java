// TC : O(n*n)
// SC : O(1) excluding output

class Solution {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int maxLocal[][] = new int[n-2][n-2];
        
        for(int row=1;row<=n-2;row++){
           for(int col = 1;col<=n-2;col++){
               int currMax = grid[row][col];
               
               currMax = Math.max(currMax,Math.max(grid[row-1][col],grid[row+1][col]));
               currMax = Math.max(currMax,Math.max(grid[row][col-1],grid[row][col+1]));
               currMax = Math.max(currMax,Math.max(grid[row-1][col-1],grid[row+1][col+1]));
               currMax = Math.max(currMax,Math.max(grid[row-1][col+1],grid[row+1][col-1]));
               
               maxLocal[row-1][col-1] = currMax;
           } 
        }
        
        return maxLocal;
    }
}