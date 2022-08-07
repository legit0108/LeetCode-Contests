// For each character check all possible characters that fit in range of k 
// TC : O(len)
// SC : O(1)

class Solution {
    public int longestIdealString(String s, int k) {
        int dp[] = new int[26];
        int len = s.length();
        int longestStringLen = 0;
        
        for(int idx1 =0;idx1<len;idx1++){
            char ch = s.charAt(idx1);
            int idx = ch-'a';
            
            for(int idx2=Math.max(0,idx-k);idx2<=Math.min(25,idx+k);idx2++){
                 dp[idx] = Math.max(dp[idx],dp[idx2]);                
            }
            
            dp[idx]++;
            longestStringLen = Math.max(longestStringLen,dp[idx]);
        }
        
        return longestStringLen;
    }
}