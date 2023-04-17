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


// Optimized solution
// Find all divisors for all numbers in nums optimally and then find divisor with the maximum divisibility score

class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
       HashMap<Integer, Integer> map = new HashMap(); // count of numbers a divisor divides
        
       for(int num: nums){
           List<Integer> allDivisors = getAllDivisors(num);
           
           for(int divisor: allDivisors){
               map.put(divisor, map.getOrDefault(divisor, 0)+1); 
           }
       }
        
       int maxDivCount = 0;
       int divisorWithMaxDivCount = Integer.MAX_VALUE;
       
       for(int divisor: divisors){
           int freq = map.getOrDefault(divisor, 0);
           
           if(freq>maxDivCount){
               maxDivCount = freq;
               divisorWithMaxDivCount = divisor;
           }else if(freq==maxDivCount) divisorWithMaxDivCount = Math.min(divisor, divisorWithMaxDivCount);
       }
        
       return divisorWithMaxDivCount;
    }
    
    private List<Integer> getAllDivisors(int num){
        HashMap<Integer, Integer> primes = new HashMap();
        
        for(int div=2; div*div<=num; div++){
            while(num%div==0){
                primes.put(div, primes.getOrDefault(div, 0)+1);
                num/=div;
            }    
        }
        
        if(num>1) primes.put(num, primes.getOrDefault(num, 0)+1);
        
        List<Integer> divisors = new ArrayList();
        divisors.add(1);
        
        for(int prime: primes.keySet()){
            int freq = primes.get(prime);
            int curr = 1;
            int size = divisors.size();
            
            while(freq>0){
                curr = curr*prime;
                
                for(int index=0; index<size; index++){
                    divisors.add(divisors.get(index)*curr);
                }
                
                freq--;
            }
        }
        
        return divisors;
    }
}