/*

-> Find max sum possible, we get max sum after adding all elements greater than or equal to zero
-> For finding second max sum we can either subtract smallest positive number from sum or add largest negative number to sum
-> Add or subtract numbers from current sum to get next largest sum
-> Hack : We can convert all negative numbers to positive and then simply subtract them instead of adding
-> Apply include/exclude method to generate subsequences

TC : O(len*log(len) + klogk)
SC : O(k)

*/

class Solution {
    public long kSum(int[] nums, int k) {
        int len = nums.length;
        int absNums[] = new int[len];
        long maxSum = 0;
        
        for(int idx=0;idx<len;idx++){
            int num = nums[idx];
            if(num>=0) maxSum+=num;
            absNums[idx] = Math.abs(num);
        }
        
        Arrays.sort(absNums);
        PriorityQueue<Pair> minHeap = new PriorityQueue();
        List<Long> sumsList = new ArrayList();
        
        minHeap.add(new Pair(absNums[0],0));
        sumsList.add(maxSum);
        
        while(minHeap.size()>0&&sumsList.size()<k){
            Pair pair = minHeap.remove();
            long sumSoFar = pair.sumSoFar;
            int idx = pair.idx;
            
            sumsList.add(maxSum-sumSoFar);
            
            if(idx+1<len){
                minHeap.add(new Pair(sumSoFar+absNums[idx+1],idx+1));
                minHeap.add(new Pair(sumSoFar+absNums[idx+1]-absNums[idx],idx+1));
            }
        }
        
        return sumsList.get(k-1);
    }
    
    private class Pair implements Comparable<Pair>{
        private long sumSoFar;
        private int idx;
        
        Pair(long sumSoFar,int idx){
            this.sumSoFar = sumSoFar;
            this.idx = idx;
        }
        
        public int compareTo(Pair other){
            if(this.sumSoFar>=other.sumSoFar) return 1;
            return -1;
        }
    }
}