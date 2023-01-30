/*
 Note: There needs to be contiguous allocation
 Brute force -> DP -> TLE
 
 Observations:
 -> Only need to track start indices
 -> Value just before the start index and value at the start index contribute to the answer
 -> For all n partitions, we know the cost, we need to select k-1 of them with max/min cost
 -> Add starting and ending index values since they are always taken in every partition
 -> Sort / Heap / Nth element

 TC: O(lenlog(k))
 SC: O(k)
*/

class Solution {
    public long putMarbles(int[] weights, int k) {
        int len = weights.length;
        PriorityQueue<Long> minHeap = new PriorityQueue();
        PriorityQueue<Long> maxHeap = new PriorityQueue(Collections.reverseOrder());
        
        for(int idx=1; idx<len; idx++){
            long currWeight = weights[idx]+weights[idx-1];
                
            minHeap.add(currWeight);
            maxHeap.add(currWeight);
            
            int size = maxHeap.size();
            
            if(size==k){
                maxHeap.remove();
                minHeap.remove();
            }
        }
        
        long maxScore = weights[0] + weights[len-1];
        long minScore = weights[0] + weights[len-1];
        
        while(maxHeap.size()>0){
            maxScore+=minHeap.remove();
            minScore+=maxHeap.remove();
        }
        
        return maxScore-minScore;
    }
}