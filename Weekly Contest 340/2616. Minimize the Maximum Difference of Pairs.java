// Binary Search on answer

/*
 -> Lets suppose nums is sorted: [a, b, c, d]
 -> We sort the array to minimize difference between elements (adjacent elements in sorted array have minimal difference)
 -> If b-a<=diff, where diff is some difference, it is always optimal to pick this pair
 -> Any other pairing will eventually lead to fewer number of pairs, that is not what we want
 -> We want to maximize our pairs such that they become >= threshold p
 -> If b-a>diff, it is optimal to skip a since the difference of a with any element will be greater than d (nums is sorted)
 -> We greedily pick pairs as early as possible to maximize number of pairs we can pick (so that they cross given threshold p)

 TC: O(len*log(len)) + O(len*log(high-low))
 SC: O(1) 
 */

 class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int len = nums.length;
        int low = 0;
        int high = nums[len-1]-nums[0];
        
        while(low<high){
            int mid = low + (high-low)/2;
            
            if(diffPossible(nums, mid, p, len)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private boolean diffPossible(int[] nums, int diff, int reqdPairs, int len){
        int index = 0;
        int pairs = 0;
        
        while(index<len-1){
            if((nums[index+1]-nums[index])<=diff){
                pairs++;
                index+=2;
            }else index++;
        }
        
        return pairs>=reqdPairs;
    }
}