// TC: O(len)
// SC: O(1)

class Solution {
    public int captureForts(int[] forts) {
        int maxForts = 0;
        int start = 0;
        int end = 0;
        int len = forts.length;
        
        while(end<len){
            int fort = forts[end];
            
            if(fort!=0){
                if(fort == -forts[start]) maxForts = Math.max(maxForts, end-start-1);
                start = end;
            }
            
            end++;
        }
        
        return maxForts;
    }
}