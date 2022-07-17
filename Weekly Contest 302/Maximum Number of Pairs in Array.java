// TC : O(len)
// SC : O(len)

class Solution {
    public int[] numberOfPairs(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap();
        int len = nums.length;
        int leftOver = len;
        int pairs = 0;
        
        for(int idx =0;idx<len;idx++){
            if(map.containsKey(nums[idx])){
                map.put(nums[idx],map.get(nums[idx])-1);
                if(((int)map.get(nums[idx]))==0) map.remove(nums[idx]);
                leftOver-=2;
                pairs++;
            }else map.put(nums[idx],map.getOrDefault(nums[idx],0)+1);
        }
        
        return new int[]{pairs,leftOver};
    }
}