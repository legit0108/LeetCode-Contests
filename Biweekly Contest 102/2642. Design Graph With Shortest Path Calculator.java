// Solution-1: Dijsktra

class Graph {
    private ArrayList<Pair>[] graph;
    private int nodes;
    
    public Graph(int n, int[][] edges) {
        nodes = n;
        createGraph(edges);
    }
    
    private void createGraph(int[][] edges){
        graph = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) graph[node] = new ArrayList();
        
        for(int[] edge: edges){
            addEdge(edge);
        }
    }
    
    public void addEdge(int[] edge) { // O(1)
        int node1 = edge[0];
        int node2 = edge[1];
        int cost = edge[2];
        
        graph[node1].add(new Pair(node2, cost));
    }
    
    public int shortestPath(int node1, int node2) { // O(ElogV) = O(V^2logV)
        PriorityQueue<Pair> minHeap = new PriorityQueue(); 
        minHeap.add(new Pair(node1, 0));
        boolean[] visited = new boolean[nodes];
        
        while(minHeap.size()>0){
            Pair pair = minHeap.remove();
            int node = pair.node;
            int cost = pair.cost;
            
            if(node==node2) return cost;
            
            if(visited[node]) continue;
            visited[node] = true;
            
            ArrayList<Pair> neighborsList = graph[node];
            for(int index=0; index<neighborsList.size(); index++){
                pair = neighborsList.get(index);
                
                int neighbor = pair.node;
                int weight = pair.cost;
                
                if(!visited[neighbor]) minHeap.add(new Pair(neighbor, cost+weight));
            }
        }
        
        return -1;
    }
    
    private class Pair implements Comparable<Pair>{
        int node;
        int cost;
        
        Pair(){}
        
        Pair(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        
        public int compareTo(Pair other){
            return Integer.compare(this.cost, other.cost);
        }
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */


// Solution-2: Floyd Warshall

class Graph {
    private int[][] dist;
    private int nodes;
    
    public Graph(int n, int[][] edges) {
        nodes = n;
        floydWarshall(edges);
    }
    
    public void addEdge(int[] edge) { // O(V^2)
        int node1 = edge[0];
        int node2 = edge[1];
        int cost = edge[2];
        
        for(int src=0; src<nodes; src++){
            for(int dest=0; dest<nodes; dest++){
                dist[src][dest] = Math.min(dist[src][dest], dist[src][node1]+cost+dist[node2][dest]);
            }
        }
    }
    
    public int shortestPath(int node1, int node2) { // O(1)
        int distance = dist[node1][node2];
        
        if(distance==(int)1e9) return -1;
        else return distance;
    }
    
    private void floydWarshall(int[][] edges){ // O(V^3)
        dist = new int[nodes][nodes];
        
        for(int row=0; row<nodes; row++){
            Arrays.fill(dist[row], (int)1e9);
        }
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            int cost = edge[2];
        
            dist[node1][node2] = cost;
        }
        
        for(int node=0; node<nodes; node++) dist[node][node] = 0;
        
        for(int node2=0; node2<nodes; node2++){
            for(int node1=0; node1<nodes; node1++){
                for(int node3=0; node3<nodes; node3++){
                    dist[node1][node3] = Math.min(dist[node1][node3], dist[node1][node2]+dist[node2][node3]); 
                }
            }
        }
    }
} 