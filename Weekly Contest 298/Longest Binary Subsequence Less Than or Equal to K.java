// TC : O(N)
// SC : O(1)
// where N is length of s 

class Solution {
    public int longestSubsequence(String s, int k) {
        int len = s.length();
        int lenSoFar = 0;
        long num = 0;
    
        for(int idx = len-1;idx>=0;idx--){
            int dig = s.charAt(idx)-'0';
            if(dig==0){
                // always optimal to include zero , 
                // subsequence length increased without any cost
                lenSoFar++;
            }else if(lenSoFar<63){
                // lenSoFar <63 -> overflow check
                // only optimal to include one if including one does not exceed k
                // subsequence length increased with cost
                
                long mask = 1l<<lenSoFar;
                if((num|mask)<=k){
                    lenSoFar++;
                    num|=mask;
                }
            }
        }
        
        return lenSoFar;
    }
}