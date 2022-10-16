// Check for all numbers <=num whether reverse of number plus original number equals num

// TC: O(num*len(number)) ~ O(num)
// SC: O(1)

class Solution {
    public boolean sumOfNumberAndReverse(int num) {
        for(int number = num/2; number<=num; number++){ // small optimization: start from num/2 instead of 0
            if(number+rev(number)==num) return true;
        }
        
        return false;
    }
    
    private int rev(int num){
        int revNum = 0;
        
        while(num>0){
            revNum = revNum*10+num%10;
            num/=10;
        }
        
        return revNum;
    }
}