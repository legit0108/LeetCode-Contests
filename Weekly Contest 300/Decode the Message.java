// TC : O(key.length()+message.length())
// SC : O(1) excluding output

class Solution {
    public String decodeMessage(String key, String message) {
       int len = key.length();
       char map[] = new char[26];
       char letter = 'a';
       StringBuilder sb = new StringBuilder();
        
       for(int idx=0;idx<len;idx++){
           char ch = key.charAt(idx);
           if(ch==' ') continue;
           else if(map[ch-'a']==0){
               map[ch-'a'] = letter;
               letter++;
           }
       } 
       
       len = message.length();
       for(int idx=0;idx<len;idx++){
           char ch = message.charAt(idx);
           if(ch==' ') sb.append(ch);
           else sb.append(map[ch-'a']);
       }
       
       String ans = sb.toString();
       return ans; 
    }
}