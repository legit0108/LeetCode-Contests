// Direct DFS / BFS implementation
// Do not visit the subtree of restricted nodes
// The main thing to note here is that we do not need
// extra visited set to store nodes
// instead our restricted set can act as visited set

// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int reachableNodes(int nodes, int[][] edges, int[] restricted) {
        ArrayList<Integer> graph[] = new ArrayList[nodes];
        HashSet<Integer> restrictedSet = new HashSet();
        int len = restricted.length;
        
        for(int node =0;node<nodes;node++) graph[node] = new ArrayList();
        for(int node =0;node<len;node++) restrictedSet.add(restricted[node]);
        
        for(int edge[] : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        } 
        
        return dfs(0,graph,restrictedSet);
    }
    
    private int dfs(int node,ArrayList<Integer> graph[],HashSet<Integer> visited){
        visited.add(node);
        int count = 1;
        
        for(int nextNode : graph[node]){
            if(visited.contains(nextNode)==false) count+=dfs(nextNode,graph,visited);
        }
        
        return count;
    }
}