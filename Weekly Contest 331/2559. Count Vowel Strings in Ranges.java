// Prefix sum

// TC: O(words.length + queries.length)
// SC: O(words.length + queries.length)

class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int len = words.length;
        int[] prefix = new int[len];
        
        for(int idx =0; idx<len; idx++){
            if(idx>0) prefix[idx] = prefix[idx-1];
            
            String word = words[idx];
            
            if(isVowel(word.charAt(0)) && isVowel(word.charAt(word.length()-1))) prefix[idx]++;
        }
        
        int totalQueries = queries.length;
        int[] ans = new int[totalQueries];
        
        for(int idx=0; idx<totalQueries; idx++){
            int[] query= queries[idx];
            int start = query[0];
            int end = query[1];
            
            int count = prefix[end];
            if(start>0) count-=prefix[start-1];
            
            ans[idx] = count;
        }
        
        return ans;
    }
    
    private boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
        else return false;
    }
}