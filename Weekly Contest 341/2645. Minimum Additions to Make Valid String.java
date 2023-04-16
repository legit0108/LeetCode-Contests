// For every 'a', 'b', 'c' we insert it if it is not present where it should be present
// TC: O(len)
// SC: O(1)

class Solution {
    public int addMinimum(String word) {
        int minOps = 0;
        int len = word.length();
        int index = 0;
        
        while(index<len){
            if(charAt(word, index, len)=='a') index++; // first 'a' should be present
            else minOps++; // 'a' is not present so we insert 'a'
            
            // followed by 'b' and then 'c'
            
            if(charAt(word, index, len)=='b') index++;
            else minOps++;
            
            if(charAt(word, index, len)=='c') index++;
            else minOps++;
        }
        
        return minOps;
    }
    
    private char charAt(String word, int index, int len){
        if(index<len) return word.charAt(index);
        else return '!';
    }
}