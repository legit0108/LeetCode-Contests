/*
  Ideas: 

  To get any value val by applying such operation: 
  (nums[index] + value * x) = val
 
  Apply mod with value on both sides
  nums[index]%value = val%value -> 1

  So, store remainders of nums with value in a map,
  for every index from 0 to len-1, 
  if 1 is not satisfied, then we cannot create that number
  so that number has to be the MEX
 
  We check from 0 to index,
  ensure that all numbers before index can be created
  if index cannot be created then it has to be the MEX

  TC: O(len)
  SC: O(len)
*/


class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        HashMap<Integer, Integer> map = new HashMap();
        
        for(int num: nums){
            int rem = num%value;
            if(rem<0) rem+=value;
            
            map.put(rem, map.getOrDefault(rem, 0)+1);
        }
        
        int mex = 0;
        int len = nums.length;
        
        for(int index =0; index<len; index++){
           int rem = index%value;
            
           if(map.containsKey(rem)){
               int freq = map.get(rem);
               freq--;
               map.put(rem, freq);
               
               if(freq==0) map.remove(rem);
               
               mex++;
           }else break;
        }
        
        return mex;
    }
}