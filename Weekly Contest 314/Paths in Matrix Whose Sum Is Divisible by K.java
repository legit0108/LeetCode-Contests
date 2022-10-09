// TC: O(rows*cols*k)
// SC: O(rows*cols*k)

class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        long dp[][][] = new long[rows][cols][k];
        long mod = (long)1e9+7;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                int rem = grid[row][col]%k;
                
                if(row==0 && col==0) dp[row][col][rem] = 1;
                else{
                    for(int currem=0;currem<k;currem++){
                        if(row==0) dp[row][col][(currem+rem)%k] = dp[row][col-1][currem];
                        else if(col==0) dp[row][col][(currem+rem)%k] = dp[row-1][col][currem];
                        else{
                            dp[row][col][(currem+rem)%k] = ((dp[row-1][col][currem]%mod) + (dp[row][col-1][currem]%mod))%mod;
                        }
                    }
                }
            }
        }
        
        int ans = (int)(dp[rows-1][cols-1][0]%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
}