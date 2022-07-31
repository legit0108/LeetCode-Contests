// TC : O(len)
// SC : O(len)

// In first operation all numbers equal to smallest number become 0 
// In second operation all numbers equal to next smallest number become 0
// Indirectly we are told to find number of unique elements in nums

class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet();
        
        for(int num : nums){
            if(num>0) set.add(num);
        }
        
        return set.size();
    }
}