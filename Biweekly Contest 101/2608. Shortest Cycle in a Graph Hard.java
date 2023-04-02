// We can do this by running DFS/BFS from every node and finding the minimum cycle length over all cycles
// We need to run DFS/BFS from every node because one component can contain multiple cycles

// TC: O(V*(V+E))
// SC: O(V+E)
// where V = number of vertices, E = number of edges


// Solution-1: DFS

class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        int shortestCycle = Integer.MAX_VALUE;
        
        for(int node=0; node<n; node++){
            shortestCycle = Math.min(shortestCycle, dfs(node, -1, 0, graph, new Integer[n]));
        }
        
        if(shortestCycle==Integer.MAX_VALUE) shortestCycle = -1;
        return shortestCycle;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private int dfs(int node, int parent, int depth, ArrayList<Integer>[] graph, Integer[] dist){
        dist[node] = depth;
        int shortestCycle = Integer.MAX_VALUE;
        ArrayList<Integer> neighbours = graph[node];
        
        for(int neighbour: neighbours){
            if(dist[neighbour]==null){
                shortestCycle = Math.min(shortestCycle, dfs(neighbour, node, depth+1, graph, dist));
            }else if(neighbour!=parent){
                shortestCycle = Math.min(shortestCycle, Math.abs(dist[neighbour]-dist[node])+1);
            }
        }
        
        return shortestCycle;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS

class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        ArrayList<Integer>[] graph = createGraph(edges, n);
        int shortestCycle = Integer.MAX_VALUE;
        
        for(int node=0; node<n; node++){
            shortestCycle = Math.min(shortestCycle, bfs(graph, node, n));
        }
        
        if(shortestCycle==Integer.MAX_VALUE) shortestCycle = -1;
        return shortestCycle;
    }
    
    private ArrayList<Integer>[] createGraph(int[][] edges, int nodes){
        ArrayList<Integer>[] graph = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        return graph;
    }
    
    private int bfs(ArrayList<Integer>[] graph, int node, int nodes){
        Queue<Integer> queue = new ArrayDeque();
        Integer[] dist = new Integer[nodes];
        Integer[] parent = new Integer[nodes];
        
        parent[node] = -1;
        dist[node] = 0;
        queue.add(node);
        int shortestCycle = Integer.MAX_VALUE;
        
        while(queue.size()>0){
            node = queue.remove();
            ArrayList<Integer> neighbours = graph[node];
            
            for(int neighbour: neighbours){
                if(dist[neighbour]==null){
                    parent[neighbour] = node;
                    dist[neighbour] = dist[node]+1;
                    
                    queue.add(neighbour);
                }else if(parent[neighbour]!=node && parent[node]!=neighbour){
                    shortestCycle = Math.min(shortestCycle, dist[neighbour]+dist[node]+1);
                }
            }
        }
        
        return shortestCycle;
    }
}