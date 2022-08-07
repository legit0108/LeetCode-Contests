// DP problem
// for each state , idx , we only need to look at states idx-1 , idx-2 , idx-3
// dp[idx] depends on dp[idx-1] , dp[idx-2] , dp[idx-3] 
// so we can implement our solution using only 3 variables instead of entire DP table

// TC : O(len)
// SC : O(1)

class Solution {
    public boolean validPartition(int[] nums) {
        boolean canPartition1 = false; // if we are at idx can we partition nums from [0...idx-1]
        boolean canPartition2= false; // if we are at idx can we partition nums from [0...idx-2]
        boolean canPartition3 = false; // if we are at idx can we partition nums from [0...idx-3]
        int len = nums.length;
        
        for(int idx =0;idx<len;idx++){
            boolean canPartition = false; // if we are at idx can we partition nums from [0...idx]
            
            if(idx-1>=0&&nums[idx-1]==nums[idx]){
                if(idx-2>=0) canPartition|=canPartition2;
                else canPartition = true;
            }
            
            if(idx-2>=0&&nums[idx-2]==nums[idx]&&nums[idx]==nums[idx-1]){
                if(idx-3>=0) canPartition|=canPartition3;
                else canPartition = true;
            }
            
            if(idx-2>=0){
                int secondLastVal = nums[idx-2];
                int lastVal = nums[idx-1];
                int currVal = nums[idx];
                    
                if(currVal==lastVal+1&&lastVal==secondLastVal+1){
                    if(idx-3>=0) canPartition|=canPartition3;
                    else canPartition = true;
                }
            }
            
            // update for further iterations
            canPartition3 = canPartition2;
            canPartition2 = canPartition1;
            canPartition1 = canPartition;
        }
        
        boolean canPartition = canPartition1; // canPartition1 set to final answer in last iteration
        return canPartition;
    }
}