// TC : O(len)
// SC : O(1)

class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        int best1 = 0;
        int best2 = 0;
        int factor1 = 0;
        int factor2 = 0;
        int len = nums1.length;
        
        for(int idx=0;idx<len;idx++){
            // process for nums1
            int currMax = Math.max(best1-nums1[idx]+nums2[idx],-nums1[idx]+nums2[idx]);
            factor1 = Math.max(factor1,currMax);
            if(currMax==(-nums1[idx]+nums2[idx])){
                best1 = -nums1[idx]+nums2[idx];
            }else{
                best1-=nums1[idx];
                best1+=nums2[idx];
            }
            
            //process for nums2
            currMax = Math.max(best2-nums2[idx]+nums1[idx],-nums2[idx]+nums1[idx]);
            factor2 = Math.max(factor2,currMax);
            if(currMax==(-nums2[idx]+nums1[idx])){
                best2 = -nums2[idx]+nums1[idx];
            }else{
                best2-=nums2[idx];
                best2+=nums1[idx];
            }
            
            sum1+=nums1[idx];
            sum2+=nums2[idx];
        }
        
        int ans = Math.max(sum1,sum2);
        ans = Math.max(ans,Math.max(sum1+factor1,sum2+factor2));
        return ans;
    }
}