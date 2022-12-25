// TC: O(log(1e10))
// SC: O(1)

class Solution {
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long div1 = divisor1;
        long div2 = divisor2;
        long gcd = getGcd(div1, div2);
        long lcm = (div1*div2)/gcd;
        
        long low = 1;
        long high = (long)1e10;
        long max = 0;
        
        while(low<=high){
            long mid = low + (high-low)/2;
            
            long notDivsbByDiv1 = mid - (mid/div1);
            long notDivsbByDiv2 = mid - (mid/div2);
            long notDivsbByBoth = mid - (mid/div1) - (mid/div2) + (mid/lcm);
            long notDivsbByEither = notDivsbByDiv1 + notDivsbByDiv2 - notDivsbByBoth;
            
            if(notDivsbByDiv1>=uniqueCnt1 && notDivsbByDiv2>=uniqueCnt2 
               && notDivsbByEither>=uniqueCnt1+uniqueCnt2){
                max = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return (int)max;
    }
    
    private long getGcd(long num1, long num2){
        while(num2>0){
            long rem = num1%num2;
            num1 = num2;
            num2 = rem;
        }
        
        return num1;
    }
}