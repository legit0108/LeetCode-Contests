// TC : O(totalQueries*logn)
// SC : O(1) ignoring output space

class Solution {
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int totalQueries = queries.length;
        int answer[] = new int[totalQueries];
        
        for(int idx =0; idx<totalQueries; idx++){
            int query[] = queries[idx];
            int node1 = query[0];
            int node2 = query[1];
            
            int cycleLen = 1;
            
            while(node1!=node2){
                if(node1>node2) node1 = node1/2;
                else node2 = node2/2;
                
                cycleLen++;
            }
            
            answer[idx] = cycleLen;
        }
        
        return answer;
    }
}