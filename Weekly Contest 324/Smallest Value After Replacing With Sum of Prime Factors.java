// TC : O(num*sqrt(num)), can be optimized using prime sieve
// SC : O(1)

class Solution {
    public int smallestValue(int num) {
        while(true){
            int sumOfPrimeFactors = getSumOfPrimeFactors(num);
            
            if(num==sumOfPrimeFactors) break; 
            else num = sumOfPrimeFactors;
        }
        
        return num;
    }
    
    private int getSumOfPrimeFactors(int num){
        int sumOfPrimeFactors = 0;
        
        for(int div=2; div*div<=num; div++){
            while(num%div==0){
                num/=div;
                sumOfPrimeFactors+=div;
            }
        }

        if(num>1) sumOfPrimeFactors+=num;
        
        return sumOfPrimeFactors;
    }
}