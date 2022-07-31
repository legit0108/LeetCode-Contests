// TC : O(nodes)
// SC : O(nodes)

// Run dfs/bfs memoizing distance to each node .
// If we encounter a node with distance , we found a cycle , 
// length of cycle = currDist-prevDist

// dfs/bfs work same because we have at max
// one outgoing edge from each node

class Solution {
    public int longestCycle(int[] edges) {
        int nodes = edges.length;
        Integer memo[][] = new Integer[nodes][2];
        int longestCycleLen = -1;
        
        for(int node = 0;node<nodes;node++){
            int currStartNode = node;
            int currNode = node;
            int currDist = 0;
            
            
            while(currNode!=-1){
                if(memo[currNode][0]!=null){
                    int prevDist = memo[currNode][0];
                    int prevStartNode = memo[currNode][1];
                    
                    if(prevStartNode==currStartNode){
                        longestCycleLen = Math.max(longestCycleLen,currDist-prevDist);
                    }
                    
                    break;
                }else{
                    memo[currNode][0] = currDist;
                    memo[currNode][1] = currStartNode;
                }
                
                currDist++;
                currNode = edges[currNode];
            }
        }
        
        return longestCycleLen;
    }
}