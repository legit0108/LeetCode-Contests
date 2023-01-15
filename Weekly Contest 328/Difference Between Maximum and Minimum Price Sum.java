/*
 -> All price positive: min obtained at root, max obtained root->leaf
 -> Calculate value assuming one root, then calculate it for other roots 
 -> Bottom-up instead of top-down
 -> Generally, we find the max path sum inwards, here we need to find it outwards
 -> Reduce time of outDfs from O(nodes) to O(1) -> set of inward value for all children
 -> Instead of set calculate first and second max
 -> In-out concept: When you know the value for one root and want to calculate it for other roots
 
 TC: O(nodes)
 SC: O(nodes)
*/


class Solution {
    private ArrayList<Integer>[] tree;
    private int[] price;
    private long[] maxIn;
    private long[] maxOut;
    
    public long maxOutput(int n, int[][] edges, int[] price) {
        tree = new ArrayList[n];
        this.price = price;
        maxIn = new long[n];
        maxOut = new long[n];
        
        for(int node=0; node<n; node++) tree[node] = new ArrayList();
        
        for(int[] edge: edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        inDfs(0, -1);
        outDfs(0, -1);
        
        long maxCost = 0;
        
        for(int root=0; root<n; root++){
            long rootPrice = price[root];
            long cost = Math.max(maxIn[root], maxOut[root]+rootPrice)- // max path sum 
                        rootPrice; // min path sum
            maxCost = Math.max(maxCost, cost);
        }
        
        return maxCost;
    }
    
    private long inDfs(int node, int parent){
        long priceSum = 0;
        
        for(int neighbour: tree[node]){
            if(neighbour != parent) priceSum = Math.max(priceSum, inDfs(neighbour, node));
        }
        
        long nodePrice = price[node];
        priceSum+=nodePrice;
        maxIn[node] = priceSum;
        
        return priceSum;
    }
    
    private void outDfs(int node, int parent){
        long max = 0;
        long secondMax = 0;
        
        for(int neighbour: tree[node]){
            if(neighbour!=parent){
                long currMaxIn = maxIn[neighbour];
                
                if(currMaxIn>=max){
                    secondMax = max;
                    max = currMaxIn;
                }else if(currMaxIn>=secondMax) secondMax = currMaxIn;
            }
        }
        
        for(int neighbour: tree[node]){
            if(neighbour!=parent){
                long currMaxIn = maxIn[neighbour];
                long maxExclCurr = (currMaxIn==max)?secondMax:max;
                
                maxOut[neighbour] = Math.max(maxOut[node], maxExclCurr)+(long)price[node];
                
                outDfs(neighbour, node);
            }
        }
    }
}