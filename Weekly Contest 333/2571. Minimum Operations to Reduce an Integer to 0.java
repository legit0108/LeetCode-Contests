/*
Solution-1: 

We only need to take care of continuous 1s in binary representation of n
 Single 1 -> minOps+=1
 Multiple 1 -> minOps+=2
 
TC: O(logn)
SC: O(1)
*/

class Solution {
    public int minOperations(int n) {
        int minOps = 0;
        int ones = 0;
        int bit = 0;
        
        while(n>0){
            if((n&1)!=0){ 
                ones++;
                n>>=1;
            }else{
                if(ones==1){
                    minOps++;
                    n>>=1;
                }else if(ones>1){
                    n|=1;
                    minOps++;
                }else n>>=1;
                
                ones = 0;
            }
        }
        
        if(ones==1) minOps++;
        else if(ones>1) minOps+=2;
        
        return minOps;
    }
}


/*
 Solution-2: 

 Trick to tansform multiple 1s
 n ^ (n*3) 
  -> Multiple continuous 1 transform into 2 bits
  -> Single 1 transform into 1 bit 
  -> Count bits in n ^ (n*3)
  
 TC: O(1)
 SC: O(1)
*/

class Solution {
    public int minOperations(int n) {
        return Integer.bitCount(n ^ (n*3));
    }
}