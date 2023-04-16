// We first find how many times each node appears in all trips
// We then apply DP with two options for each node -> either halve its price or recur for children
// If we halve a node, we cannot halve its children 
// 2 states for DP: node, canDivide

// TC: O(n + n*trips.length)
// SC: O(n)


// Solution-1: DFS + DP

class Solution {
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        buildTree(tree, edges, n);
        
        int[] freq = new int[n];
        for(int[] trip: trips){
            int node1 = trip[0];
            int node2 = trip[1];
            
            dfs(node1, -1, node2, tree, freq);
        }
        
        int[][] dp = new int[n][2];
        for(int node=0; node<n; node++) dp[node][0] = dp[node][1] = -1;
        
        int minCost = findMinCost(0, 1, -1, tree, freq, price, dp);
        return minCost;
    }
    
    private void buildTree(ArrayList<Integer>[] tree, int[][] edges, int nodes){
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
    }
    
     private int findMinCost(int node, int canDivide, int parent, ArrayList<Integer>[] tree, int[] freq, int[] price, int[][] dp){
        if(dp[node][canDivide]!=-1) return dp[node][canDivide];
        
        int minCost = 0;
        int minCostAfterDividing = Integer.MAX_VALUE;
        int minCostWithoutDividing = Integer.MAX_VALUE;
        
        int priceOfNode = price[node];
        int freqOfNode = freq[node];
        
        if(canDivide==1){
            minCostAfterDividing = priceOfNode/2*freqOfNode;
            
            ArrayList<Integer> children = tree[node];
            for(int child: children){
                if(child!=parent){
                    minCostAfterDividing+=findMinCost(child, 0, node, tree, freq, price, dp);
                }
            }
        }
            
        minCostWithoutDividing = priceOfNode*freqOfNode;
        
        ArrayList<Integer> children = tree[node];
        for(int child: children){
            if(child!=parent){
                minCostWithoutDividing+=findMinCost(child, 1, node, tree, freq, price, dp);
            }
        }
        
        minCost = Math.min(minCostAfterDividing, minCostWithoutDividing);
        return dp[node][canDivide] = minCost;
    }
    
    private boolean dfs(int node, int parent, int end, ArrayList<Integer>[] tree, int[] freq){
        freq[node]++;
        if(node==end) return true;
        
        ArrayList<Integer> children = tree[node];
        for(int child: children){
            if(child!=parent){
                boolean foundEndNode = dfs(child, node, end, tree, freq);
                if(foundEndNode) return true;
            }
        }
        
        freq[node]--; // This node is definitely not in the path from start to end node
        return false;
    }
}


// Solution-2: BFS + DP

class Solution {
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        ArrayList<Integer>[] tree = new ArrayList[n];
        buildTree(tree, edges, n);
        
        int[] freq = new int[n];
        for(int[] trip: trips){
            int node1 = trip[0];
            int node2 = trip[1];
            
            bfs(node1, node2, n, tree, freq);
        }
        
        int[][] dp = new int[n][2];
        for(int node=0; node<n; node++) dp[node][0] = dp[node][1] = -1;
        
        int minCost = findMinCost(0, 1, -1, tree, freq, price, dp);
        return minCost;
    }
    
    private void buildTree(ArrayList<Integer>[] tree, int[][] edges, int nodes){
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
    }
    
     private int findMinCost(int node, int canDivide, int parent, ArrayList<Integer>[] tree, int[] freq, int[] price, int[][] dp){
        if(dp[node][canDivide]!=-1) return dp[node][canDivide];
        
        int minCost = 0;
        int minCostAfterDividing = Integer.MAX_VALUE;
        int minCostWithoutDividing = Integer.MAX_VALUE;
        
        int priceOfNode = price[node];
        int freqOfNode = freq[node];
        
        if(canDivide==1){
            minCostAfterDividing = priceOfNode/2*freqOfNode;
            
            ArrayList<Integer> children = tree[node];
            for(int child: children){
                if(child!=parent){
                    minCostAfterDividing+=findMinCost(child, 0, node, tree, freq, price, dp);
                }
            }
        }
            
        minCostWithoutDividing = priceOfNode*freqOfNode;
        
        ArrayList<Integer> children = tree[node];
        for(int child: children){
            if(child!=parent){
                minCostWithoutDividing+=findMinCost(child, 1, node, tree, freq, price, dp);
            }
        }
        
        minCost = Math.min(minCostAfterDividing, minCostWithoutDividing);
        return dp[node][canDivide] = minCost;
    }
    
    private void bfs(int start, int end, int nodes, ArrayList<Integer>[] tree, int[] freq){
        int[] parent = new int[nodes];
        Arrays.fill(parent, -1);
        
        Queue<Integer> queue = new ArrayDeque();
        queue.add(start);
        
        while(queue.size()>0){
            int node = queue.remove();
            
            if(node==end){
                while(parent[node]!=-1){
                    freq[node]++;
                    node = parent[node];
                }
                
                freq[node]++;
                break;
            }else{
                ArrayList<Integer> children = tree[node];
                
                for(int child: children){
                    if(child!=parent[node]){
                        parent[child] = node;
                        queue.add(child);
                    }
                }
            }
        }
    }
}