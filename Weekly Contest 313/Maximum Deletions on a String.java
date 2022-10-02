// Solution-1 : KMP + DP 

// Find all lengths for a particular index such that 
// first "length" characters from index 
// are equal to following "length" characters
// using KMP, then use DP to iterate through 
// all options for an index

// TC : O(len^2)
// SC : O(len^2)

class Solution {
    public int deleteString(String s) {
        int len = s.length();
        Integer dp[] = new Integer[len];
        List<Integer> options[] = new ArrayList[len];
        
        for(int idx =0; idx<len; idx++){
            options[idx] = new ArrayList();
            String str = s.substring(idx);
            
            int lps[] = getLps(str);
            int lpsLen = lps.length;
            
            for(int currIdx = 0; currIdx<lpsLen; currIdx++){
                if(lps[currIdx]>0){
                    if(currIdx-2*lps[currIdx]+1 == 0) options[idx].add(lps[currIdx]);
                }
            }
        }
        
        return deleteString(s, 0, len, options, dp);
    }
    
    private int[] getLps(String str){
        int strLen = str.length();
        int lps[] = new int[strLen];
        int idx = 1;
        int len = 0;
        
        while(idx<strLen){
            if(str.charAt(idx) == str.charAt(len)){
                len++;
                lps[idx] = len;
                idx++;
            }else{
                if(len>0) len = lps[len-1];
                else{
                    lps[idx] = 0;
                    idx++;
                }
            }
        }
        
        return lps;
    }
    
    private int deleteString(String str, int idx, int len, List<Integer> options[], Integer dp[]){
        if(idx == len) return 0;
        if(dp[idx]!=null) return dp[idx];
        
        int maxOperations = 1;
        
        for(int length : options[idx]){
            int currOperations = deleteString(str, idx+length, len, options, dp);
            maxOperations = Math.max(maxOperations, currOperations+1);
        }
        
       return dp[idx] = maxOperations;
    }
}

// Solution-2 : LCS + DP, 
// derived from lee215s solution : https://leetcode.com/problems/maximum-deletions-on-a-string/discuss/2648900/JavaC%2B%2BPython-DP-Solution

// Find longest common substring for all pairs of indices
// If the longest common substring for any pair
// overlap or touch each other
// then we can perform partitioning 

// TC : O(len^2)
// SC : O(len)

class Solution {
    public int deleteString(String s) {
        int len = s.length();
        int lcs[] = new int[len+1];
        int dp[] = new int[len];
        
        for(int idx1=len-1; idx1>=0; idx1--){
            dp[idx1] = 1;
            
            for(int idx2=idx1+1; idx2<len; idx2++){
                if(s.charAt(idx1) == s.charAt(idx2)){
                    lcs[idx2] = lcs[idx2+1]+1;
                }else lcs[idx2] = 0;
                
                if(lcs[idx2] >= idx2-idx1) dp[idx1] = Math.max(dp[idx1], dp[idx2]+1);
            }
        }
        
        return dp[0];
    }
}