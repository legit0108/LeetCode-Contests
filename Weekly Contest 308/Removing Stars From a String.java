// TC : O(len)
// SC : O(1) excluding output space

class Solution {
    public String removeStars(String s) {
        StringBuilder str = new StringBuilder();
        int starCount = 0;
        int len = s.length();
        
        for(int idx=len-1;idx>=0;idx--){
            char ch = s.charAt(idx);
            
            if(ch=='*'){
                starCount++;
            }else{
                if(starCount>0){
                    starCount--;
                }else str.append(ch);
            }
        }
        
        return str.reverse().toString();
    }
}