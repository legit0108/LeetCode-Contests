// Pick 1, then 0, then -1

// TC: O(1)
// SC: O(1)

class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if(k<=numOnes) return k;
        else if(k<=numOnes+numZeros) return numOnes;
        else return numOnes - (k-(numOnes+numZeros));
    }
}