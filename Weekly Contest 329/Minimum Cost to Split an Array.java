// Partition-DP

// TC: O(len^2)
// SC: O(len)

class Solution {
    public int minCost(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len+1];
        
        for(int idx1=len-1; idx1>=0; idx1--){
            int ans = Integer.MAX_VALUE;
            int currLen = 0; 
            HashMap<Integer, Integer> map = new HashMap();
            
            for(int idx2=idx1; idx2<len; idx2++){
                int num = nums[idx2];
                
                if(!map.containsKey(num)) map.put(num, 1);
                else{
                    map.put(num, map.get(num)+1);
                    int freq = map.get(num);
                    
                    if(freq==2) currLen+=2;
                    else currLen++;
                }
                
                ans = Math.min(ans, k+currLen+dp[idx2+1]);
            }
            
            dp[idx1] = ans;
        }
        
        return dp[0];
    }
}