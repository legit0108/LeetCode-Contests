/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Solution-1: 1 BFS
// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        if(root==null) return root;
        
        Queue<TreeNode> queue = new ArrayDeque();
        queue.add(root);
        int levelSum = root.val;
        
        while(queue.size()>0){
            int size = queue.size();
            int nextLevelSum = 0;
            
            while(size>0){
                TreeNode node = queue.remove();
                size--;
                
                node.val = levelSum-node.val;    
                int childSum = 0;
                
                TreeNode left = node.left;
                if(left!=null){
                    int val = left.val;
                    nextLevelSum+=val;
                    childSum+=val;
                }
                
                TreeNode right = node.right;
                if(right!=null){
                    int val = right.val;
                    nextLevelSum+=val;
                    childSum+=val;
                }
                
                if(left!=null){
                    left.val = childSum;
                    queue.add(left);
                }
                
                if(right!=null){
                    right.val = childSum;
                    queue.add(right);
                }
            }
            
            levelSum = nextLevelSum;
        }
        
        return root;
    }
}


// Solution-2: 2 DFS
// TC: O(nodes)
// SC: O(nodes)

class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        if(root==null) return null;
        
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0, root.val);
        
        dfs1(root, 0, map);
        dfs2(root, 0, map);
        
        return root;
    }
    
    private void dfs1(TreeNode node, int level, HashMap<Integer, Integer> map){
        int val = node.val;
        int childSum = 0;
        
        TreeNode left = node.left;
        if(left!=null) childSum+=left.val;
        
        TreeNode right = node.right;
        if(right!=null) childSum+=right.val;
        
        if(left!=null){
            map.put(level+1, map.getOrDefault(level+1, 0)+left.val);
            left.val = childSum;
            
            dfs1(left, level+1, map);
        }
        
        if(right!=null){
            map.put(level+1, map.getOrDefault(level+1, 0)+right.val);
            right.val = childSum;
            
            dfs1(right, level+1, map);
        }
    }
    
    private void dfs2(TreeNode node, int level, HashMap<Integer, Integer> map){
        if(node==null) return;
        
        node.val = map.get(level)-node.val;
        
        dfs2(node.left, level+1, map);
        dfs2(node.right, level+1, map);
    }
}