// Solution-1: Prefix sum/line sweep

// TC: O(queries.length*n) + O(n*n)
// SC: O(1) excluding output


class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int mat[][] = new int[n][n];
        
        for(int query[] : queries){
            int row1 = query[0];
            int col1 = query[1];
            int row2 = query[2];
            int col2 = query[3];
            
            for(int row=row1; row<=row2; row++){
                mat[row][col1]++;
                if(col2+1<n) mat[row][col2+1]--;
            }
        }
        
        for(int row=0; row<n; row++){
            for(int col=0;col<n; col++){
                if(col>0) mat[row][col]+=mat[row][col-1];
            }
        }
        
        return mat;
    }
}


// Solution-2: Prefix sum/line sweep

// TC: O(queries.length) + O(n*n)
// SC: O(1) excluding output

class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int mat[][] = new int[n][n];
        
        for(int query[] : queries){
            int row1 = query[0];
            int col1 = query[1];
            int row2 = query[2];
            int col2 = query[3];
            
            mat[row1][col1]++;
            if(row2+1<n) mat[row2+1][col1]--;
            if(col2+1<n) mat[row1][col2+1]--;
            if(row2+1<n && col2+1<n) mat[row2+1][col2+1]++;
        }
        
        for(int row=0; row<n; row++){
            for(int col=0;col<n; col++){
                if(row>0) mat[row][col]+=mat[row-1][col];
                if(col>0) mat[row][col]+=mat[row][col-1];
                if(row>0 && col>0) mat[row][col]-=mat[row-1][col-1];
            }
        }
        
        return mat;
    }
}