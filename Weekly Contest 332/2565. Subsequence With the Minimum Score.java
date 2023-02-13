/*
 Solution-1: Prefix, Suffix + Binary Search

 Ideas: 
  
 -> For any (left, right), it is optimal to remove all t[left: right] so that t becomes a subsequence of s (it does not affect the score, and it is better to keep t as small as possible for it to become a subsequence of s)
 -> Brute force: O(n^3)
 -> Optimizing to O(n^2):
    -> Calculate minimum index 'i' (in 's') that can cover prefix before 'left' (in 't')
    -> Calculate maximum index 'j' (in 's'), which can cover the suffix after 'right' (in 't')
    -> t[0: left-1] + t[right+1: n] is subsequence of s if these do not collide
 -> Optimize to O(nlogn):
    -> Remove loop for right
    -> Find right effectively(binary search)
    -> We want minimum right since we want right-left to be as small as possible to minimize the score
    -> We can apply binary search because arrays obtained are sorted
    
 TC: O(s_len + t_len + t_len * log(t_len))
 SC: O(s_len + t_len)
*/

class Solution {
    public int minimumScore(String s, String t) {
        int s_len= s.length();
        int t_len = t.length();
        int[] pref_idx = get_pref_idx(s, t, s_len, t_len);
        int[] suff_idx = get_suff_idx(s, t, s_len, t_len);
        int min_score = t_len;
        
        for(int idx=0; idx<t_len; idx++){
            int left_idx = (idx==0?-1:pref_idx[idx-1]); 
            
            if(left_idx!=s_len){
                int min_right_idx = binary_search(suff_idx, left_idx, t_len);
            
                if(min_right_idx>=idx) min_score = Math.min(min_score, min_right_idx-idx);
            }
        }
        
        return min_score;
    }
    
    private int[] get_pref_idx(String s, String t, int s_len, int t_len){
        int s_idx = 0;
        int t_idx = 0;
        int[] pref_idx = new int[t_len];
        
        while(s_idx<s_len && t_idx<t_len){
            if(s.charAt(s_idx) == t.charAt(t_idx)){
                pref_idx[t_idx] = s_idx;
                s_idx++;
                t_idx++;
            }else s_idx++;
        }
        
        while(t_idx<t_len){
            pref_idx[t_idx] = s_len;
            t_idx++;
        }
        
        return pref_idx;
    }
    
    private int[] get_suff_idx(String s, String t, int s_len, int t_len){
        int s_idx = s_len-1;
        int t_idx = t_len-1;
        int[] pref_idx = new int[t_len];
        
        while(s_idx>=0 && t_idx>=0){
            if(s.charAt(s_idx) == t.charAt(t_idx)){
                pref_idx[t_idx] = s_idx;
                s_idx--;
                t_idx--;
            }else s_idx--;
        }
        
        while(t_idx>=0){
            pref_idx[t_idx] = -1;
            t_idx--;
        }
        
        return pref_idx;
    }
    
    private int binary_search(int[] arr, int left_idx, int len){
        int low = 0;
        int high = len-1;
        int right_idx = len;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(arr[mid]>left_idx){
                right_idx = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return right_idx;
    }
}


// Solution-2: Using suffix array, two pointers

/*
  -> Refer to ideas of the previous solution
  -> It is optimal to removal all t[left: right]
  -> suffix -> stores first position of t[t_idx] in s, iterating from back, store first position since we want to maximize our chance of forming a subsequence
  -> suffix is monotonically increasing
*/

// TC: O(s_len + t_len)
// SC: O(t_len)

class Solution {
    public int minimumScore(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();
        
        int[] suffix = new int[t_len];
        int t_idx = t_len-1;
        
        for(int s_idx=s_len-1; s_idx>=0 && t_idx>=0; s_idx--){
            if(s.charAt(s_idx) == t.charAt(t_idx)){
                suffix[t_idx] = s_idx;
                t_idx--;
            }
        }
        
        int minScore = t_idx+1;
        
        if(minScore==0) return 0;
        
        while(t_idx>=0){
            suffix[t_idx] = -1;
            t_idx--;
        }
        
        int s_idx = 0;
        t_idx = 0;
        int suffix_idx = 0;
        
        while(s_idx<s_len){
            if(s.charAt(s_idx) == t.charAt(t_idx)){
                while(suffix_idx<t_len && suffix[suffix_idx]<=s_idx) suffix_idx++;
                
                minScore = Math.min(minScore, suffix_idx-t_idx-1);
                
                t_idx++;    
            }
            
            s_idx++;
        }
        
        return minScore;
    }
}