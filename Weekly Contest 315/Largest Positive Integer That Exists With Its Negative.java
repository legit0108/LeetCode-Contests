// TC: O(nums.length)
// SC: O(nums.length)

class Solution {
    public int findMaxK(int[] nums) {
        Set<Integer> set = new HashSet();
        int max = -1;
        
        for(int num : nums){
            if(num>0 && set.contains(-num)) max = Math.max(max,num);
            else if(num<0 && set.contains(-num)) max = Math.max(max, -num);
            
            set.add(num);
        }
        
        return max;
    }
}