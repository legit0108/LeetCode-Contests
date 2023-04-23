// If we find any number of 1s in the array, we return len-onesCount
// Else we need to convert some element of the array into 1, then convert rest len-1 elements into 1
// For every index , we will try to find shortest such subarray after index such that gcd of that subarray is 1
// We will then convert the element at index to 1 by taking gcd of element at index with gcd of subarray

// TC: O(n^2*log(min(nums)))
// SC: O(1)

class Solution {
    public int minOperations(int[] nums) {
        int len = nums.length;
        int minOps = Integer.MAX_VALUE;
        int onesCount = 0;
        
        for(int index=0; index<len; index++){
            if(nums[index]==1) onesCount++;
        }
        
        if(onesCount>0) return len-onesCount;
        
        for(int index1=0; index1<len; index1++){
            int gcd = nums[index1];
            
            for(int index2=index1+1; index2<len; index2++){
                gcd = getGcd(gcd, nums[index2]);
                if(gcd==1) minOps = Math.min(minOps, index2-index1);
            }
        }
        
        if(minOps==Integer.MAX_VALUE) return -1;
        else return minOps+(len-1); // since we now have a 1 we can convert all other elements to 1
    }
    
    private int getGcd(int num1, int num2){
        while(num2>0){
            int rem = num1%num2;
            num1 = num2;
            num2 = rem;
        }
        
        return num1;
    }
}


// Solution-2: 
// Look at point 3 here: https://codeforces.com/blog/entry/48417
// GCD on subsegments: Assume you have set of numbers in which you add elements one by one and on each step calculate gcd of all numbers from set. Then we will have no more than log(max(ai)) different values of gcd. Thus you can keep compressed info about all gcd on subsegments of ai

// TC: O(len*log(min(nums))*log(max(nums)))
// SC: O(log(max(nums)))

class Solution {
    public int minOperations(int[] nums) {
        int len = nums.length;
        int minOps = Integer.MAX_VALUE;
        int onesCount = 0;
        
        for(int index=0; index<len; index++){
            if(nums[index]==1) onesCount++;
        }
        
        if(onesCount>0) return len-onesCount;
        
        HashMap<Integer, Integer> map = new HashMap(); // stores gcd of all subsegments ending at a particular index
        
        for(int index=0; index<len; index++){
            int num = nums[index];
            HashMap<Integer, Integer> nextMap = new HashMap();
            nextMap.put(num, index);
            
            for(int gcd: map.keySet()){
                int currGcd = getGcd(gcd, num);
                int gcdIndex = map.get(gcd);
                
                if(!nextMap.containsKey(currGcd)) nextMap.put(currGcd, gcdIndex);
                else nextMap.put(currGcd, Math.max(nextMap.get(currGcd), gcdIndex)); // --> (1)
            }
            
            // if our map contains 1 then that means gcd of a subsegment ending at previous index with num is 1 
            // so num can be converted to 1
            // to minimize index-nextMap.get(1) we maximize index in (1)
            if(nextMap.containsKey(1)) minOps = Math.min(minOps, index-nextMap.get(1));
            map = nextMap;
        }
        
        if(minOps==Integer.MAX_VALUE) return -1;
        else return minOps+(len-1);
    }
    
    private int getGcd(int num1, int num2){
        while(num2>0){
            int rem = num1%num2;
            num1 = num2;
            num2 = rem;
        }
        
        return num1;
    }
}