/*

Max possible power = sum(stations) + k
Solve: given k power stations, can every city be given at least minPower?
Where to install a power station? Farthest position
Install power station at farthest position -> more number of cities benefitted
Putting power station at farthest position -> Ensured that power station utilized to fullest
Smartly place stations -> prefix sum on the go
Binary search on minPower

TC: O(lenlog(high-low))
SC: O(len)

*/


class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int len = stations.length;
        long[] power = new long[len];
        
        for(int idx=0; idx<len; idx++){
            int left = Math.max(idx-r, 0);
            int right = Math.min(idx+r, len-1);
            long count = stations[idx];
            
            power[left]+=count;
            if(right+1<len) power[right+1]-=count;
        }
        
        long low = Long.MAX_VALUE;
        long high = k;
        long ans = -1;
        
        for(int idx=0; idx<len; idx++){
            long count = stations[idx];
            high+=count;
            low = Math.min(low, count);
            
            if(idx>0) power[idx]+=power[idx-1];
        }
        
        while(low<=high){
            long mid = low + (high-low)/2;
            
            if(minPowerPossible(power, mid, r, k, len)){
                ans = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return ans;
    }
    
    private boolean minPowerPossible(long[] power, long minPower, int r, int k, int len){
        long[] extraPower = new long[len];
        
        for(int idx=0; idx<len; idx++){
            if(idx>0) extraPower[idx]+=extraPower[idx-1];
            long totalPower = power[idx]+extraPower[idx]; 
            
            if(totalPower<minPower){
                long reqdPower = minPower-totalPower;
                
                if(k<reqdPower) return false;
                else{
                    k-=reqdPower;
                    
                    extraPower[idx]+=reqdPower;
                    int end = idx+2*r+1;
                    if(end<len) extraPower[end]-=reqdPower;
                }
            }
        }
        
        return true;
    }
}