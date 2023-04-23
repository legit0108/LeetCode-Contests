// TC: O(1)

class Solution {
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime+delayedTime)%24;
    }
}