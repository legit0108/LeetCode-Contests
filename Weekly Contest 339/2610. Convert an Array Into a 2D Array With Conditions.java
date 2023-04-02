// The row we need to add the current element to is equal to the frequency of that element

// TC: O(len)
// SC: O(len)

class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> matrix = new ArrayList();
        int len = nums.length;
        int[] map = new int[len+1];
        
        for(int num: nums){
            map[num]++;
            int freq = map[num];
            
            if(matrix.size()<freq) matrix.add(new ArrayList());
            List<Integer> row = matrix.get(freq-1);
            row.add(num);
        }
        
        return matrix;
    }
}