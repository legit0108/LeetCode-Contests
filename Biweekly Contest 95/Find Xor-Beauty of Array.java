// Expand the expression, terms cancel each other out (a^a = 0), only xor of array remains

// TC: O(nums.length)
// SC: O(1)

class Solution {
    public int xorBeauty(int[] nums) {
        int xor = 0;
        
        for(int num: nums) xor^=num;
        
        return xor;
    }
}