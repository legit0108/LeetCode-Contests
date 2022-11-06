// Solution-1: Recursion + memoization

// TC: O(totalRobots*totalFactories*totalRobots)
// SC: O(totalRobots*totalFactories*totalRobots)

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (factory1, factory2)->Integer.compare(factory1[0], factory2[0]));
        
        int totalRobots = robot.size();
        int totalFactories = factory.length;
        Long dp[][][] = new Long[totalRobots][totalFactories][totalRobots+1]; // limit at max totalRobots  
        
        return minimumTotalDistance(0, 0, factory[0][1], robot.size(), factory.length, robot, factory, dp);
    }
    
    private long minimumTotalDistance(int robotIdx, int factoryIdx, int limit, int totalRobots, int totalFactories, List<Integer> robot, int[][] factory, Long dp[][][]){
        if(robotIdx==totalRobots) return 0;
        if(factoryIdx==totalFactories) return (long)(1e18); // no factory left to assign robot
        if(dp[robotIdx][factoryIdx][limit]!=null) return dp[robotIdx][factoryIdx][limit];
        
        if(limit==0) return dp[robotIdx][factoryIdx][limit] = minimumTotalDistance(robotIdx, factoryIdx+1, (factoryIdx+1<totalFactories)?factory[factoryIdx+1][1]:0, totalRobots, totalFactories, robot, factory, dp);
        
        long assignRobot = Math.abs(robot.get(robotIdx)-factory[factoryIdx][0]) + minimumTotalDistance(robotIdx+1, factoryIdx, limit-1, totalRobots, totalFactories, robot, factory, dp);
        long skipFactory = minimumTotalDistance(robotIdx, factoryIdx+1, (factoryIdx+1<totalFactories)?factory[factoryIdx+1][1]:0, totalRobots, totalFactories, robot, factory, dp);
        
        return dp[robotIdx][factoryIdx][limit] = Math.min(assignRobot, skipFactory);
    }
}

// Solution-2: Tabulation

// TC: O(totalRobots*totalFactories*totalRobots)
// SC: O(totalRobots*totalFactories*totalRobots)

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (factory1, factory2)->Integer.compare(factory1[0], factory2[0]));
        
        int totalRobots = robot.size();
        int totalFactories = factory.length;
        long dp[][][] = new long[totalRobots+1][totalFactories+1][totalRobots+1]; 
        
        for(int robotIdx = totalRobots; robotIdx>=0; robotIdx--){
            for(int factoryIdx = totalFactories; factoryIdx>=0; factoryIdx--){
                for(int limit = totalRobots; limit>=0; limit--){
                    if(robotIdx==totalRobots) dp[robotIdx][factoryIdx][limit] = 0;
                    else if(factoryIdx==totalFactories) dp[robotIdx][factoryIdx][limit] = (long)1e18;
                    else{
                        if(limit==0) dp[robotIdx][factoryIdx][limit] = dp[robotIdx][factoryIdx+1][(factoryIdx+1<totalFactories)?factory[factoryIdx+1][1]:0];
                        else{ 
                            long assignRobot = Math.abs(robot.get(robotIdx)-factory[factoryIdx][0]) + dp[robotIdx+1][factoryIdx][limit-1];
                            long skipFactory = dp[robotIdx][factoryIdx+1][(factoryIdx+1<totalFactories)?factory[factoryIdx+1][1]:0];

                            dp[robotIdx][factoryIdx][limit] = Math.min(assignRobot, skipFactory);
                        }
                    }
                }
            }
        }
        
        return dp[0][0][factory[0][1]];
    }
    
}

// Solution-3: Tabulation but space optimized

// TC: O(totalRobots*totalFactories*totalRobots)
// SC: O(totalFactories*totalRobots)

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (factory1, factory2)->Integer.compare(factory1[0], factory2[0]));
        
        int totalRobots = robot.size();
        int totalFactories = factory.length;
        long dp[][] = new long[totalFactories+1][totalRobots+1]; 
        
        for(int robotIdx = totalRobots; robotIdx>=0; robotIdx--){
            long currDp[][] = new long[totalFactories+1][totalRobots+1];
            
            for(int factoryIdx = totalFactories; factoryIdx>=0; factoryIdx--){
                for(int limit = totalRobots; limit>=0; limit--){
                    if(robotIdx==totalRobots) currDp[factoryIdx][limit] = 0;
                    else if(factoryIdx==totalFactories) currDp[factoryIdx][limit] = (long)1e18;
                    else{
                        if(limit==0) currDp[factoryIdx][limit] = currDp[factoryIdx+1][(factoryIdx+1<totalFactories)?factory[factoryIdx+1][1]:0];
                        else{ 
                            long assignRobot = Math.abs(robot.get(robotIdx)-factory[factoryIdx][0]) + dp[factoryIdx][limit-1];
                            long skipFactory = currDp[factoryIdx+1][(factoryIdx+1<totalFactories)?factory[factoryIdx+1][1]:0];

                            currDp[factoryIdx][limit] = Math.min(assignRobot, skipFactory);
                        }
                    }
                }
            }
            
            dp = currDp;
        }
        
        return dp[0][factory[0][1]];
    }
    
}