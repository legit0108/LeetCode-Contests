// TC: O(len)
// SC: O(1) if output space ignored

class Solution {
    public long[] findPrefixScore(int[] nums) {
        int len = nums.length;
        long[] ans = new long[len];
        long max = 0;
        
        for(int index=0; index<len; index++){
            long num = nums[index];
            max = Math.max(max, num);
             
            ans[index] = max+num;
            if(index>0) ans[index]+=ans[index-1];
        }
        
        return ans;
    }
}