// TC: O(rows*cols)
// SC: O(1)

class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int maxCount = 0;
        int maxRow = 0;
        
        for(int row=0; row<rows; row++){
            int ones = 0;
            
            for(int col=0; col<cols; col++){
                if(mat[row][col]==1) ones++;
            }
            
            if(ones>maxCount){
                maxCount = ones;
                maxRow = row;
            }
        }
        
        return new int[]{maxRow, maxCount};
    }
}