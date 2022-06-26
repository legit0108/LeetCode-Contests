// TC : O(n)
// SC : O(1)

// At every plot we have 2 choices : 
// -> Place house at current plot , so we cannot place house at previous plot
// -> Do not place house at current plot , so we can place house at previous plot
// At the end we return totalWays^2 
// We have houses on either side of the street
// You can think of it as taking cartesian product of 2 sets 

class Solution {
    public int countHousePlacements(int n) {
        long houseAtLastPlot = 2;
        long houseAtSecondLastPlot = 1;
        long mod = (long)(1e9+7);
        
        for(int currPlot = 2;currPlot<=n;currPlot++){
            long houseAtPlot = (houseAtLastPlot+houseAtSecondLastPlot)%mod;
            houseAtSecondLastPlot = houseAtLastPlot;
            houseAtLastPlot = houseAtPlot;
        }
        
        long totalWays = (houseAtLastPlot*houseAtLastPlot)%mod;
        int ways = (int)(totalWays%mod);
        if(ways<0) ways+=(int)mod;
        return ways;
    }
}