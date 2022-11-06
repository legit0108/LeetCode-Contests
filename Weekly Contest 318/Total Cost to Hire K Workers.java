// Solution-1: Two min heaps

// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> heap1 = new PriorityQueue<Integer>((a,b)->(costs[a]==costs[b])?Integer.compare(a, b):Integer.compare(costs[a], costs[b]));
        PriorityQueue<Integer> heap2 = new PriorityQueue<Integer>((a,b)->(costs[a]==costs[b])?Integer.compare(a, b):Integer.compare(costs[a], costs[b]));
        
        int len = costs.length;
        int idx = 0;
        for(idx =0; idx<candidates; idx++) heap1.add(idx);
        for(idx = len-1; idx>=len-candidates && idx>=candidates; idx--) heap2.add(idx);
        
        long totalCost = 0;
        int idx1 = candidates;
        int idx2 = idx;
        
        while(k>0){
            if(heap1.size()==0){
                totalCost+=(long)costs[heap2.remove()];
                
                if(idx1<=idx2){
                    heap2.add(idx2);
                    idx2--;
                }
            }else if(heap2.size()==0){
                totalCost+=(long)costs[heap1.remove()];
                
                if(idx1<=idx2){
                    heap1.add(idx1);
                    idx1++;
                }
            }else{
                if(costs[heap1.peek()]<=costs[heap2.peek()]){
                    totalCost+=(long)costs[heap1.remove()];
                    
                    if(idx1<=idx2){
                        heap1.add(idx1);
                        idx1++;
                    }
                }else{
                    totalCost+=(long)costs[heap2.remove()];
                
                    if(idx1<=idx2){
                        heap2.add(idx2);
                        idx2--;
                    }
                }
            }
            
            k--;
        }
        
        return totalCost;
    }
}

// Solution-2: One min heap

// TC: O(lenlog(len))
// SC: O(len)

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((a,b)->(costs[a]==costs[b])?Integer.compare(a, b):Integer.compare(costs[a], costs[b]));
        
        int len = costs.length;
        int idx = 0;
        for(idx =0; idx<candidates; idx++) heap.add(idx);
        for(idx = len-1; idx>=len-candidates && idx>=candidates; idx--) heap.add(idx);
        
        long totalCost = 0;
        int idx1 = candidates;
        int idx2 = idx;
        
        while(k>0){
            idx = heap.remove();
            
            totalCost+=(long)costs[idx];
            
            int dist1 = Math.abs(idx1-idx);
            int dist2 = Math.abs(idx2-idx);
            
            if(dist1<=dist2){
                if(idx1<=idx2){
                    heap.add(idx1);
                    idx1++;
                }
            }else{
                if(idx1<=idx2){
                    heap.add(idx2);
                    idx2--;
                }
            }
            
            k--;
        }
        
        return totalCost;
    }
}