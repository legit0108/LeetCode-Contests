/*

Bitwise AND of every pair of elements that are in different positions in the subarray is equal to zero implies 
elements within subarray have distinct set bits, use sliding window to find maximum size subarray.

TC : O(len)
SC : O(1)

*/

class Solution {
    public int longestNiceSubarray(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = 0;
        int maxLen = 1;
        int mask = 0;
        
        while(end<len){
            while((mask & nums[end])>0){
                mask = mask ^ nums[start];
                start++;
            }
            
            mask = mask | nums[end];
            maxLen = Math.max(maxLen,end-start+1);
            end++;
        }
        
        return maxLen;
    }
}