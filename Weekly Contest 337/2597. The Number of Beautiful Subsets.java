// Solution-1: Backtracking / brute force

// TC: O(2^len)
// SC: O(len)

class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        return dfs(nums, 0, nums.length, new HashMap<Integer, Integer>(), k)-1;
    }
    
    private int dfs(int[] nums, int index, int len, HashMap<Integer, Integer> map, int k){
        if(index==len) return 1;
        
        int count = 0;
        
        count+=dfs(nums, index+1, len, map, k);
        
        int val = nums[index];
        
        if(!(map.containsKey(val+k) || map.containsKey(val-k))){
            int freq = map.getOrDefault(val, 0);
            freq++;
            map.put(val, freq);

            count+=dfs(nums, index+1, len, map, k);

            freq--;
            map.put(val, freq);
            if(freq==0) map.remove(val);
        }
        
        return count;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----


/*
 Solution-2: Group using the remainder with k
 
 Ideas:

 Elements having the same remainder with k affect each other
 Divide elements into groups having the same remainder with k
 answer = beautiful subsets in group 1  * beautiful subsets in group 2 * ........
 Process elements of a group in sorted order
 
 currTaken = number of subsets if the current number is included
 currNotTaken = number of subsets if the current number is not included

 for each number in the group: 

  if(prev+k==num) currTaken = notTaken*subsets;
  else currTaken = (taken+notTaken)*subsets;
                
  currNotTaken = taken+notTaken;
 
 count = count*(taken+notTaken); after processing all elements in a group

 TC: O(lenlog(len)) (can be optimized to O(len) with count sort or using array as map)
 SC: O(len)
*/

class Solution {
    public int beautifulSubsets(int[] nums, int k) {
        HashMap<Integer, List<Integer>> remMap = new HashMap();
        int len = nums.length;
        
        for(int num: nums){
            int rem = num%k;
            
            if(!remMap.containsKey(rem)) remMap.put(rem, new ArrayList());
            
            List<Integer> list = remMap.get(rem);
            list.add(num);
        }
        
        int count = 1;
        
        for(int rem: remMap.keySet()){
            List<Integer> list = remMap.get(rem);
            TreeMap<Integer, Integer> freqMap = new TreeMap();
            
            for(int num: list) freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
            
            int taken = 0;
            int notTaken = 1;
            int prev = -(int)1e9;
            
            for(int num: freqMap.keySet()){
                int freq = freqMap.get(num);
                int subsets = pow(2, freq)-1;
                int currTaken = 0;
                int currNotTaken = 0;
                
                if(prev+k==num) currTaken = notTaken*subsets;
                else currTaken = (taken+notTaken)*subsets;
                
                currNotTaken = taken+notTaken;
                
                taken = currTaken;
                notTaken = currNotTaken;
                prev = num;
            }
            
            count = count*(taken+notTaken);
        }
        
        return count-1;
    }
    
    private int pow(int x, int n){
        int ans = 1;
        
        while(n>0){
            if(n%2!=0) ans = ans*x;
            x = x*x;
            n = n/2;
        }
        
        return ans;
    }
}