// Method - 1 : Sorting + GCD

// TC : O(len*log(num) + len*log(len))
// SC : O(1)

class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int gcd =0;
        Arrays.sort(nums);
        int minDels = 0;
        int len = nums.length;
        
        for(int num : numsDivide){
            gcd = getGcd(num,gcd);
        }
        
        
        for(int num : nums){
            if(gcd%num==0){
                break;
            }else minDels++;
        }
        
        if(minDels==len) return -1;
        return minDels;
    }
    
    private int getGcd(int num1,int num2){
        while(num2!=0){
            int temp = num1%num2;
            num1 = num2;
            num2 = temp;
        }
        
        return num1;
    }
}

// Method - 2 : No sort

// TC : O(len*log(num) + len)
// SC : O(1)

class Solution {
    public int minOperations(int[] nums, int[] numsDivide) {
        int gcd =0;
        int minDels = 0;
        int len = nums.length;
        int minNum = Integer.MAX_VALUE;
        
        for(int num : numsDivide){
            gcd = getGcd(num,gcd);
        }
        
        
        for(int num : nums){
            if(gcd%num==0){
                minNum = Math.min(minNum,num);
            }
        }
        
        for(int num : nums){
            if(num<minNum) minDels++;
        }
        
        if(minDels==len) return -1;
        return minDels;
    }
    
    private int getGcd(int num1,int num2){
        while(num2!=0){
            int temp = num1%num2;
            num1 = num2;
            num2 = temp;
        }
        
        return num1;
    }
}