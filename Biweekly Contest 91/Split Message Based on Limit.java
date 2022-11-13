/*

-> Each part should have length 'limit'
-> Each part should have <a/b> in suffix
-> Total parts should be minimized
-> <a/b> is unwanted since we want to minimize parts (takes unnecessary space)
-> Reduce space taken by each suffix, it is unwanted part
-> a: 1->b, if you minimize b, a is also minimized
-> All constraints satisfied if b is minimized
-> Trying out every possible b is not viable since max value of b = message.length() = 10^4
-> Instead of minimizing b in terms of number, minimize b in terms of spaces occupied
-> We have 4 choices: 1 length b, 2 length b, 3 length b and 4 length b
-> Since length of each part is limit, we don't have to determine actual value of b, it is fixed for a particular length
-> 1 length b possible? Not possible if length of a becomes 2, 2 length b possible ? Not possible if length of a becomes 3
-> Replicate length of a for last part to every part (a==b for last part)
-> Return empty list if 4 length b also not possible

TC: O(message.length()) ignoring string manipulations
SC: O(message.length())

*/

class Solution {
    public String[] splitMessage(String message, int limit) {
        String ans[] = new String[0];
        
        for(int digits = 1; digits<=4; digits++){
            String[] result = splitMessageIntoDigitsParts(message, limit, digits);
            
            if(result==null) continue;
            else{
                ans = result;
                break; // break as soon as possible since we need to minimize parts
            }
        }
        
        return ans;
    }
    
    private String[] splitMessageIntoDigitsParts(String message, int limit, int digits){
        int parts = 1;
        int idx = 0;
        int len = message.length();
        List<StringBuilder> list = new ArrayList();
        
        while(idx<len){
            int suffixLen = 3 + getLen(parts) + digits; // 3 for </>, getLen(parts) for a, digits for b        
            parts++;
            
            int charsLeft = limit-suffixLen;
            if(charsLeft<=0) return null;
            
            StringBuilder currPart = new StringBuilder();
            while(idx<len && charsLeft>0){
                currPart.append(message.charAt(idx));
                charsLeft--;
                idx++;
            }
            
            list.add(currPart); // push </> suffix later if configuration valid
        }
        
        int size = list.size();
        if(getLen(size)!=digits) return null;
        
        String[] result = new String[size];
        parts = 1;
        idx = 0;
        
        for(StringBuilder str : list){
            StringBuilder suffix = new StringBuilder("<"+parts+"/"+size+">");
            str.append(suffix);
            
            result[idx] = str.toString();
            
            idx++;
            parts++;
        }
        
        return result;
    }
    
    private int getLen(int num){
        int len = 0;
        
        while(num>0){
            len++;
            num/=10;
        }
        
        return len;
    }
}