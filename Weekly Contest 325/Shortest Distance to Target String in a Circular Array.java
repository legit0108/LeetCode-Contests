// TC: O(len * word.length())
// SC: O(1)

class Solution {
    public int closetTarget(String[] words, String target, int startIndex) {
        int len = words.length;
        int shortestDist = Integer.MAX_VALUE;
        
        for(int idx=0; idx<len; idx++){
            String word = words[idx];
            
            if(word.equals(target)){
                int start = Math.min(idx, startIndex);
                int end = Math.max(idx, startIndex);
                    
                if(start==end) return 0;
                
                shortestDist = Math.min(shortestDist, 
                               Math.min(end-start, len-end + start));
            }
        }
        
        if(shortestDist==Integer.MAX_VALUE) shortestDist = -1;
        return shortestDist;
    }
}