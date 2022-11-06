// Solution-1: Use extra space

// TC: O(len)
// SC: O(len)

class Solution {
    public int[] applyOperations(int[] nums) {
        int len = nums.length;
        int ans[] = new int[len];
        int startIdx = 0;
        
        for(int idx=0; idx<len; idx++){
           if(idx+1<len && (nums[idx]==nums[idx+1])){
               nums[idx]*=2;
               nums[idx+1] = 0;
           }
           
           if(nums[idx]!=0) ans[startIdx++] = nums[idx];
        }
        
        return ans;
    }
}

// Solution-2: Linear time single pass

// TC: O(len)
// SC: O(1)

class Solution {
    public int[] applyOperations(int[] nums) {
        int len = nums.length;
        int pivotIdx = 0;
        
        for(int idx =0; idx<len; idx++){
            if(idx+1<len && (nums[idx]==nums[idx+1])){
                nums[idx]*=2;
                nums[idx+1] = 0;
            }
            
            if(nums[idx]!=0){
                swap(nums, idx, pivotIdx);
                pivotIdx++;
            }
        }
        
        return nums;
    }
    
    private void swap(int nums[], int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}