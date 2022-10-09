// TC: O(len)
// SC: O(len)

class Solution {
    public int[] findArray(int[] pref) {
        int len = pref.length;
        int arr[] = new int[len];
		
        arr[0] = pref[0];
        for(int idx = 1; idx<len; idx++) arr[idx]=pref[idx]^pref[idx-1];
		
        return arr;
    }
}