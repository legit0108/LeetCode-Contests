/*

-> Generate palindrome with maximum digits first
-> If middle element is present, it should be as high as possible

TC : O(len)
SC : O(1) excluding output space

*/

class Solution {
    public String largestPalindromic(String num) {
        int map[] = new int[10];
        int len = num.length();
        
        for(int idx =0;idx<len;idx++){
            map[num.charAt(idx)-'0']++;
        }
            
        StringBuilder part1 = new StringBuilder();
        int oddChar = -1;
        
        for(int dig = 9;dig>=0;dig--){
            int freq = map[dig]/2;
            
            while(freq>0){
                part1.append(dig);
                freq--;
            }
            
            if(map[dig]%2!=0&&oddChar==-1){
                oddChar = dig;
            }
        }
        
        StringBuilder part2 = new StringBuilder(part1);
        if(oddChar!=-1) part1.append(oddChar);
        StringBuilder ans = part1.append(part2.reverse());
        
        while(ans.length()>1&&ans.charAt(ans.length()-1)=='0') ans.deleteCharAt(ans.length()-1);
        ans.reverse();
        
        while(ans.length()>1&&ans.charAt(ans.length()-1)=='0') ans.deleteCharAt(ans.length()-1);
        ans.reverse();
        
        return ans.toString();
    }
}