// Solution-1: Brute force
// TC: O(n)

class Solution {
    public int sumOfMultiples(int n) {
        int sum = 0;
        
        for(int num =1; num<=n; num++){
            if(num%3==0 || num%5==0 || num%7==0) sum+=num;
        }
        
        return sum;
    }
}


// Solution-2: Math
// n(a U b U c) = n(a) + n(b) + n(c) - n(a & b) - n(b & c) - n(c & a) + n(a & b & c)

// TC: O(1)

class Solution {
    public int sumOfMultiples(int n) {
        int sum = sumOfMultiples(n, 3) + sumOfMultiples(n, 5) + sumOfMultiples(n, 7)
                 -sumOfMultiples(n, 15) - sumOfMultiples(n, 35) - sumOfMultiples(n, 21)
                 +sumOfMultiples(n, 105);
        
        return sum;
    }
    
    private int sumOfMultiples(int n, int k){
        int count = n/k; // first we get count of multiples of k in [1,n]
        
        // Example: for n = 13 and k = 5 we have: 5 and 10
        // Number of multiples of 5 in [1,13] = 2
        // 5 + 10 = 5*(1+2) = k * sum of first count numbers = k * count*(count+1)/2

        return k*count*(count+1)/2;
    }
}