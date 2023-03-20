// Solution-1: Iterate bit by bit

// TC: O(logn)
// SC: O(1)

class Solution {
    public int[] evenOddBit(int n) {
        int even = 0;
        int odd = 0;
        int bit = 0;
        
        while(bit<32){
            int mask = 1<<bit;
            
            if((n&mask)!=0){
                if(bit%2==0) even++;
                else odd++;
            }
            
            bit++;
        }
        
        return new int[]{even, odd};
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Using masks for even and odd position

// TC: O(1)
// SC: O(1)

class Solution {
    public int[] evenOddBit(int n) {
        int evenMask = 0b0101010101;
        int oddMask = 0b1010101010;
        
        int even = Integer.bitCount(n&evenMask); // Integer.bitCount(n) works in O(1)
        int odd = Integer.bitCount(n&oddMask);
        
        return new int[]{even, odd};
    }
}