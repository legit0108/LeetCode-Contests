// Keep appending characters to answer as long as character to append is less than or equal to suffix minimum of input string

// TC: O(len)
// SC: O(len)

class Solution {
    public String robotWithString(String s) {
        int len = s.length();
        char suffixMin[] = new char[len];
        suffixMin[len-1] = s.charAt(len-1);
        
        for(int idx=len-2; idx>=0; idx--){
            suffixMin[idx] = suffixMin[idx+1];
            
            char ch = s.charAt(idx);
            if(ch<suffixMin[idx]) suffixMin[idx] = ch;
        }
        
        Stack<Character> stack = new Stack();
        StringBuilder str = new StringBuilder();
        
        for(int idx=0; idx<len; idx++){
            stack.push(s.charAt(idx));
            
            if(idx<len-1){
                while(stack.size()>0 && stack.peek()<=suffixMin[idx+1]){
                    str.append(stack.pop());
                }
            }
        }
        
        while(stack.size()>0) str.append(stack.pop());
        return str.toString();
    }
}