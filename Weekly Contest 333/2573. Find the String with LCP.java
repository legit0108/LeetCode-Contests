/*
 Ideas:
 
 -> lcp[i][j] = longest common substring of the string starting at i and j
 -> Start the first character with a for lexicographically smallest string
 -> Find corresponding characters using lcp matrix (should be lexicographically minimum)
 -> Either string obtained will be the answer, or no other string is possible 
    (since we filled the string using some cells of the matrix)
 -> Verify the string obtained by calculating lcp of the string
 
 TC: O(len*len)
 SC: O(len)
*/ 

class Solution {
    public String findTheString(int[][] lcp) {
        StringBuilder str = new StringBuilder();
        char ch = 'a';
        int len = lcp.length;
        
        for(int idx1=0; idx1<len; idx1++){
            boolean found = false;
            
            for(int idx2=0; idx2<idx1; idx2++){
                if(lcp[idx1][idx2]>0){
                    str.append(str.charAt(idx2));
                    found = true;
                    break;
                }
            }
            
            if(!found){
                if(ch>='a' && ch<='z'){
                    str.append(ch);
                    ch++;
                }else return "";
            }
        }
        
        if(isValid(str, lcp)) return str.toString();
        else return "";
    }
    
    private boolean isValid(StringBuilder str, int[][] lcp){
        int len = str.length();
        int[] dp = new int[len+1];
        
        for(int idx1 = len-1; idx1>=0; idx1--){
            for(int idx2=0; idx2<len; idx2++){
                if(str.charAt(idx1) == str.charAt(idx2)){
                    dp[idx2] = 1 + dp[idx2+1];
                }else dp[idx2] = 0;
                
                if(dp[idx2]!=lcp[idx1][idx2]) return false;
            } 
        }
        
        return true;
    }
}