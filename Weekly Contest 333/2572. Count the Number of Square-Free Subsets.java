/*
 Ideas:
 
 -> Count the number of subsets without iterating over the subsets
 -> Make buckets of square-free numbers (bucket -> product of nums)
 -> Consider 10 prime numbers <30
 -> For any of the prime numbers (for a given num), if the count becomes >1, then it is not useful
 -> Total square-free numbers = 2^10 (because we have 10 prime numbers)
 -> We need to find the number of subsets with product X
 -> Hash product X to square-free number, this works because we have only 1024 distinct square-free numbers
 
 TC: O(len*1024)
 SC: O(len*1024)
*/

class Solution {
    private long mod = (long)1e9+7;
    private long[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    private long[][] dp;
    
    public int squareFreeSubsets(int[] nums) {
       List<Integer> filteredNums = new ArrayList();
       int len = nums.length;
       dp = new long[len][1025];
       for(int idx=0; idx<len; idx++) Arrays.fill(dp[idx], -1);
       long ones = 0;
        
       for(int num: nums){
           if(num==1) ones++;
           else filteredNums.add(num);
       }
        
       long subsetsOfOne = 1;
       while(ones>0){
           subsetsOfOne = ((subsetsOfOne*2l)+mod)%mod;
           ones--;
       } 
       subsetsOfOne = ((subsetsOfOne - 1l)+mod)%mod; 
        
       long nonEmptySubsetsExcludingOne = 0;
       for(int mask=1; mask<1024; mask++){
           nonEmptySubsetsExcludingOne = (nonEmptySubsetsExcludingOne + countSubsets(0, mask, filteredNums, filteredNums.size()))%mod; 
       }
        
       long result = ((nonEmptySubsetsExcludingOne + subsetsOfOne)+mod)%mod;
       result = ((result + (((nonEmptySubsetsExcludingOne*subsetsOfOne)+mod)%mod))+mod)%mod;
       
       int ans = (int)(result%mod);
       if(ans<0) ans+=(int)mod;
        
       return ans; 
    }
    
    private long countSubsets(int idx, int mask, List<Integer> nums, int size){
        if(mask==0) return 1l;
        else if(idx==size) return 0;
        
        long ans = dp[idx][mask];
        if(ans!=-1) return ans;
        
        long product = maskToProduct(mask);
        long val = nums.get(idx);
        
        ans = countSubsets(idx+1, mask, nums, size);
        if(product%val==0){
            ans = ans + countSubsets(idx+1, convertToMask(product/val), nums, size);
        }
        
        return dp[idx][mask] = ans;
    }
    
    private int convertToMask(long product){
        int mask = 0;
        
        for(int idx=0; idx<primes.length; idx++){
            if((product%primes[idx])==0) mask = mask | (1<<idx);
        }
        
        return mask;
    }
    
    private long maskToProduct(int mask){
        long product = 1;
        
        for(int idx=0; idx<primes.length; idx++){
            if((mask&(1<<idx))!=0) product*=primes[idx];
        }
        
        return product;
    }
}