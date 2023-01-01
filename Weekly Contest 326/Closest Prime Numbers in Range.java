// Prime sieve

// TC: O(right-left), assuming prime computation to be O(1) amortized
// SC: O(MAX_SIZE)

class Solution {
    static boolean[] isPrime;
    static final int MAX_SIZE= (int)1e6+1;
    
    static{
        isPrime = new boolean[MAX_SIZE];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for(int num1=2; num1*num1<MAX_SIZE; num1++){
            if(isPrime[num1]){
                for(int num2=num1*num1; num2<MAX_SIZE; num2+=num1){
                    isPrime[num2] = false;
                }
            }
        }
    }
    
    public int[] closestPrimes(int left, int right) {
        int minDiff = Integer.MAX_VALUE;
        int prevPrime = -1;
        int[] ans = new int[]{-1,-1};
        
        for(int num = left; num<=right; num++){
            if(isPrime[num]){
                if(prevPrime!=-1){
                    int diff = num-prevPrime;
                    
                    if(diff<minDiff){
                        minDiff = diff;
                        
                        ans[0] = prevPrime;
                        ans[1] = num;
                        
                        if(minDiff<3) break; // early exit, gap of 1 only occurs for [2,3] 
                    }
                }
                
                prevPrime = num;
            }
        }
        
        return ans;
    }
}