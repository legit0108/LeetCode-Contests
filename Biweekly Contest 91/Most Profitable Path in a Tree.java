// Solution-1: Two DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int nodes = edges.length+1;
        ArrayList<Integer> tree[] = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int edge[] : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        HashMap<Integer, Integer> nodeToRootDist = dfs(tree, bob, -1, 0);
        return dfs(tree, nodeToRootDist, 0, -1, 0, amount);
    }
    
    private int dfs(ArrayList<Integer> tree[], HashMap<Integer, Integer> nodeToRootDist, int node, int parent, int dist, int amount[]){
        int netIncome = 0;
        int currAmount = amount[node];
        
        if(!nodeToRootDist.containsKey(node)) netIncome+=currAmount;
        else{
            int distCoveredByBob = nodeToRootDist.get(node);
            
            if(dist>distCoveredByBob) netIncome+=0;   
            else{
                if(dist==distCoveredByBob) netIncome+=currAmount/2;
                else netIncome+=currAmount;
            }
        }
        
        int maxNetIncome = Integer.MIN_VALUE;
        
        for(int child : tree[node]){
            if(child!=parent){
                maxNetIncome = Math.max(maxNetIncome, netIncome + dfs(tree, nodeToRootDist, child, node, dist+1, amount));
            }
        }
        
        if(maxNetIncome==Integer.MIN_VALUE) maxNetIncome = netIncome;
        return maxNetIncome;
    }
    
    private HashMap<Integer, Integer> dfs(ArrayList<Integer> tree[], int node, int parent, int dist){
        HashMap<Integer, Integer> nodeToRootDist = null;
        
        if(node==0){
            nodeToRootDist = new HashMap();
            nodeToRootDist.put(node, dist);
        }else{
            for(int child : tree[node]){
                if(child!=parent){
                    HashMap<Integer, Integer> map = dfs(tree, child, node, dist+1);
                    
                    if(map!=null){
                        nodeToRootDist = map;
                        nodeToRootDist.put(node, dist);
                        break;
                    }
                }
            }
        }
        
        return nodeToRootDist;
    }
}

// Solution-2: One DFS

// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int nodes = edges.length+1;
        ArrayList<Integer> tree[] = new ArrayList[nodes];
        for(int node=0; node<nodes; node++) tree[node] = new ArrayList();
        
        for(int edge[] : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            
            tree[node1].add(node2);
            tree[node2].add(node1);
        }
        
        Pair pair = dfs(tree, 0, -1, bob, 0, amount);
        return pair.maxNetIncome;
    }
    
    private Pair dfs(ArrayList<Integer> tree[], int node, int parent, int bob, int dist, int amount[]){
        Pair pair = new Pair();
        int maxNetIncomeFromChild = Integer.MIN_VALUE;
        
        if(node==bob) pair.dist = 0;
        
        for(int child : tree[node]){
            if(child!=parent){
                Pair curr = dfs(tree, child, node, bob, dist+1, amount);
                
                pair.dist = Math.min(pair.dist, curr.dist+1);
                maxNetIncomeFromChild = Math.max(maxNetIncomeFromChild, curr.maxNetIncome);
            }
        }
        
        int distCoveredByBob = pair.dist;
        int maxNetIncome = 0;
        int currAmount = amount[node];
        
        if(dist>distCoveredByBob) maxNetIncome+=0;   
        else{
            if(dist==distCoveredByBob) maxNetIncome+=currAmount/2;
            else maxNetIncome+=currAmount;
        }
        
        if(maxNetIncomeFromChild!=Integer.MIN_VALUE) maxNetIncome+=maxNetIncomeFromChild;
        pair.maxNetIncome = maxNetIncome;
        
        return pair;
    }
    
    private class Pair{
        private int dist;
        private int maxNetIncome;
        
        private Pair(){
            this.dist = (int)1e9;
            this.maxNetIncome = Integer.MIN_VALUE;
        }
        
        private Pair(int dist, int maxNetIncome){
            this.dist = dist;
            this.maxNetIncome = maxNetIncome;
        }
    }
}