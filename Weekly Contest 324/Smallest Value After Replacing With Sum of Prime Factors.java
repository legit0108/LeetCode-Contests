// Solution - 1

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

// Solution - 2 : Prime sieve

// TC : O(MAX_SIZE*log(log(MAX_SIZE)))
// SC : O(MAX_SIZE)

class Solution {
    private int MAX_SIZE = (int)1e5+1;
    private static Integer smallestPrimeFactor[];
    
    public int smallestValue(int num) {
        if(smallestPrimeFactor==null) init();
        
        while(true){
            int sumOfPrimeFactors = getSumOfPrimeFactors(num);
            
            if(num==sumOfPrimeFactors) break; 
            else num = sumOfPrimeFactors;
        }
        
        return num;
    }
    
    private int getSumOfPrimeFactors(int num){
        int sumOfPrimeFactors = 0;
        
        while(num>1){
            int smallestPrime = smallestPrimeFactor[num];
            
            while((num%smallestPrime)==0){
                num/=smallestPrime;
                sumOfPrimeFactors+=smallestPrime;
            }
        }

        return sumOfPrimeFactors;
    }
    
    private void init(){
        smallestPrimeFactor = new Integer[MAX_SIZE];
        
        for(int num=2; num<MAX_SIZE; num++) smallestPrimeFactor[num] = num;
        
        for(int prime=2; prime*prime<MAX_SIZE; prime++){
            if(smallestPrimeFactor[prime]==prime){
                
                for(int num=prime*prime; num<MAX_SIZE; num+=prime){
                    if(smallestPrimeFactor[num]==num) smallestPrimeFactor[num] = prime;
                }
            }
        }
    }
}