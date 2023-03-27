/*

 Idea: Trim the tree
 
 -> Keep trimming the tree as long as leaf nodes with no coin are found
 -> Finally, we move twice up from the leaf nodes since the grandparent node can collect all coins with a distance of at most two
 -> Minimum edges required = 2 * Number of edges left in the trimmed tree
 
 TC: O(nodes)
 SC: O(nodes)

*/ 

class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int nodes = coins.length;
        int[] degree = new int[nodes];
        ArrayList<Integer>[] tree = new ArrayList[nodes];
        createTree(edges, tree, degree, nodes);
        
        for(int node=0; node<nodes; node++){
            int currNode = node;
            
            while(degree[currNode]==1 && coins[currNode]==0){
                int parent = getParent(tree[currNode], degree);
                
                degree[currNode]--;
                degree[parent]--;
                
                currNode = parent;
            }
        }
        
        Queue<Integer> leafNodes = new ArrayDeque();
        for(int node=0; node<nodes; node++){
            if(degree[node]==1) leafNodes.add(node);
        }
        
        int iterations = 2;
        while(iterations-->0) runBfs(leafNodes, tree, degree);
        
        int minEdges = 0;
        for(int node=0; node<nodes; node++){
            int degreeOfNode = degree[node];
            if(degreeOfNode>0) minEdges+=degreeOfNode;
        }
        
        return minEdges;
    }
    
    private void createTree(int[][] edges, ArrayList<Integer>[] tree, int[] degree, int nodes){
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
            
            degree[node1]++;
            degree[node2]++;
        }
    }
    
    private int getParent(ArrayList<Integer> neighbours, int[] degree){
        int parent = -1;
        
        for(int neighbour: neighbours){
            if(degree[neighbour]>0){
                parent = neighbour;
                break;
            }
        }
        
        return parent;
    }
    
    private void runBfs(Queue<Integer> leafNodes, ArrayList<Integer>[] tree, int[] degree){
        int size = leafNodes.size();
        
        while(size>0){
            int leafNode = leafNodes.remove();
            size--;
            
            int parent = getParent(tree[leafNode], degree);
            
            degree[leafNode]--;
            
            if(parent!=-1){
                degree[parent]--;
                if(degree[parent]==1) leafNodes.add(parent);
            }
        }
    }
}