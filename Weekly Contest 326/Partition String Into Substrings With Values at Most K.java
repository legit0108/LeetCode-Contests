// Solution-1: Binary search

// TC: O(len*log(len))
// SC: O(1)

class Solution {
    public int minimumPartition(String s, int k) {
        int len = s.length();
        int low = 1;
        int high = len;
        int minSubstrs = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(canPartition(s, mid, k, len)){
                minSubstrs = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return minSubstrs;
    }
    
    private boolean canPartition(String s, int parts, int k, int len){
        long num = 0;
        int partitions = 0;
        
        for(int idx =0; idx<len; idx++){
            long dig = s.charAt(idx)-'0';
            
            if(num*10l+dig>k){
                partitions++;
                
                if(dig>k) return false;
                
                num = dig;
            }else num = num*10l+dig;
        }
        
        return (partitions+1)<=parts;
    }
}

// Solution-2: Greedy
// Form new partition if and only if current value exceeds k

// TC: O(len)
// SC: O(1)

class Solution {
    public int minimumPartition(String s, int k) {
        int len = s.length();
        int partitions = 0;
        long val = 0;
        
        for(int idx=0; idx<len; idx++){
            int dig = s.charAt(idx)-'0';
            long newVal = val*10 + dig;
            
            if(newVal>k){
                if(dig>k) return -1;
                
                partitions++;
                val = dig;
            }else val = newVal;
        }
        
        return partitions+1;
    }
}