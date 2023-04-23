// TC: O(rows*cols)
// SC: O(1)

class Solution {
    public int[] findColumnWidth(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int[] width = new int[cols];
        
        for(int col=0; col<cols; col++){
            int maxWidth = 0;
            
            for(int row =0; row<rows; row++){
                int currWidth = getWidth(grid[row][col]);
                maxWidth = Math.max(maxWidth, currWidth);
            }
            
            width[col] = maxWidth;
        }
        
        return width;
    }
    
    private int getWidth(int num){
        if(num==0) return 1;
        
        int width = 0;
        
        if(num<0){
            width++;
            num = -num;
        }
        
        while(num>0){
            width++;
            num = num/10;
        }
        
        return width;
    }
}