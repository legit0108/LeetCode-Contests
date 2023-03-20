// TC: O(n*n)
// SC: O(1)

class Solution {
    public boolean checkValidGrid(int[][] grid) {
        int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        int n = grid.length;
        int row = 0;
        int col = 0;
        int move = 0;
        
        while(move<n*n-1){
            int nextRow = -1;
            int nextCol = -1;
            boolean foundValidCell = false;
            
            for(int index=0; index<8; index++){
                int[] dir = dirs[index];
                int currRow = row+dir[0];
                int currCol = col+dir[1];
                
                if(currRow>=0 && currCol>=0 && currRow<n && currCol<n){
                    if(grid[currRow][currCol]==move+1){
                        nextRow = currRow;
                        nextCol = currCol;
                        foundValidCell = true;
                        break;
                    }
                }
            }
            
            if(!foundValidCell) return false; // if cannot find a valid next move out of 8 possible, return false
            
            row = nextRow;
            col = nextCol;
            move++;
        }
        
        return true;
    }
}