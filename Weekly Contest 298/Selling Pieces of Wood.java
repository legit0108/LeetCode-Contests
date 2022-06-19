// To cut a piece of wood, you must make a vertical or horizontal cut across the entire height or width of the piece to split it into two smaller pieces.

// For a piece of w * h,

// we can make a vertical cut to split it into a * h and (w - a) * h
// So we can update dp[w][h] = max(dp[w][h], dp[a][h] + dp[w - a][h]).

// we can make a horizontal cut to split it into w * a and w * (h - a)
// So we can update dp[w][h] = max(dp[w][h], dp[w][a] + dp[w][h - a]).

// Complexity
// Time O(mmn + mnn)
// Space O(mn)

class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        long dp[][] = new long[m+1][n+1];
        for(int price[] : prices){
            int height = price[0];
            int width = price[1];
            int money = price[2];
            
            dp[height][width] = money;
        }
        
        for(int height = 1;height<=m;height++){
            for(int width = 1;width<=n;width++){
                long max = Long.MIN_VALUE;
                
                for(int edge=1;edge<=width;edge++){
                    max = Math.max(max,dp[height][edge]+dp[height][width-edge]);
                }
                
                for(int edge=1;edge<=height;edge++){
                    max = Math.max(max,dp[edge][width]+dp[height-edge][width]);
                }
                
                dp[height][width] = max;
            }
        }
        
        return dp[m][n];
    }
}