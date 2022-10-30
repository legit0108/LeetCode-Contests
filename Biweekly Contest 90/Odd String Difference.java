// TC: O(words.length*word.length())
// SC ~ O(1) because we have only two keys

class Solution {
    public String oddString(String[] words) {
        HashMap<String, Pair> map = new HashMap();
        int len = words.length;
        
        for(int idx=0; idx<len; idx++){
            String word = words[idx];
            String key = getKey(word);
            
            if(map.containsKey(key)) map.get(key).count++;
            else map.put(key, new Pair(word, 1));
        }
        
        for(String key : map.keySet()){
            if(map.get(key).count==1) return map.get(key).originalStr;
        }
        
        return null;
    }
    
    private String getKey(String word){
        int len = word.length();
        StringBuilder key = new StringBuilder();
        
        for(int idx=0; idx<len-1; idx++){
            key.append(word.charAt(idx+1)-word.charAt(idx)).append("_");
        }
        
        return key.toString();
    }
    
    private class Pair{
        private String originalStr;
        private int count;
        
        private Pair(){
            
        }
        
        private Pair(String originalStr, int count){
            this.originalStr = originalStr;
            this.count = count;
        }
    }
}