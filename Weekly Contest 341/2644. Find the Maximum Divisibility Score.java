// TC: O(nums.length*divisors.length)
// SC: O(1)

class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        int maxDivCount = 0;
        int divWithMaxDivCount = Integer.MAX_VALUE;
        int divisorsLen = divisors.length;
        int numsLen = nums.length;
        
        for(int divisorsIndex=0; divisorsIndex<divisorsLen; divisorsIndex++){
            int divCount = 0;
            int divisor = divisors[divisorsIndex];
            
            for(int index=0; index<numsLen; index++){
                if((nums[index]%divisor)==0) divCount++;
            }
            
            if(divCount>maxDivCount){
                divWithMaxDivCount = divisor;
                maxDivCount = divCount;
            }else if(divCount==maxDivCount) divWithMaxDivCount = Math.min(divWithMaxDivCount, divisor);
        }
        
        return divWithMaxDivCount;
    }
}