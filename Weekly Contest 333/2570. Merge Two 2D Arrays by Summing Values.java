// Brute force: Map
// Better: Two pointers

// TC: O(len1 + len2)
// SC: O(1) ignoring output

class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> result = new ArrayList();
        int len1 = nums1.length;
        int len2 = nums2.length;
        int idx1 = 0;
        int idx2 = 0;
        
        while(idx1<len1 && idx2<len2){
            int[] pair1 = nums1[idx1];
            int[] pair2 = nums2[idx2];
            
            int id1 = pair1[0];
            int val1 = pair1[1];
            
            int id2 = pair2[0];
            int val2 = pair2[1];
            
            if(id1<id2){ 
                result.add(new int[]{id1, val1});
                idx1++;
            }
            else if(id2<id1){
                result.add(new int[]{id2, val2});
                idx2++;
            }
            else{
                result.add(new int[]{id1, val1+val2});
                idx1++;
                idx2++;
            }
        }
        
        while(idx1<len1){
            int[] pair1 = nums1[idx1];
            int id1 = pair1[0];
            int val1 = pair1[1];
            
            result.add(new int[]{id1, val1});
            idx1++;
        }
        
        while(idx2<len2){
            int[] pair2 = nums2[idx2];
            int id2 = pair2[0];
            int val2 = pair2[1];
            
            result.add(new int[]{id2, val2});
            idx2++;
        }
        
        return result.toArray(new int[result.size()][2]);
    }
}