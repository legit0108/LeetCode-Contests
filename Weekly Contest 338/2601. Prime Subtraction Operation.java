// Iterate from the end of nums and subtract the smallest prime number that can be subtracted to make nums strictly increasing (Greedy)

// TC: O(len*log(primesList.size()))
// SC: O(1)
// Ignoring time and space required for pre-computing primes

class Solution {
    public boolean primeSubOperation(int[] nums) {
        List<Integer> primesList = generatePrimes(1000);
        int len = nums.length;
        boolean isStrictlyIncreasing = true;
        int next = nums[len-1];
        
        for(int index=len-2; index>=0; index--){
            int curr = nums[index];
            
            if(curr>=next){
                int prime = binarySearch(curr, next, primesList);
                 
                if(prime!=-1) curr-=prime;
                else{
                    isStrictlyIncreasing = false;
                    break;
                }
            }
            
            next = curr;
        }
        
        return isStrictlyIncreasing;
    }
    
    private int binarySearch(int curr, int next, List<Integer> primesList){ // find smallest prime such thar curr-prime<next
        int low = 0;
        int high = primesList.size()-1;
        int smallestPrime = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            int prime = primesList.get(mid);
            
            if(prime>=curr) high = mid-1;
            else{
                if(curr-prime<next){
                    smallestPrime = prime;
                    high = mid-1;
                }else low = mid+1;
            }
        }
        
        return smallestPrime;
    }
    
    private List<Integer> generatePrimes(int val){ // generate all prime numbers<=val
        int size = val+1;
        boolean[] isPrime = new boolean[size];
        
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for(int num1=2; num1*num1<size; num1++){
            if(isPrime[num1]){
                for(int num2=num1*num1; num2<size; num2+=num1) isPrime[num2] = false;
            }
        }
        
        List<Integer> primesList = new ArrayList();
        for(int num=0; num<=val; num++){
            if(isPrime[num]) primesList.add(num);
        }
        
        return primesList;
    }
}