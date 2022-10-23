// TC: O(len^2*log(min(nums)))
// SC: O(1)

class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        
        for(int idx1=0; idx1<len; idx1++){
            int gcd = 0;
            
            for(int idx2=idx1; idx2<len; idx2++){
                gcd = getGcd(gcd, nums[idx2]);
                if(gcd==k) count++;
            }
        }
        
        return count;
    }
    
    private int getGcd(int a, int b){
        while(b!=0){
            int rem = a%b;
            a = b;
            b = rem;
        }
        
        return a;
    }
}