// We use a dp of size 3001 X 1001 to fit constraints and counter negative coordinates

// TC : O(3001 X 1001) ~ O(start X k)
// SC : O(3001 X 1001) ~ O(start X k)

class Solution {
    long mod = (long)1e9+7;
    Long dp[][] = new Long[3001][1001];
    
    public int numberOfWays(int start, int end, int k) {
        long ans = (ways(start,end,k)+mod)%mod;
        
        int finalAns = (int)(ans%mod);
        if(finalAns<0) finalAns+=(int)mod;
		
        return finalAns;
    }
    
    private long ways(int start,int end,int k){
        if(k==0){
            if(start==end) return 1;
            return 0;
        }
        
        if(dp[start+1000][k]!=null) return dp[start+1000][k];
        
        long ans = (((ways(start-1,end,k-1)%mod) + (ways(start+1,end,k-1)%mod))+mod)%mod;
        dp[start+1000][k] = ans;
		
        return ans;
    }
}