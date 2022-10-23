// Solution-1: Ternary Search

// TC: O(lenlog(high-low))
// SC: O(1)

class Solution {
    public long minCost(int[] nums, int[] cost) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        
        for(int num: nums){
            low = Math.min(low, num);
            high = Math.max(high, num);
        }
        
        while(low<high){
            int mid1 = low + (high-low)/3;
            int mid2 = high - (high-low)/3;
            
            long cost1 = getCost(nums, cost, mid1);
            long cost2 = getCost(nums, cost, mid2);
            
            if(cost2>cost1) high = mid2-1;
            else if(cost1>cost2) low = mid1+1;
            else{
                low = mid1+1;
                high = mid2-1;
            }
        }
        
        long minCost = getCost(nums, cost, low);
        return minCost;
    }
    
    private long getCost(int nums[], int cost[], int val){ // getCost is convex in nature
        int len = nums.length;
        long minCost = 0;
        
        for(int idx=0; idx<len; idx++){
            minCost+=(long)Math.abs(nums[idx]-val)*(long)cost[idx];   
        }
        
        return minCost;
    }
}

// Solution-2: Weighted Median

// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public long minCost(int[] nums, int[] cost) {
        int len = nums.length;
        TreeMap<Long, Long> map = new TreeMap();
        long totalLen = 0;
        
        for(int idx=0; idx<len; idx++){
            long num = nums[idx];
            long currCost = cost[idx];
            
            map.put(num, map.getOrDefault(num, 0l)+currCost);
            totalLen+=currCost;
        }
        
        long elemsSoFar = 0;
        long median = -1;
        
        for(long num: map.keySet()){
            long freq = map.get(num);
            
            if(elemsSoFar+freq>=totalLen/2){
                median = num;
                break;
            }
            
            elemsSoFar+=freq;
        }
        
        long minimumCost = 0;
        
        for(long num: map.keySet()){
            minimumCost+=Math.abs(num-median)*map.get(num);
        }
        
        return minimumCost;
    }
}

// Solution-3: dp, prefix-suffix
// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public long minCost(int[] nums, int[] cost) {
        int len = nums.length;
        long pair[][] = new long[len][2];
        
        for(int idx=0; idx<len; idx++){
            pair[idx][0] = nums[idx];
            pair[idx][1] = cost[idx];
        }
        
        Arrays.sort(pair, (pair1, pair2)->Long.compare(pair1[0], pair2[0]));
        
        long prefix[] = new long[len];
        long currCost = 0;
        
        for(int idx=0; idx<len; idx++){
            if(idx>0){ 
                prefix[idx] = prefix[idx-1];
                long diff = pair[idx][0]-pair[idx-1][0];
                prefix[idx]+=diff*currCost;
            }
            
            currCost+=pair[idx][1];
        }
        
        long suffix[] = new long[len];
        currCost = 0;
        
        for(int idx=len-1; idx>=0; idx--){
            if(idx<len-1){
                suffix[idx] = suffix[idx+1];
                long diff = pair[idx+1][0]-pair[idx][0];
                suffix[idx]+=diff*currCost;
            }
            
            currCost+=pair[idx][1];
        }
        
        long minimumCost = Long.MAX_VALUE;
        
        for(int idx=0; idx<len; idx++){
            currCost = prefix[idx] + suffix[idx]; /* cost to make all elements smaller than current element equal to current element + cost to make all elements larger than current element equal to current element (we convert all elements to some element present in array to minimize cost) */
            
            minimumCost = Math.min(minimumCost, currCost);
        }
        
        return minimumCost;
    }
}