// Bit manipulation

// TC : O(log2(n))
// SC : O(1)

class Solution {
    public int minimizeXor(int num1, int num2) {
        int x = num1; // to minimize xor take x as num1, because num1 ^ num1 == 0
        int reqdBitCount = Integer.bitCount(num2);
        int currBitCount = Integer.bitCount(x);
        
        if(currBitCount>reqdBitCount){
            // if currBitCount is greater than reqdBitCount
            // we need to set off some bits in x
            // this will increase value of x^num1
            // the increase should be minimum so we set off LSB bits
            
            int bit = 0;
            
            while(currBitCount>reqdBitCount){
                int mask = 1<<bit;
                
                if((mask&x)>0){
                    x^=mask;
                    currBitCount--;
                }
                
                bit++;
            }
        }else{
            // if currBitCount is smaller than reqdBitCount
            // we need to set on some bits in x
            // this will increase value of x^num1
            // the increase should be minimum so we set on LSB bits
            
            int bit = 0;
            
            while(currBitCount<reqdBitCount){
                int mask = 1<<bit;
                
                if((mask&x)==0){
                    x|=mask;
                    currBitCount++;
                }
                
                bit++;
            }
        }
        
        return x;
    }
}