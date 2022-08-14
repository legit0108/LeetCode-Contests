// TC : O(nodes)
// SC : O(nodes)

class Solution {
    public int edgeScore(int[] edges) {
        int nodes = edges.length;
        long score[] = new long[nodes];
        long maxScore = -1;
        int highestScoreNode = -1;
        
        for(int node =0;node<nodes;node++){
            score[edges[node]]+=(long)node;
            
            if(score[edges[node]]>maxScore){
                maxScore = score[edges[node]];
                highestScoreNode = edges[node];
            }else if(score[edges[node]]==maxScore){
                highestScoreNode = Math.min(highestScoreNode,edges[node]);
            }
        }
        
        return highestScoreNode;
    }
}