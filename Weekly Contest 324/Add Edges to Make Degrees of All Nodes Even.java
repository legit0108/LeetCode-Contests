// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        HashSet<Integer> graph[] = new HashSet[n+1];
        for(int node=1; node<=n; node++) graph[node] = new HashSet();
        
        for(List<Integer> edge : edges){
            int node1 = edge.get(0);
            int node2 = edge.get(1);
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        List<Integer> oddDegreeNodes = new ArrayList();
        
        for(int node=1; node<=n; node++){
            if(graph[node].size()%2!=0) oddDegreeNodes.add(node);
        }
        
        int size = oddDegreeNodes.size();
        
        if(size>4 || size%2!=0) return false;
        else if(size==0) return true;
        else if(size==2){
            int node1 = oddDegreeNodes.get(0);
            int node2 = oddDegreeNodes.get(1);
            
            if(!graph[node1].contains(node2)) return true;
            
            for(int node=1; node<=n; node++){
                if(node!=node1 && node!=node2){
                    if(!graph[node].contains(node1) && !graph[node].contains(node2)) return true;
                }
            }
            
            return false;
        }else if(size==4){
            int node1 = oddDegreeNodes.get(0);
            int node2 = oddDegreeNodes.get(1);
            int node3 = oddDegreeNodes.get(2);
            int node4 = oddDegreeNodes.get(3);
            
            if(!graph[node1].contains(node2) && !graph[node3].contains(node4)) return true;
            else if(!graph[node1].contains(node3) && !graph[node2].contains(node4)) return true;
            else if(!graph[node1].contains(node4) && !graph[node2].contains(node3)) return true;
            else return false;
        }else return false;
    }
}