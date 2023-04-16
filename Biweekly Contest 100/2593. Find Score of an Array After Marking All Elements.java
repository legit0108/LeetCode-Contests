// Simulate using set
// TC: O(len*log(len))
// SC: O(len)

class Solution {
    public long findScore(int[] nums) {
        int len = nums.length;
        long score = 0;

        TreeSet<Integer> set;
        set = new TreeSet<Integer>((index1, index2)->((nums[index1]!=nums[index2])?
        Integer.compare(nums[index1], nums[index2]):Integer.compare(index1, index2)));
                
        for(int index = 0; index<len; index++){
            set.add(index);
        }
        
        while(set.size()>0){
            int index = set.first();
            
            score+=(long)nums[index];
            
            set.remove(index);
            if(index-1>=0) set.remove(index-1);
            if(index+1<len) set.remove(index+1);
        }
        
        return score;
    }
}