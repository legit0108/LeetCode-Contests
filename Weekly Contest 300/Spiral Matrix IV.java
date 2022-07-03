// TC : O(min(m*n,length(list)))
// SC : O(1) excluding output

class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int mat[][] = new int[m][n];
        for(int row =0;row<m;row++) Arrays.fill(mat[row],-1);
        int firstRow = 0;
        int lastRow = m-1;
        int firstCol = 0;
        int lastCol = n-1;
        int count = 0;
        ListNode curr = head;
        
        while(count<m*n&&curr!=null){
            for(int col=firstCol;col<=lastCol&&count<m*n&&curr!=null;col++){
                mat[firstRow][col] = curr.val;
                curr = curr.next;
                count++;
            }
            
            firstRow++;
            
            for(int row=firstRow;row<=lastRow&&count<m*n&&curr!=null;row++){
                mat[row][lastCol] = curr.val;
                curr = curr.next;
                count++;
            }
            
            lastCol--;
            
            for(int col = lastCol;col>=firstCol&&count<m*n&&curr!=null;col--){
                mat[lastRow][col] = curr.val;
                curr = curr.next;
                count++;
            }
            
            lastRow--;
            
            for(int row = lastRow;row>=firstRow&&count<m*n&&curr!=null;row--){
                mat[row][firstCol] = curr.val;
                curr = curr.next;
                count++;
            }
            
            firstCol++;
        }
        
        return mat;
    }
}