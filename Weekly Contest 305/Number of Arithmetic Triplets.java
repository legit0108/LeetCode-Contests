// nums[k] - nums[j] == diff
// nums[j] - nums[i] == diff

// nums[j] = nums[k] - diff
// nums[i] = nums[j] - diff = nums[k] - 2*diff

// i<j<k is satisfied because its a strictly increasing array
// so nums[i] appears before nums[j]

// TC : O(nums.length)
// SC : O(nums.length)

class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        HashSet<Integer> set = new HashSet();
        int triplets = 0;
        
        for(int num : nums){
            if(set.contains(num-diff)&&set.contains(num-2*diff)) triplets++;
            set.add(num);
        }
        
        return triplets;
    }
}