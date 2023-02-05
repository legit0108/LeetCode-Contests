/*
 Binary search on answer
 
 ->Rob as early as possible so that you can rob more houses
 ->arr: [A1, A2, ...., An]
 ->A1<=capability, A2<=capability
 ->Rob A1 -> options left to rob: [A3, ...., An]
 ->Rob A2 -> options left to rob: [A4, ...., An]
 ->So it is always better to rob the first possible house
 
 TC: O(len*log(high-low))
 SC: O(1)
*/

class Solution {
    public int minCapability(int[] nums, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        
        for(int num: nums){
            low = Math.min(low, num);
            high = Math.max(high, num);
        }
        
        int ans = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(capabilityPossible(nums, mid, k)){
                ans = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return ans;
    }
    
    private boolean capabilityPossible(int[] nums, int capability, int k){
        int len = nums.length;
        int count = 0;
        
        for(int idx=0; idx<len; idx++){
            int num = nums[idx];
            
            if(num<=capability){
                count++;
                idx++;
            }
        }
        
        return count>=k;
    }
}