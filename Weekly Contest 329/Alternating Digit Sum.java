// Implementation-based

// TC: O(logn)
// SC: O(1)

class Solution {
    public int alternateDigitSum(int n) {
        int sum = 0;
        int len = 0;
        int flag = 0;
        
        while(n>0){
            int dig = n%10;
            
            if(flag==0) sum+=dig;
            else sum+=-dig;
            
            len++;
            n/=10;
            flag^=1;
        }
        
        if(len%2==0) sum = -sum;
        return sum;
    }
}