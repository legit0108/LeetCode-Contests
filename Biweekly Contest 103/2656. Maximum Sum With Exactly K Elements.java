// Let max be the maximum value in nums
// Return max+(max+1)+(max+2)+......+(max+k-1)

// TC: O(nums.length)
// SC: O(1)

class Solution {
    public int maximizeSum(int[] nums, int k) {
        int max = 0;
        for(int num: nums) max = Math.max(max, num);
        
        return sumUpto(max+k-1) - sumUpto(max-1);
    }
    
    private int sumUpto(int n){
        return n*(n+1)/2;
    }
}