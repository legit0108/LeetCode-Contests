// HashMap + Sliding window

// TC: O(len)
// SC: O(len)

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap();
        long currSum = 0;
        long maxSum = 0;
        
        for(int idx =0; idx<k; idx++){
            int val = nums[idx];
            
            map.put(val, map.getOrDefault(val, 0)+1);
            currSum+=(long)val;
        }
        
        if(map.size()==k) maxSum = currSum;
        
        for(int idx = k; idx<len; idx++){
            int elemToAcquire = nums[idx];
            int elemToRelease = nums[idx-k];
            
            map.put(elemToAcquire, map.getOrDefault(elemToAcquire, 0)+1);
            map.put(elemToRelease, map.getOrDefault(elemToRelease, 0)-1);
            currSum+=(long)elemToAcquire;
            currSum-=(long)elemToRelease;
            
            if(map.get(elemToRelease)==0) map.remove(elemToRelease);
            if(map.size()==k) maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }
}