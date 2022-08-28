/*

Find topo sort of both rowConditions and colConditions
If cannot obtain topo sort of either, return empty matrix
Order elements in each row using rowOrdering 
Column for element of specific row is decided by colOrdering
In this way we meet all conditions - top to down and left to right

TC : O(k^2)
SC : O(k^2)

*/

class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer> rowOrdering = topoSort(rowConditions,k);
        List<Integer> colOrdering = topoSort(colConditions,k);
        
        if(rowOrdering.size()<k||colOrdering.size()<k) return new int[0][0];
        
        HashMap<Integer,Integer> map = new HashMap();
        for(int idx = 0;idx<k;idx++) map.put(colOrdering.get(idx),idx);
        
        int ans[][] = new int[k][k];
        for(int idx=0;idx<k;idx++) ans[idx][map.get(rowOrdering.get(idx))] = rowOrdering.get(idx);
        
        return ans;
    }
    
    private List<Integer> topoSort(int conditions[][],int k){
        int len = conditions.length;
        ArrayList<Integer> graph[] = new ArrayList[k+1];
        
        for(int node = 1;node<=k;node++) graph[node] = new ArrayList();
        
        int indegree[] = new int[k+1];
        
        for(int condition[] : conditions){
            int node1 = condition[0];
            int node2 = condition[1];
            
            graph[node1].add(node2);
            indegree[node2]++;
        }
        
        Queue<Integer> queue = new ArrayDeque();
            
        for(int node = 1;node<=k;node++){
            if(indegree[node]==0) queue.add(node); 
        }
        
        List<Integer> ordering = new ArrayList();
        
        while(queue.size()>0){
            int node = queue.remove();
            ordering.add(node);
            
            for(int childNode : graph[node]){
                indegree[childNode]--;
                if(indegree[childNode]==0) queue.add(childNode);
            }
        }
        
        return ordering;
    }
}