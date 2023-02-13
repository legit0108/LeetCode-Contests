// Note that the condition 0 <= i < j < n is not violated on sorting nums

// Solution-1: Sorting + Binary Search
// TC: O(len*log(len))
// SC: O(1)

class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int len = nums.length;
        long count = 0;
        
        for(int idx = 0; idx<len; idx++){
            long val = nums[idx];
            long min = (long)lower-val;
            long max = (long)upper-val;
            
            int left = -1;
            int low = 0;
            int high = len-1;
            
            while(low<=high){
                int mid = low + (high-low)/2;
                
                if(nums[mid]>=min){
                    left = mid;
                    high = mid-1;
                }else{
                    low = mid+1;   
                }
            }
            
            int right = -1;
            low = 0;
            high = len-1;
            
            while(low<=high){
                int mid = low + (high-low)/2;
                
                if(nums[mid]<=max){
                    right = mid;
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
            
            if(right!=-1 && left!=-1){
                long curr = (long)right-(long)left+1l; 
                if(idx>=left && idx<=right) curr--;
                count+=curr;
            }
        }
        
        return count/2;
    }
}


// Solution-2: Sorting + Two Pointers
// TC: O(len*log(len))
// SC: O(1)

class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        
        int len = nums.length;
        long count = find(nums, upper)-find(nums, lower-1);
        
        return count;
    }
    
    private long find(int[] nums, int val){ // get count of all pairs having sum <= val
        long count = 0;
        int start = 0;
        int end = nums.length-1;
        
        while(start<end){
            if(nums[start]+nums[end]>val) end--;
            else{
                count+=(long)end-(long)start;
                start++;
            }
        }
        
        return count;
    }
}