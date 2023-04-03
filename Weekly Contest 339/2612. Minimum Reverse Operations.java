/*
 Ideas:
  -> Solve without banned array first
  -> We need to find the shortest distance from the initial index containing 1 for all indices
  -> Shortest distance: BFS
  -> Find all neighbours: reversals that include index (in the best case there will be k such subarrays)
  -> From startIndex to endIndex: neighbours are present at spaces of 2
  -> Handle edge cases for startIndex (<0) and endIndex (>=n) 
  -> TC: O(V+E) = O(V + V*e) (every node has e edges) = O(n + n*k)
  -> Tackle banned indices: Mark them as visited in the initial phase, none of them should ever have 1
  -> Reduce the complexity: Iterate over unvisited neighbours
  -> Iterate over sets of non-visited indices
  -> Since we always jump by 2 we can do this using a set for odd indices and a set for even indices
  -> We reduce the complexity to O(nlogn) by iterating over edges that have not been visited previously
  
  TC: O(nlogn)
  SC: O(n)
*/


class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, -1);
        cost[p] = 0;
        
        if(k==1) return cost;
        
        TreeSet<Integer> evenIndices = new TreeSet();
        TreeSet<Integer> oddIndices = new TreeSet();
        
        for(int index=0; index<n; index++){
            if(index!=p){
                if(index%2==0) evenIndices.add(index);
                else oddIndices.add(index);
            }
        }
        
        for(int index: banned){
            if(evenIndices.contains(index)) evenIndices.remove(index);
            else if(oddIndices.contains(index)) oddIndices.remove(index);
        }
        
        Queue<Integer> queue = new ArrayDeque();
        queue.add(p);
        
        while(queue.size()>0){
            int index = queue.remove();
            
            int startIndex = index-k+1;
            int endIndex = index+k-1;
            
            if(startIndex<0) startIndex = k-1-index; // index steps from the front, index steps from the end for a k-sized subarray on reversal (symmetry, the subarray ends at k-1)
            if(endIndex>=n) endIndex = n-k+(n-index); // n-index steps from the end, n-index steps from the front for a k-sized subarray on reversal (symmetry, the subarray starts at n-k)
            
            Integer neighbourIndex = startIndex;
            
            if(neighbourIndex%2==0) addNeighbours(index, neighbourIndex, endIndex, queue, cost, evenIndices);
            else addNeighbours(index, neighbourIndex, endIndex, queue, cost, oddIndices);
        }
        
        return cost;
    }
    
    private void addNeighbours(int index, Integer neighbourIndex, int endIndex, Queue<Integer> queue, int[] cost, TreeSet<Integer> indices){
        neighbourIndex = indices.ceiling(neighbourIndex);
        
        while(neighbourIndex!=null && ((int)neighbourIndex)<=endIndex){
            cost[neighbourIndex] = cost[index]+1;
            queue.add(neighbourIndex);
            indices.remove(neighbourIndex);
            
            neighbourIndex = indices.higher(neighbourIndex);
        }
    }
}