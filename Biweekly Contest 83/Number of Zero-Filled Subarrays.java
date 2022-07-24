// if we have 'zeros' consecutive zeros
// they contribute 'zeros*(zeros+1)/2' subarrays

// TC : O(len)
// SC : O(1)

class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long count = 0;
        int len = nums.length;
        
        for(int idx =0;idx<len;idx++){
            long zeros = 0;
            
            while(idx<len&&nums[idx]==0){
                zeros++;
                idx++;
            }
            
            count+=(zeros*(zeros+1))/2;
        }
        
        return count;
    }
}