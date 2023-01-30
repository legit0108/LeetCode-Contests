/*
 Solution-1, Prefix/Suffix:

 We need to solve the problem in n^2logn or n^2
 Fix 2 indices out of 4
 Best option: Fix j and k
 beforeSmaller[i][j] -> How many indices are there <= j that are smaller than arr[i]
 afterGreater[i][j] -> How many indices are there >=j that are greater than arr[i]
 Fix j and k -> result+=beforeSmaller[k][j-1] * afterGreater[j][k+1]
 Use int[][] to avoid MLE
 
 TC: O(n^2)
 SC: O(n^2)
*/

class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] beforeSmaller = getBeforeSmaller(nums, n);
        int[][] afterGreater = getAfterGreater(nums, n);
        long result = 0;
        
        for(int j=1; j<n-2; j++){
            for(int k=j+1; k<n-1; k++){
                if(nums[k]<nums[j]){
                    result+=(long)beforeSmaller[k][j-1] * (long)afterGreater[j][k+1];
                }
            }
        }
        
        return result;
    }
    
    private int[][] getBeforeSmaller(int[] nums, int n){
        int[][] beforeSmaller = new int[n][n];
        
        // beforeSmaller[i][j] -> How many indices are there <= j that are smaller than arr[i]
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(j>0) beforeSmaller[i][j] = beforeSmaller[i][j-1];
                if(nums[i]>nums[j]) beforeSmaller[i][j]++;
            }
        }
        
        return beforeSmaller;
    }
    
    private int[][] getAfterGreater(int[] nums, int n){
        int[][] afterGreater = new int[n][n];
        
        // afterGreater[i][j] -> How many indices are there >=j that are greater than arr[i]
        for(int i = n-1; i>=0; i--){
            for(int j = n-1; j>i; j--){
                if(j<n-1) afterGreater[i][j] = afterGreater[i][j+1];
                if(nums[j]>nums[i]) afterGreater[i][j]++;
            }
        }
        
        return afterGreater;
    }
}


/*
 Solution-2, DP:

 dp[j] stores the count of all valid triplets (i, j, k) that satisfies i < j < k and nums[i] < nums[k] < nums[j] and using the       
 current number as the role j.

 For every new value l, iterate all previously stored dp[j] (132 pattern counts). If nums[l] > nums[j], they can form a valid 1324   
 quadruplet pattern, then add dp[j] into total 1324 counts.

 During iteration, we also update the previous dp[j] by keeping track of smaller numbers in front of each new value. If   
 nums[l] < nums[j], the new value is a potential k for j in the future, so add its count to the dp[j].
 
 TC: O(n^2)
 SC: O(n)
*/

class Solution {
    public long countQuadruplets(int[] nums) {
       int n = nums.length;
       long[] dp = new long[n];
       long result = 0; 
        
       for(int j=0; j<n; j++){
           long count = 0;
           
           for(int i=0; i<j; i++){
               if(nums[j]>nums[i]){
                   result+=dp[i];
                   count++;
               }else if(nums[j]<nums[i]) dp[i]+=count;
           }
       }
        
       return result; 
    }
}