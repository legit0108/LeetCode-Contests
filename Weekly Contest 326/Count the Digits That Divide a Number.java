// TC: O(log(num))
// SC: O(1)

class Solution {
    public int countDigits(int num) {
       int number = num;
       int count = 0;
        
       while(number>0){
           int dig = number%10;
           
           if(num%dig==0) count++;
            
           number/=10;
       }
        
       return count;
    }
}