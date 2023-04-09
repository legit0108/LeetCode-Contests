// TC: O(n*sqrt(max(nums))), can be optimized to O(n) with sieve (ignoring cost of computing primes)
// SC: O(1)

class Solution {
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int maxPrime = 0;
        
        for(int index=0; index<n; index++){
            int num1 = nums[index][index];
            int num2 = nums[index][n-index-1];
            
            if(isPrime(num1)) maxPrime = Math.max(maxPrime, num1);
            if(isPrime(num2)) maxPrime = Math.max(maxPrime, num2);
        }
        
        return maxPrime;
    }
    
    private boolean isPrime(int num){
        if(num<2) return false;
        
        for(int div=2; div*div<=num; div++){
            if(num%div==0) return false;
        }
        
        return true;
    }
}