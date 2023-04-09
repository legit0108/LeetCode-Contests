// Prefix/Suffix

// We do two traversals, left to right and right to left
// When we traverse left to right we find out sum of nums[index] for all indices smaller than this index having equal value
// When we traverse right to left we find out sum of nums[index] for all indices greater than this index having equal value

// TC: O(len)
// SC: O(len)

class Solution {
    public long[] distance(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Long> sumMap = new HashMap();
        HashMap<Integer, Long> freqMap = new HashMap();
        long[] ans = new long[len];
        
        for(int index =0; index<len; index++){
            int num = nums[index];
            
            long freq = freqMap.getOrDefault(num, 0l);
            long sum = sumMap.getOrDefault(num, 0l);
             
            ans[index]+=freq*((long)index)-sum;
            
            freqMap.put(num, freq+1l);
            sumMap.put(num, sum+(long)index);
        }
        
        sumMap = new HashMap();
        freqMap = new HashMap();
        
        for(int index=len-1; index>=0; index--){
            int num = nums[index];
            
            long freq = freqMap.getOrDefault(num, 0l);
            long sum = sumMap.getOrDefault(num, 0l);
             
            ans[index]+=sum-freq*((long)index);
            
            freqMap.put(num, freq+1l);
            sumMap.put(num, sum+(long)index);
        }
        
        return ans;
    }
}