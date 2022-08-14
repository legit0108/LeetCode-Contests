// Pre-requisites : Digit DP
// TC : O(2^10*len)
// SC : O(2^10*len) can be optimized to O(2^10) by tabulation , since we only look at next immediate state for recurrence relation

class Solution {
    public int countSpecialNumbers(int n) {
        String str = Integer.toString(n);
        int len = str.length();
        Integer dp[][][] = new Integer[1<<10][len][2];
        return countSpecialNumbers(str,0,0,1,len,dp);
    }
    
    // mask -> numbers used , idx -> currentIdx , bound -> flag for how many numbers we can place at idx
    private int countSpecialNumbers(String str,int mask,int idx,int bound,int len,Integer dp[][][]){
        if(idx==len){
            if(mask==0) return 0; // no number or 0
            return 1;
        }
        
        if(dp[mask][idx][bound]!=null) return dp[mask][idx][bound];
        
        int dig = str.charAt(idx)-'0';
        int ans = 0;
        int maxDig=((bound==0)?9:dig);
        
        for(int currDig =0;currDig<=maxDig;currDig++){
            if((mask&(1<<currDig))==0){
                if(mask==0&&currDig==0){
                    ans+=countSpecialNumbers(str,mask,idx+1,0,len,dp); // for numbers with length less than str
                }
                else{
                    // for numbers with same length as str  
                    ans+=countSpecialNumbers(str,mask|1<<currDig,idx+1,((bound==0)?0:((currDig==str.charAt(idx)-'0')?1:0)),len,dp); 
                }
            }
        }
        
        return dp[mask][idx][bound] = ans;
    }
}