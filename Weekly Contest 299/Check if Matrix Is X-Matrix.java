// TC : O(len^2)
// SC : O(1)

class Solution {
    public boolean checkXMatrix(int[][] grid) {
        int len = grid.length;
        
        for(int row =0;row<len;row++){
            for(int col =0;col<len;col++){
                if((row==col)||(row+col)==len-1){
                    if(grid[row][col]==0) return false;
                }else if(grid[row][col]!=0) return false;
            }
        }
        
        return true;
    }
}