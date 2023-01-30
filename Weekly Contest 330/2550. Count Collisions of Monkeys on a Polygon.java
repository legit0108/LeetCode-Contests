/*
 Each monkey moves simultaneously to neighbouring vertex
 Collision happens if at least two monkeys reside on the same vertex after movement
 Each monkey moves only once
 2^n possible ways for monkeys to jump (either clockwise or anti-clockwise)
 No collision occurs if all monkeys move clockwise or all monkeys move anti-clockwise
 So, ans = 2^n-2, but wait, we have no collision in the case when n is even, and adjacents swap their positions
 So, when n is even, the ans is pow(2, n)-4, else the ans is pow(2, n)-2
 For now, pow(2, n)-2 passes if n is even (consider collision in the air or when swapping adjacent points)
 
 TC: O(logn)
 SC: O(1)
*/

class Solution {
    public int monkeyMove(int n) {
        long mod = (long)1e9+7;
        long result = pow(2, n, mod);
        
        int ans = (int)(((result-2l)+mod)%mod);
        if(ans<0) ans+=(int)mod;
        return ans;
    }
    
    private long pow(long x, long n, long mod){
        long result = 1;
        
        while(n>0){
            if(n%2!=0) result = ((result*x)+mod)%mod;
            x = ((x*x)+mod)%mod;
            n = n/2;
        }
        
        return result;
    }
}