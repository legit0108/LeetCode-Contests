/* 
 Ad-Hoc/Observation based
 
 Key observations: 

 We can change 1 to 0 and 0 to 1 if there is at least one 1 in the string:

 We cannot make a string to be all zeros (one 1 will remain).
 We cannot modify all-zeros string.
 
 Therefore, we can make s into any target
 providing both s and target have one
 or both s and target do not have one
     
 TC: O(len)
 SC: O(1)
*/

class Solution {
    public boolean makeStringsEqual(String s, String target) {
        int len = s.length();
        boolean onePresentInS = false;
        boolean onePresentInTarget = false;
        
        for(int idx=0; idx<len; idx++){
            if(s.charAt(idx)=='1') onePresentInS = true;
            if(target.charAt(idx)=='1') onePresentInTarget = true;
        }
        
        return onePresentInS == onePresentInTarget;
    }
}