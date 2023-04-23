// -50<=nums[i]<=50 so we can traverse all negative values in current window and find xth smallest integer
// TC: O(len*50)
// SC: O(50) ignoring output space

class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int len = nums.length;
        int[] ans = new int[len-k+1];
        int[] map = new int[51];
        
        for(int index=0; index<len; index++){
            int num = nums[index];
            if(num<0) map[-num]++;
            
            if(index>=k-1){
                int count = 0;
                int beauty = 0;
                    
                for(int val=50; val>=1; val--){
                    count+=map[val];
                    
                    if(count>=x){
                        beauty = -val;
                        break;
                    }
                }
                
                ans[index-k+1] = beauty;
                
                num = nums[index-k+1];
                if(num<0) map[-num]--;
            }
        }
        
        return ans;
    }
}