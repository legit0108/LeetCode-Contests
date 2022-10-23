// TC: O(1)
// SC: O(1)

class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        String start1 = event1[0];
        String end1 = event1[1];
        String start2 = event2[0];
        String end2 = event2[1];
        
        return ((start1.compareTo(end2)<=0) && (start2.compareTo(end1)<=0));
    }
}