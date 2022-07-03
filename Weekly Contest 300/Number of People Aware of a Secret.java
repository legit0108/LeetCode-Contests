// TC : O(n)
// SC : O(n)

// dp[day] -> number of people who got to know about secret on this day 
// dp[day-delay] people will start sharing secret on this day
// so people+=dp[day-delay]
// dp[day-forget] people will forget secret on this day
// so people-=dp[day-forget]
// dp[day] = people
// ans = summation of dp from day = n-forget+1 to n

class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long dp[] = new long[n+1];
        dp[1] = 1;
        long people = 0;
        long mod = (long)1e9+7;
        long ans = 0;
        
        for(int day=1;day<=n;day++){
            if(day>1){
                dp[day] = (people+dp[Math.max(day-delay,0)]-dp[Math.max(day-forget,0)])%mod;
                people = dp[day];
            }
            
            if(day>=n-forget+1){
                ans = (ans + dp[day])%mod;
            }
        }
        
        int finalAns = (int)ans;
        if(finalAns<0) finalAns+=(int)mod;
        return finalAns;
    }
}