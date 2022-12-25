/*

Intuition:

Move window to make rest of the string have required number of characters
Find the maximum window size of the string such that count of all a, b, c chars in rest of the string is atleast k

Why take maximum ?

Because we need to find minimum number of minutes

By taking max window in free area we get overall smaller window 

TC: O(len)
SC: O(1)

*/

class Solution {
    public int takeCharacters(String s, int k) {
        int len = s.length();
        int map[] = new int[3];
        
        for(int idx=0; idx<len; idx++){
            char ch = s.charAt(idx);
            
            map[ch-'a']++;
        }
        
        if(map[0]<k || map[1]<k || map[2]<k) return -1;
        
        int start = 0;
        int end = 0;
        int maxWindowSize = 0;
        
        while(end<len){
            char ch = s.charAt(end);
            
            map[ch-'a']--; // start to end is in free area
            
            while(start<=end && (map[0]<k || map[1]<k || map[2]<k)){
                map[s.charAt(start)-'a']++;
                start++;
            }
            
            maxWindowSize = Math.max(maxWindowSize, end-start+1);
            end++;
        }
        
        return len-maxWindowSize;
    }
}