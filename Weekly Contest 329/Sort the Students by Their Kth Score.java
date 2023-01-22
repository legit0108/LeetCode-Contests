// Sorting, use comparator to sort

// TC: O(sort)
// SC: O(sort)

class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (student1, student2) -> Integer.compare(student2[k], student1[k]));
        
        return score;
    }
}