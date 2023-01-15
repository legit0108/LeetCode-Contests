// Sliding window

// TC: O(len)
// SC: O(len)

class Solution {
    public long countGood(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        int len = nums.length;
        int start = 0;
        int end = 0;
        int pairs = 0;
        long goodSubarr = 0;
        
        while(start<end || end<len){
            while(pairs<k && end<len){
                int num = nums[end];
                
                int freq = map.getOrDefault(num, 0);
                pairs+=freq;
                map.put(num, freq+1);
                
                end++;
            }
            
            if(pairs>=k){
                long validSubarr = len-end+1;
                goodSubarr+=validSubarr;
            }
            
            int num = nums[start];
            
            int freq = map.get(num);
            pairs-=freq-1;
            map.put(num, freq-1);
            
            start++;
        }
        
        return goodSubarr;
    }
}