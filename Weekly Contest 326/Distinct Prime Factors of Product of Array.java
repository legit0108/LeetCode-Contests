// Prime factorization + set

// TC: O(nums.length*sqrt(num)), can be optimized a bit by precomputing primes
// SC: O(distinct primes)

class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet();
        
        for(int num : nums){
            for(int div=2; div*div<=num; div++){
                if(num%div==0){
                    set.add(div);
                    
                    while(num%div==0) num/=div;
                }
            }
                
            if(num>1) set.add(num);
        }
        
        return set.size();
    }
}