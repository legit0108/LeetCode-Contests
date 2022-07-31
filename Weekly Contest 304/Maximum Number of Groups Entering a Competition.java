// TC : O(1) considering sqrt works in O(1)
// SC : O(1)

// The idea is to sort grades , and then assign groups
// in consecutive numbers , group 1 has 1 student
// group 2 has 2 students , group 3 has 3 students
// an assignment like this is always valid because 
// we would be iterating the array in sorted order 
// so the sum of current n elements will always be greater 
// than sum of previous n-1 elements , also we would be 
// maximizing groups since the size of consecutive groups 
// differs by 1 , however this approach would take nlogn time .

// So in the previous approach we found
// out the following relation for assigning groups : 
// For n students 1 + 2 + 3 + ... k <= n
// or k*(k+1)/2 <= n
// we can use math to solve this relation
// we can also do it using binary search and we can also do it by iterating
// till the condition becomes false , but both of these methods will be less
// optimal than the math solution

// The math solution goes like this 
// k*(k+1)/2 <= n
// k*(k+1) <= 2n
// (k+0.5)*(k+0.5) <= 2n + 0.25 
// k+0.5 <= sqrt(2n + 0.25)
// k <= sqrt(2n + 0.25) - 0.5 
// In the code k corresponds to groups and n corresponds to len

class Solution {
    public int maximumGroups(int[] grades) {
        int len = grades.length;
        int groups = (int)(Math.sqrt(2*len + 0.25) - 0.5);
        return groups;
    }
}