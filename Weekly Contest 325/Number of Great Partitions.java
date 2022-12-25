// Intuition :
// 1. If the sum of the array is smaller than 2*k, then it is impossible to find a great partition.
// 2. Solve the reverse problem, that is, find the number of partitions where the sum of elements of at least one of the two groups is smaller than k.

// Lets store those invalid ways using dp
// Hence, total valid ways = total ways - 2*invalid ways

// Here, we are multiplying the invalid ways with 2 as the invalid ways can be in either partition (left or right)

// TC: O(len*k)
// SC: O(k)

class Solution {
    public int countPartitions(int[] nums, int k) {
        int len = nums.length;
        long sum = 0;
        
        long[] dp = new long[k];
        long mod = (long)1e9+7;
        long invalidWays = 0;
        long totalSubsets = 1;
        
        for(int idx=0; idx<=len; idx++){
            if(idx<len){
                totalSubsets = (((totalSubsets%mod) * (2%mod))+mod)%mod; 
                sum+=(long)nums[idx];
            }
            
            for(int currSum=k-1; currSum>=0; currSum--){
                if(currSum==0) dp[currSum] = 1l;
                else if(idx>0){
                    dp[currSum] = dp[currSum]%mod;
                    
                    int val = nums[idx-1];
                    
                    if(currSum>=val){
                        dp[currSum] = (((dp[currSum]%mod) + (dp[currSum-val]%mod))+mod)%mod;
                    }
                }
                
                if(idx==len) invalidWays = (((invalidWays%mod) + (dp[currSum]%mod))+mod)%mod;
            }
        }
        
        if(sum<2*k) return 0;
        
        long totalWays = (((totalSubsets%mod) - ((2*invalidWays)%mod))+mod)%mod;
        int ans = (int)totalWays;
        if(ans<0) ans+=(int)mod;
        
        return ans;
    }
}