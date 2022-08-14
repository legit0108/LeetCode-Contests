// METHOD - 1 : STACK
// TC : O(len)
// SC : O(len)

class Solution {
    public String smallestNumber(String pattern) {
        Stack<Integer> stack = new Stack();
        StringBuilder ans = new StringBuilder();
        int num = 1;
        int len = pattern.length();
        
        for(int idx =0;idx<len;idx++){
            char ch = pattern.charAt(idx);
            
            if(ch=='D'){
                stack.push(num);
                num++;
            }else{
                ans.append(num);
                while(stack.size()>0) ans.append(stack.pop());
                num++;
            }
        }
        
        ans.append(num);
        while(stack.size()>0) ans.append(stack.pop());
        
        return ans.toString();
    }
}

// METHOD - 2 : SIMULATE STACK USING TWO POINTERS
// TC : O(len)
// SC : O(1) excluding space required for output

class Solution {
    public String smallestNumber(String pattern) {
        StringBuilder ans = new StringBuilder();
        int num = 1;
        int top = 0;
        int bottom = 0;
        int len = pattern.length();
        
        for(int idx =0;idx<len;idx++){
            char ch = pattern.charAt(idx);
            
            if(ch=='D'){
                if(bottom==0) bottom = num;
                top = num;
                num++;
            }else{
                ans.append(num);
                
                while(bottom!=0&&top>=bottom){
                    ans.append(top);
                    top--;
                }
                
                top = 0;
                bottom = 0;
                num++;
            }
        }
        
        ans.append(num);
        while(bottom!=0&&top>=bottom){
            ans.append(top);
            top--;
        }
        
        return ans.toString();
    }
}