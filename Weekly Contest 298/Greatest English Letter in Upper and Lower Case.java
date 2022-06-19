// TC : O(N)
// SC : O(1)
// N is length of s

class Solution {
    public String greatestLetter(String s) {
        int len = s.length();
        boolean upper[] = new boolean[26];
        boolean lower[] = new boolean[26];
        String ans = "";
        
        for(int idx=0;idx<len;idx++){
            char ch = s.charAt(idx);
            
            if(ch>='A'&&ch<='Z'){
                upper[ch-'A'] = true;
            }else{
                lower[ch-'a'] = true;
            }
        }
        
        for(int idx=25;idx>=0;idx--){
            if(upper[idx]&&lower[idx]){
                ans+=(char)('A'+idx);
                break;
            }
        }
        
        return ans;
    }
}