// Divide into even and odd array 

// TC: O(lenlog(len))
// SC: O(1)

class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        
        int len = nums.length;
        int smallestEvenIdx = 0;
        int smallestOddIdx = 0;
        long ops = 0;
        int idx = 0;
        
        while(idx<len){
           int val = nums[idx];
           
           if(val%2==0){
               while(target[smallestEvenIdx]%2!=0) smallestEvenIdx++;
               if(target[smallestEvenIdx]>val) ops+=(target[smallestEvenIdx]-val)/2; 
               smallestEvenIdx++; 
           }else{
               while(target[smallestOddIdx]%2==0) smallestOddIdx++;
               if(target[smallestOddIdx]>val) ops+=(target[smallestOddIdx]-val)/2;    
               smallestOddIdx++;      
           }
            
           idx++; 
        }
        
        return ops;
    }
}