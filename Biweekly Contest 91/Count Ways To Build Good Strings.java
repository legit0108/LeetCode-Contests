// DP

// TC: O(high)
// SC: O(high)

class Solution {
    public int countGoodStrings(int low, int high, int zero, int one) {
        long mod = (long)1e9+7;
        long dp[] = new long[high+1];
        dp[0] = 1;
        long count = 0;
        
        for(int len = 1; len<=high; len++){
             long currCount = 0;
             
             if(len-zero>=0) currCount = ((currCount%mod) + (dp[len-zero]%mod))%mod;
             if(len-one>=0) currCount = ((currCount%mod) + (dp[len-one]%mod))%mod;
             
             if(len>=low) count = (count%mod + currCount%mod)%mod;
             dp[len] = currCount;
        }
        
        int finalAns = (int)(count%mod);
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
}