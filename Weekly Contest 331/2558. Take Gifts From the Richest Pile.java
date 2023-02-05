// Max-heap

// TC: O(gifts.length + klog(gifts.length))
// SC: O(gifts.length)

class Solution {
    public long pickGifts(int[] gifts, int k) {
       PriorityQueue<Long> maxHeap = new PriorityQueue(Collections.reverseOrder());
       long total = 0;
       
       for(long gift: gifts){
           total+=gift;
           maxHeap.add(gift);
       }
        
       long consumed = 0;
        
       while(k>0){
           long maxPile = maxHeap.remove();
           long remaining = (long)Math.sqrt(maxPile);
           
           consumed+=maxPile-remaining;
           
           maxHeap.add(remaining);
           k--;
       } 
        
       return total-consumed;
    }
}