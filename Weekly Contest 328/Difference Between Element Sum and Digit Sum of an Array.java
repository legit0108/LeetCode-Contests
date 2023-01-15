// Implementation

// TC: O(nums.length * log(max(nums)))
// SC: O(1)

class Solution {
    public int differenceOfSum(int[] nums) {
        int sum = 0;
        int digitSum = 0;
        
        for(int num: nums){
            sum+=num;
            digitSum+=getDigitSum(num);
        }
        
        return Math.abs(sum-digitSum);
    }
    
    private int getDigitSum(int num){
        int digitSum = 0;
        
        while(num>0){
            int digit = num%10;
            digitSum+=digit;
            num/=10;
        }
        
        return digitSum;
    }
}