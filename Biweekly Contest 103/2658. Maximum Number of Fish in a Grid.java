// Variation of number of islands 
// All the solutions run in O(rows*cols) time and space (Union-Find amortized)


// Solution-1: DFS

class Solution {
    public int findMaxFish(int[][] grid){
       int rows = grid.length;
       int cols = grid[0].length;
       int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
       
       int maxFish = 0;
       boolean[][] visited = new boolean[rows][cols];
        
       for(int row=0; row<rows; row++){
           for(int col=0; col<cols; col++){
               if(grid[row][col]>0 && !visited[row][col]){
                   maxFish = Math.max(maxFish, dfs(row, col, rows, cols, grid, visited, dirs));
               }
           }
       } 
        
       return maxFish;
    }
    
    private int dfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited, int[][] dirs){
        int count = grid[row][col];
        visited[row][col] = true;
        
        for(int index=0; index<dirs.length; index++){
            int[] dir = dirs[index];
            int nextRow = row+dir[0];
            int nextCol = col+dir[1];
            
            if(nextRow>=0 && nextCol>=0 && nextRow<rows && nextCol<cols && grid[nextRow][nextCol]>0 && !visited[nextRow][nextCol]){
                count+=dfs(nextRow, nextCol, rows, cols, grid, visited, dirs);
            }
        }
        
        return count;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: BFS

class Solution {
    public int findMaxFish(int[][] grid){
       int rows = grid.length;
       int cols = grid[0].length;
       int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
       
       int maxFish = 0;
       boolean[][] visited = new boolean[rows][cols];
        
       for(int row=0; row<rows; row++){
           for(int col=0; col<cols; col++){
               if(grid[row][col]>0 && !visited[row][col]){
                   maxFish = Math.max(maxFish, bfs(row, col, rows, cols, grid, visited, dirs));
               }
           }
       } 
        
       return maxFish;
    }
    
    private int bfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited, int[][] dirs){
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{row, col});
        int count = 0;
        
        while(queue.size()>0){
            int[] coords = queue.remove();
            row = coords[0];
            col = coords[1];
            
            if(visited[row][col]) continue;
            
            visited[row][col] = true;
            count+=grid[row][col];
            
            for(int index=0; index<dirs.length; index++){
                int[] dir = dirs[index];
                int nextRow = row+dir[0];
                int nextCol = col+dir[1];

                if(nextRow>=0 && nextCol>=0 && nextRow<rows && nextCol<cols && grid[nextRow][nextCol]>0 && !visited[nextRow][nextCol]){
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        
        return count;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Union-Find

class Solution {
    public int findMaxFish(int[][] grid){
       int rows = grid.length;
       int cols = grid[0].length;
       int totalCells = rows*cols; 
       int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
       UnionFind dsu = new UnionFind(totalCells+1);
       
       int[] ponds = new int[totalCells+1];
       for(int row=0; row<rows; row++){
           for(int col=0; col<cols; col++){
               int cell = row*cols+col;
               ponds[cell]+=grid[row][col];
           } 
       }
        
       for(int row=0; row<rows; row++){
           for(int col=0; col<cols; col++){
               if(grid[row][col]>0){
                   for(int index=0; index<dirs.length; index++){
                     int[] dir = dirs[index];
                     int nextRow = row+dir[0];
                     int nextCol = col+dir[1];

                     if(nextRow>=0 && nextCol>=0 && nextRow<rows && nextCol<cols && grid[nextRow][nextCol]>0){
                        int cell1 = row*cols+col;
                        int cell2 = nextRow*cols+nextCol; 
                        
                        dsu.union(cell1, cell2, ponds); 
                     }
                   }
               }
           }
       } 
      
       int maxFishes = 0;
       for(int fishes: ponds) maxFishes = Math.max(maxFishes, fishes);
       return maxFishes; 
    }
    
    private class UnionFind{
        int[] parent;
        int[] rank;
        int nodes;
        
        UnionFind(){}
        
        UnionFind(int nodes){
            this.nodes = nodes;
            
            parent = new int[nodes];
            rank = new int[nodes];
            
            for(int node=0; node<nodes; node++) parent[node] = node;
        }
        
        boolean union(int node1, int node2, int[] ponds){
            int root1 = find(node1);
            int root2 = find(node2);
            
            if(root1==root2) return false;
            
            int rank1 = rank[root1];
            int rank2 = rank[root2];
            
            if(rank1<rank2){
                parent[root1] = root2;
                ponds[root2]+=ponds[root1];
            }else if(rank2<rank1){
                parent[root2] = root1;
                ponds[root1]+=ponds[root2];
            }else{
                parent[root1] = root2;
                ponds[root2]+=ponds[root1];
                rank[root2]++;
            }
            
            return true;
        }
        
        int find(int node){
            int root = node;
            int initNode = node;
            
            while(parent[root]!=root) root = parent[root];
            
            node = initNode;
            
            while(node!=root){
                int par = parent[node];
                parent[node] = root;
                node = par;
            }
            
            return root;
        }
    }
}