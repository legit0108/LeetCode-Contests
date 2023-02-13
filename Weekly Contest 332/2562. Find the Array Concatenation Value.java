// TC: O(len*log(num)), for num in nums 
// SC: O(1)

class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len-1;
        long concVal = 0;
        
        while(start<=end){
            if(start==end){
                concVal+=nums[start];
            }else{
                concVal+=concat(nums[start], nums[end]);
            }
            
            start++;
            end--;
        }
        
        return concVal;
    }
    
    private long concat(long num1, long num2){
        long factor = 1;
        
        while(factor<=num2) factor*=10;
        
        return num1*factor+num2;
    }
}