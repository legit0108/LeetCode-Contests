// Track first occurrence of each letter, and check distance when second occurrence found

// TC : O(len)
// SC : O(26) / O(1) 

class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int map[] = new int[26];
        int len = s.length();
        
        for(int idx =0;idx<len;idx++){
            char ch = s.charAt(idx);
            
            if(map[ch-'a']==0) map[ch-'a'] = idx+1;
            else{
                int dis = idx-map[ch-'a'];
                if(dis!=distance[ch-'a']) return false;
            }
        }
        
        return true;
    }
}