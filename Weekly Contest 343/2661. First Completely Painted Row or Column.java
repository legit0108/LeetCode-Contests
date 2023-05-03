// Solution-1: 1 map + maintain row and column counts

// TC: O(cells + arr.length)
// SC: O(cells + arr.length)

class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int cells = rows*cols;
        
        int[] rowCount = new int[rows];
        int[] colCount = new int[cols];
        int[] map = new int[cells+1];
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                // map (row, col) to an integer
                int cell = mat[row][col];
                map[cell] = row*cols+col;
            }
        }
        
        for(int index=0; index<arr.length; index++){
            int cell = map[arr[index]];
            int row = cell/cols;
            int col = cell%cols;
            
            rowCount[row]++;
            colCount[col]++;
            
            if(rowCount[row]==cols || colCount[col]==rows) return index; // return index if row/col filled
        }
        
        return -1;
    }
}


// Solution-2: 1 map
// Idea: Track maximal index of filling for all rows and all columns
// We track maximal index since it denotes all other cells of current row / col have been filled before this index
// We find minimum of all such maximal indices

// TC: O(cells + arr.length)
// SC: O(cells)

class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int cells = rows*cols;
        int len = arr.length;
        int[] map = new int[cells+1];
        
        for(int index=0; index<len; index++){
            map[arr[index]] = index;
        }
        
        int minIndex = len;
        
        for(int row=0; row<rows; row++){
            int maxIndex = 0;
            
            for(int col=0; col<cols; col++) maxIndex = Math.max(maxIndex, map[mat[row][col]]);
            
            minIndex = Math.min(minIndex, maxIndex);
        }
        
        for(int col=0; col<cols; col++){
            int maxIndex = 0;
            
            for(int row=0; row<rows; row++) maxIndex = Math.max(maxIndex, map[mat[row][col]]);
            
            minIndex = Math.min(minIndex, maxIndex);
        }
        
        return minIndex;
    }
}