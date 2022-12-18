// TC : O(words.length*words[idx].length())
// SC : O(words.length)

class Solution {
    public int similarPairs(String[] words) {
        int len = words.length;
        HashMap<Integer, Integer> map = new HashMap();
        int pairs = 0;
        
        for(int idx=0; idx<len; idx++){
            int mask = getMask(words[idx]);
            
            pairs+=map.getOrDefault(mask, 0);
            
            map.put(mask, map.getOrDefault(mask, 0)+1);
        }
        
        return pairs;
    }
    
    private int getMask(String word){
        int len = word.length();
        int mask = 0;
        
        for(int idx=0; idx<len; idx++){
            mask = mask | (1<<(word.charAt(idx)-'a'));
        }
        
        return mask;
    }
}