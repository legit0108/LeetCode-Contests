// Method-1 : Loop till sqrt(n)

// TC : O(sqrt(n))
// SC : O(1)

class Solution {
    public int commonFactors(int a, int b) {
        if(a > b) return commonFactors(b, a);
        int count = 0;
        
        for(int num = 1; num*num<=a; num++){
            if(a%num==0){
                int factor1 = num;
                if(b%factor1==0) count++;
                
                int factor2 = a/num;
                if(factor2!=factor1 && b%factor2==0) count++;
            }
        }
        
        return count;
    }
}

// Method-2 : Loop till gcd(a, b)

// TC : O(log(min(a, b)) + gcd)
// SC : O(1)

class Solution {
    public int commonFactors(int a, int b) {
        int gcd = findGcd(a, b);
        int count = 0;
        
        for(int num=1; num<=gcd; num++){
            if(a%num==0 && b%num==0) count++;
        }
        
        return count;
    }
    
    private int findGcd(int a, int b){
        while(b != 0){
            int rem = a%b;
            a = b;
            b = rem;
        }
        
        return a;
    }
}