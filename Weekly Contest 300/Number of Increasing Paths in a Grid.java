// Method - 1 : DFS + DP

class Solution {
    Long dp[][];
    long mod = (long)1e9+7;
    
    public int countPaths(int[][] grid) {
        long count =0;
        int rows = grid.length;
        int cols = grid[0].length;
        dp = new Long[rows][cols];
         
        for(int row = 0;row<rows;row++){
           for(int col =0;col<cols;col++){
               count=(count%mod+dfs(grid,row,col,rows,cols)%mod)%mod;
           }
        }
        
        int finalAns = (int)(count%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
    
    private long dfs(int grid[][],int row,int col,int rows,int cols){
        long count =0;
        if(dp[row][col]!=null) return dp[row][col];
        
        if(row-1>=0&&grid[row-1][col]>grid[row][col]){
            count=(count%mod+dfs(grid,row-1,col,rows,cols)%mod)%mod;
        }
        if(row+1<rows&&grid[row+1][col]>grid[row][col]){
            count=(count%mod+dfs(grid,row+1,col,rows,cols)%mod)%mod;
        }
        if(col-1>=0&&grid[row][col-1]>grid[row][col]){
            count=(count%mod+dfs(grid,row,col-1,rows,cols)%mod)%mod;
        }
        if(col+1<cols&&grid[row][col+1]>grid[row][col]){ 
            count=(count%mod+dfs(grid,row,col+1,rows,cols)%mod)%mod;
        }
        
        return dp[row][col] = count+1;
    }
}

// Method - 2 : BFS / Topological Sort

class Solution {
    public int countPaths(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int cells = rows*cols;
        int indegree[][] = new int[rows][cols];
        long ans = 0;
        long mod = (long)1e9+7;
        int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
        long paths[] = new long[cells];
        Queue<int[]> queue = new ArrayDeque(); 
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                for(int dir=0;dir<4;dir++){
                    int adjRow = row+dirs[dir][0];
                    int adjCol = col+dirs[dir][1];
                    
                    if(adjRow>=0&&adjCol>=0&&adjRow<rows&&adjCol<cols){
                        if(grid[adjRow][adjCol]>grid[row][col]) indegree[adjRow][adjCol]++;
                    }
                }
            }
        }
        
        for(int row=0;row<rows;row++){
            for(int col=0;col<cols;col++){
                int cell = row*cols+col;
                paths[cell] = 1;
                if(indegree[row][col]==0){
                    queue.add(new int[]{row,col});
                }
            }
        }
        
        while(queue.size()>0){
            int arr[] = queue.remove();
            int row = arr[0];
            int col = arr[1];
            int cell = row*cols+col;
            
            for(int dir=0;dir<4;dir++){
                int adjRow = row+dirs[dir][0];
                int adjCol = col+dirs[dir][1];
                    
                if(adjRow>=0&&adjCol>=0&&adjRow<rows&&adjCol<cols){
                    if(grid[adjRow][adjCol]>grid[row][col]){
                        indegree[adjRow][adjCol]--;
                        int adjCell = adjRow*cols+adjCol;
                        paths[adjCell] = ((paths[adjCell]%mod) + (paths[cell]%mod))%mod;
                        if(indegree[adjRow][adjCol]==0) queue.add(new int[]{adjRow,adjCol});
                    }
                }
            }
        }
        
        for(int cell=0;cell<cells;cell++){
            ans = ((ans%mod) + (paths[cell]%mod))%mod;
        }
        
        int finalAns = (int)(ans%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
}

// TC : O(rows*cols)
// SC : O(rows*cols)