// Sorting + HashSet

// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int start = 0;
        int end = len-1;
        HashSet<Double> set = new HashSet();
        
        while(start<end){
            double average = (nums[start] + nums[end])*1.0/2.0; // average not compulsory, sum will also do
            set.add(average);
            
            start++;
            end--;
        }
        
        return set.size();
    }
}