// Method-1 : Two pass
// TC : O(len)
// SC : O(1)

class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int lastIdxMetal = -1;
        int lastIdxPaper = -1;
        int lastIdxGarbage = -1;
        int len = garbage.length;
        int time = 0;
        
        for(int idx=0;idx<len;idx++){
            String str = garbage[idx];
            int strLen = str.length();
            time+=strLen;
            
            for(int strIdx=0;strIdx<strLen;strIdx++){
                char ch = str.charAt(strIdx);
                
                if(ch=='M') lastIdxMetal = idx;
                else if(ch=='P') lastIdxPaper = idx;
                else if(ch=='G') lastIdxGarbage = idx;
            }
        }
        
        for(int idx=1;idx<len;idx++){
            if(idx<=lastIdxMetal) time+=travel[idx-1];
            if(idx<=lastIdxPaper) time+=travel[idx-1];
            if(idx<=lastIdxGarbage) time+=travel[idx-1];
        }
        
        return time;
    }
}

// Method-2 : One pass
// TC : O(len)
// SC : O(1)

class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int lastIdxMetal = -1;
        int lastIdxPaper = -1;
        int lastIdxGarbage = -1;
        int len = garbage.length;
        int time = 0;
        
        for(int idx=0;idx<len;idx++){
            String str = garbage[idx];
            int strLen = str.length();
            time+=strLen;
            
            for(int strIdx=0;strIdx<strLen;strIdx++){
                char ch = str.charAt(strIdx);
                
                if(ch=='M') lastIdxMetal = idx;
                else if(ch=='P') lastIdxPaper = idx;
                else if(ch=='G') lastIdxGarbage = idx;
            }
            
            if(idx>0&&idx<len-1) travel[idx]+=travel[idx-1];
        }
        
        if(lastIdxMetal>0) time+=travel[lastIdxMetal-1];
        if(lastIdxPaper>0) time+=travel[lastIdxPaper-1];
        if(lastIdxGarbage>0) time+=travel[lastIdxGarbage-1];
        
        return time;
    }
}