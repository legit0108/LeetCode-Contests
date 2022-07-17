// TC : O(len*log(max(nums)))
// SC : O(len)

class Solution {
    public int maximumSum(int[] nums) {
        int ans = -1;
        HashMap<Integer,Integer> map = new HashMap();
        int len = nums.length;
        
        for(int idx =0;idx<len;idx++){
           int sum = getSum(nums[idx]);
            
           if(map.containsKey(sum)){
               ans = Math.max(ans,map.get(sum)+nums[idx]);
           }
           
           map.put(sum,Math.max(map.getOrDefault(sum,0),nums[idx])); 
        }
        
        return ans;
    }
    
    private int getSum(int num){
        int sum = 0;
        
        while(num>0){
            sum+=num%10;
            num/=10;
        }
        
        return sum;
    }
}