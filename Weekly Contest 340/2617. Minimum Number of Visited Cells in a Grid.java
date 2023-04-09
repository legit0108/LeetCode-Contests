// Optimized BFS

/*
 -> Minimum moves required to travel from one node to another in a graph where edge weight is 1 -> BFS
 -> A standard BFS runs in rows*cols*(rows+cols) time here, which will TLE
 -> We use an optimized version of BFS
 -> We do not iterate over already visited nodes while visiting neighbors
 -> Once we visit a neighbor, we remove that neighbor from edge list
 -> The amortized complexity comes out to be O(rows*cols*log(rows*cols))

 TC: O(rows*cols*log(rows*cols))
 SC: O(rows*cols)
 */

 class Solution {
    public int minimumVisitedCells(int[][] grid) {
        HashMap<Integer, TreeSet<Integer>> remRowsMap = new HashMap(); // map of remaining rows for a col
        HashMap<Integer, TreeSet<Integer>> remColsMap = new HashMap(); // map of remaining cols for a row
        int rows = grid.length;
        int cols = grid[0].length;
        
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                if(!remRowsMap.containsKey(col)) remRowsMap.put(col, new TreeSet());
                if(!remColsMap.containsKey(row)) remColsMap.put(row, new TreeSet());
                
                remRowsMap.get(col).add(row);
                remColsMap.get(row).add(col);
            }
        }
        
        Queue<int[]> queue = new ArrayDeque();
        queue.add(new int[]{0, 0});
        remRowsMap.get(0).remove(0);
        remColsMap.get(0).remove(0);
        int minMoves = 0;
        
        while(queue.size()>0){
            int size = queue.size();
            
            while(size>0){
                int[] coords = queue.remove();
                int row = coords[0];
                int col = coords[1];
                int val = grid[row][col];
                
                if(row==rows-1 && col==cols-1) return minMoves+1; // number of cells = minMoves+1
                size--;
                
                TreeSet<Integer> remRows = remRowsMap.get(col); // remaining rows for current col
                TreeSet<Integer> remCols = remColsMap.get(row); // remaining cols for current row
                
                addNeighbours(row, col, val, queue, remRows, remColsMap, 0);
                addNeighbours(row, col, val, queue, remCols, remRowsMap, 1);
            }
            
            minMoves++;
        }
        
        return -1;
    }
    
    private void addNeighbours(int row, int col, int val, Queue<int[]> queue, TreeSet<Integer> set, HashMap<Integer, TreeSet<Integer>> map, int flag){
        if(flag==0){ // need to iterate over all rows for a col
            Integer currRow = set.higher(row);
            Integer lastRow = row+val;
            
            while(currRow!=null && ((int)currRow)<=((int)lastRow)){
                queue.add(new int[]{currRow, col});
                
                set.remove(currRow);
                map.get(currRow).remove(col);
                currRow = set.higher(currRow);
            }
        }else{ // need to iterate over all cols for a row
            Integer currCol = set.higher(col);
            Integer lastCol = col+val;
            
            while(currCol!=null && ((int)currCol)<=((int)lastCol)){
                queue.add(new int[]{row, currCol});
                
                set.remove(currCol);
                map.get(currCol).remove(row);
                currCol = set.higher(currCol);
            }
        }
    }
}