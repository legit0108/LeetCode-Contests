// Same as Aggressive Cows (SPOJ)

// TC: O(len * (log(high-low)))
// SC: O(1)

class Solution {
    public int maximumTastiness(int[] price, int k) {
        int len = price.length;
        Arrays.sort(price);
        
        int low = 0;
        int high = price[len-1]-price[0];
        int maxAbsDiff = 0;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(absDiffPossible(price, len, mid, k)){
                maxAbsDiff = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return maxAbsDiff;
    }
    
    private boolean absDiffPossible(int[] price, int len, int diff, int k){
        int count = 1;
        int prev = price[0];
        
        for(int idx=1; idx<len; idx++){
            int curr = price[idx];
            
            if((curr-prev) >= diff){
                count++;
                prev = curr;
            }
        }
        
        return count>=k;
    }
}