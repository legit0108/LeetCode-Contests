// Idea: 
// By looking at the constraints, we can conclude that we only need to
// check 32 positions for a given index (first^second will fit in range of int)
// Also, (val ^ first == second) implies (first ^ second == val)

// TC: O(32*len + totalQueries)
// SC: O(32*len)

class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        HashMap<Integer, int[]> map = new HashMap();
        int len = s.length();
        
        for(int idx1=0; idx1<len; idx1++){
            int val = 0;
            int start = idx1;
            int end = Math.min(len-1, idx1+32);
            
            for(int idx2=start; idx2<=end; idx2++){
                int bit = s.charAt(idx2)-'0';
                val<<=1;
                val|=bit;
                
                int[] pair = new int[]{idx1, idx2};
                
                if(!map.containsKey(val)){
                    map.put(val, pair);
                }else{
                    int[] prevPair = map.get(val);
                    int prevLen = prevPair[1]-prevPair[0];
                    int currLen = pair[1]-pair[0];
                    
                    if(currLen<prevLen) map.put(val, pair);
                }
            }
        }
        
        int totalQueries = queries.length;
        int[][] ans= new int[totalQueries][2];
        
        for(int idx =0; idx<totalQueries; idx++){
            int[] query = queries[idx];
            
            int val = query[0]^query[1];
            if(map.containsKey(val)) ans[idx] = map.get(val);
            else ans[idx] = new int[]{-1, -1};
        }
        
        return ans;
    }
}