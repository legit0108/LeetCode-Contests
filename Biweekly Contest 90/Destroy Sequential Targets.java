// Seeding with nums[i] allows nums[j] = nums[i] + c * space to be destroyed
// or nums[i] = nums[j] - c*space
// or nums[i] % space = (nums[j] - c*space) % space 
// or nums[i] % space = nums[j] % space, since (c*space) % space == 0
// So after seeding with nums[i] we can destroy all such targets that have same remainder as nums[i]
// Maintain frequency map of remainders and return minimum element having maximum remainder frequency count

// TC: O(len)
// SC: O(len)

class Solution {
    public int destroyTargets(int[] nums, int space) {
        HashMap<Integer, Integer> map = new HashMap();
        int len = nums.length;
        int maxCount = 0;
        
        for(int idx =0; idx<len; idx++){
            int rem = nums[idx]%space;
            map.put(rem, map.getOrDefault(rem, 0)+1);
            maxCount = Math.max(maxCount, map.get(rem));
        }
        
        int minElem = Integer.MAX_VALUE;
        for(int idx=0; idx<len; idx++){
            int val = nums[idx];
            
            int rem = val%space;
            int count = map.get(rem);
            
            if(count==maxCount) minElem = Math.min(minElem, val);
        }
        
        return minElem;
    }
}