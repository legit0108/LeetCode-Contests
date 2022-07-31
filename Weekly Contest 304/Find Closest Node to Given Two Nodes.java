// TC : O(nodes)
// SC : O(nodes)

// Find the distance from node1 to all nodes
// then do the same for node2
// then find the node with minimum maximum distance
// bfs / dfs both work in the same way here
// because every node has at max one outgoing edge

class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int nodes = edges.length;
        Integer dist1[] = new Integer[nodes];
        Integer dist2[] = new Integer[nodes];
        int ansNode = -1;
        int minDist = Integer.MAX_VALUE;
        
        computeDistance(edges,node1,dist1);
        computeDistance(edges,node2,dist2);
        
        for(int node=0;node<nodes;node++){
            if(dist1[node]!=null&&dist2[node]!=null){
                int currMaxDist = Math.max(dist1[node],dist2[node]);
				
                if(currMaxDist<minDist){
                    minDist = currMaxDist;
                    ansNode = node;
                }
            }
        }
        
        return ansNode;
    }
    
    private void computeDistance(int edges[],int node,Integer dist[]){
        int currDist = 0;
        
        while(node!=-1&&dist[node]==null){
            dist[node] = currDist;
            currDist++;
            node = edges[node];
        }
    }
}