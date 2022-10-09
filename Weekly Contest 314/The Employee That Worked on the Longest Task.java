// TC: O(len)
// SC: O(1)

class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int len = logs.length;
        int longestTime = logs[0][1];
        int empId = logs[0][0];
        
        for(int idx=1; idx<len; idx++) {
            int currEmpId = logs[idx][0];
            int currTime = logs[idx][1]-logs[idx-1][1];
            
            if(currTime>longestTime){
                longestTime = currTime;
                empId = currEmpId;
            }else if(currTime==longestTime) empId = Math.min(empId, currEmpId);
        }
        
        return empId;
    }
}