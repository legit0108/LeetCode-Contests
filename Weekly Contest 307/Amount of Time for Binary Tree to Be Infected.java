/*

-> Same question as burning tree : https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/burning-tree-/ojquestion#!
-> Can be done via BFS too by converting tree to adjacency list representation

TC : O(nodes)
SC : O(nodes)

*/

class Solution {
    int minTime = 0;
    
    public int amountOfTime(TreeNode root, int start) {
        dfs(root,start);
        return minTime;
    }
    
    private int dfs(TreeNode root,int start){
        if(root==null) return -1;
        
        if(root.val==start) {
            dfs(root,null,0);
            return 1;
        }
        
        int left = dfs(root.left,start);
        if(left!=-1){
            dfs(root,root.left,left);
            return left+1;
        }
        
        int right = dfs(root.right,start);
        if(right!=-1){
            dfs(root,root.right,right);
            return right+1;
        }
        
        return -1;
    }
    
    private void dfs(TreeNode node,TreeNode blocker,int time){
        if(node==blocker||node==null) return;
        
        minTime = Math.max(minTime,time);
        dfs(node.left,blocker,time+1);
        dfs(node.right,blocker,time+1);
    }
}