// If n>1, we can get 2, 3, 4, .... n
// If n=1, we can only get 1

// TC: O(1)
// SC: O(1)

class Solution {
    public int distinctIntegers(int n) {
        if(n==1) return 1;
        else return n-1;
    }
}