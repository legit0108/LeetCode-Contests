/*
 -> Find an index through which you can make result lexicographically larger, i.e., increment character at that index such that character obtained is in bounds of first k letters of the alphabet
 
 -> We do this step from right to left since we want to find Lexicographically Smallest Beautiful String (the idea is to keep all characters from start till some index same, and this index should be maximum)
 
 -> If we cannot find any such index, the answer is not possible
 
 -> Else, we update character at this index, keep the string same for all indices less than index, and append either 'a', or 'b' or 'c' to all indices after this index
 
 -> This is always possible since k>=4, we only iterate through three characters since we only need to check local neighbours for palindromic substring (index-1, index-2), we check 'a', 'b', 'c', to form lexicographically smallest string, we only check local neighbours since given input string is beautiful
 
 TC: O(s.length()*k)
 SC: O(1) if output space ignored
*/

class Solution {
    public String smallestBeautifulString(String s, int k) {
        int len = s.length();
        StringBuilder str = new StringBuilder();
        char end = (char)('a'+(k-1));
        int lastIndex = -1;
        char larger = '!';
        
   loop : for(int index =len-1; index>=0; index--){
            char ch = (char)(s.charAt(index)+1);
       
            for(char curr=ch; curr<=end; curr++){
                if(index==0 || (curr!=s.charAt(index-1))){
                    if(index-2>=0 && curr==s.charAt(index-2)) continue;
                    else{
                        larger = curr;
                        lastIndex = index;
                        break loop;
                    }
                }
            }
        }
        
        if(larger=='!') return "";
        
        for(int index =0; index<lastIndex; index++) str.append(s.charAt(index));
        str.append(larger);
           
        for(int index=lastIndex+1; index<len; index++){
            if(canAppend(index, 'a', str)) str.append('a');
            else if(canAppend(index, 'b', str)) str.append('b');
            else str.append('c');
        } 
        
        return str.toString(); 
    }
    
    private boolean canAppend(int index, char ch, StringBuilder str){
        if(index-1>=0 && str.charAt(index-1)==ch) return false;
        if(index-2>=0 && str.charAt(index-2)==ch) return false;
        
        return true;
    }
}