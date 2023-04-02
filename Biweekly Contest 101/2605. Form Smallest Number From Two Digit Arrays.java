/* 
 Ideas:

 -> If any single digit occurs twice, we return minimum such digit
 -> Else we return a two-digit number, 
     The first digit being min(min(nums1), min(nums2))
     The second digit being max(min(nums1), min(nums2))

 TC: O(1)
 SC: O(1)
*/

class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int[] map = new int[10];
        int min1 = Integer.MAX_VALUE;
        
        for(int index=0; index<nums1.length; index++){
            int digit = nums1[index];
            
            map[digit]++;
            
            if(digit<min1) min1 = digit;
        }
        
        int min2 = Integer.MAX_VALUE;
        int minDigitOccuringTwice = Integer.MAX_VALUE;
        for(int index=0; index<nums2.length; index++){
            int digit = nums2[index];
            
            if(map[digit]>0) minDigitOccuringTwice = Math.min(minDigitOccuringTwice, digit);
            
            map[digit]++;
            
            if(digit<min2) min2 = digit;
        }
        
        int num1 = minDigitOccuringTwice;
        int num2 = Math.min(min1, min2)*10+Math.max(min1, min2);
        
        return Math.min(num1, num2);
    }
}