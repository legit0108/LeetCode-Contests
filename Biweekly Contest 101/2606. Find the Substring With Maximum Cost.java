/*
 Idea:

 Replace each character of s with its value,
 The problem reduces to finding the maximum sum subarray 
 which can be solved using Kadane's algorithm in linear time

 TC: O(s.length())
 SC: O(1)
*/

class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        HashMap<Character, Integer> map = new HashMap();
        for(int index=0; index<chars.length(); index++) map.put(chars.charAt(index), vals[index]);
        
        int maxSum = 0;
        int currSum = 0;
        
        for(int index =0; index<s.length(); index++){
            char ch = s.charAt(index);
            
            int val = map.getOrDefault(ch, (ch-'a'+1));
            currSum = Math.max(val, currSum+val);
            
            maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }
}