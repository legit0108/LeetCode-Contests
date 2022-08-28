// TC : O(len*log(len) + queriesLen*log(len))
// SC : O(1) excluding output

class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int len = nums.length;
        for(int idx =1;idx<len;idx++) nums[idx]+=nums[idx-1];
        
        int queriesLen = queries.length;
        int ans[] = new int[queriesLen];
        
        for(int idx =0;idx<queriesLen;idx++) ans[idx] = binarySearch(nums,queries[idx]);
        
        return ans;
    }
    
    private int binarySearch(int nums[],int val){
        int low = 0;
        int high = nums.length-1;
        int ans = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            if(nums[mid]<=val){
                ans = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return ans+1;
    }
}