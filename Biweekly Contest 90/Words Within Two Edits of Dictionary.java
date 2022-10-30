// Brute force

// TC: O(queriesLen*dictionaryLen*len)
// SC: O(1)

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList();
        int queriesLen = queries.length;
        int dictionaryLen = dictionary.length;
        
        for(int queryIdx =0; queryIdx<queriesLen; queryIdx++){
            for(int wordIdx=0; wordIdx<dictionaryLen; wordIdx++){
                String queryStr = queries[queryIdx];
                String word = dictionary[wordIdx];
                int len = word.length();
                
                int mismatchCount = getMismatchCount(queryStr, word, len);
                if(mismatchCount<=2){
                    ans.add(queryStr);
                    break;
                }
            }
        }
        
        return ans;
    }
    
    private int getMismatchCount(String queryStr, String word, int len){
        int mismatchCount = 0;
        
        for(int idx=0; idx<len; idx++){
            if(queryStr.charAt(idx)!=word.charAt(idx)) mismatchCount++;
        }
        
        return mismatchCount;
    }
}