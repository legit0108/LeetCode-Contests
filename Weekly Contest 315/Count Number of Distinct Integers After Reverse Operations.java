// Use set to get count of distinct integers

// TC: O(nums.length)
// SC: O(nums.length)

class Solution {
    public int countDistinctIntegers(int[] nums) {
        Set<Integer> set = new HashSet();
        
        for(int num: nums){
            set.add(num);
            set.add(rev(num));
        }
        
        return set.size();
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