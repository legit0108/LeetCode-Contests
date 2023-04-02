// TC: O(s.length())
// SC: O(1)

class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int maxLength = 0;
        int zeros = 0;
        int ones = 0;
        
        for(int index =0; index<s.length(); index++){
            if(s.charAt(index)=='1'){
                ones++;
                  
                if(ones<=zeros) maxLength = Math.max(maxLength, 2*Math.min(zeros, ones));
            }else{
                if(index>0 && s.charAt(index-1)=='1') ones = zeros = 0;
                zeros++;
            }
        }
        
        return maxLength;
    }
}