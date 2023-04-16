// Solution-1: Binary Search on answer
// TC: O(ranks.length*log(high-low))
// SC: O(1)

class Solution {
    public long repairCars(int[] ranks, int cars) {
        long low = 1;
        long high = getMin(ranks)*(long)cars*(long)cars;
        
        while(low<high){
            long mid = low + (high-low)/2;
            
            if(isPossible(ranks, cars, mid)) high = mid;
            else low = mid+1;
        }
        
        return low;
    }
    
    private long getMin(int[] ranks){
        long min = Long.MAX_VALUE;
        
        for(int rank: ranks) min = Math.min(min, rank);
        
        return min;
    }
    
    boolean isPossible(int[] ranks, int cars, long time){
        long currCars = 0;
        
        for(int rank: ranks){
            currCars+=(long)Math.sqrt(time/(long)rank);
        }
        
        return currCars>=cars;
    }
}


// Solution-2: Heap
// Add all workers to heap and find out next worker that can repair cars

// TC: O(ranks.length*log(k))
// SC: O(k)
// where k = number of distinct ranks 

class Solution {
    public long repairCars(int[] ranks, int cars) {
        HashMap<Long, Long> map = new HashMap();
        for(long rank: ranks) map.put(rank, map.getOrDefault(rank, 0l)+1l);
        
        PriorityQueue<Worker> heap = new PriorityQueue();
        for(long rank: map.keySet()){
            heap.add(new Worker(rank, map.get(rank), 1, rank));
        }
        
        long time = 0;
        
        while(cars>0){
            Worker worker = heap.remove();
            
            time = worker.nextAvailableTime;
            cars-=worker.freq;
            worker.nextCarToRepair++;
            worker.nextAvailableTime = worker.rank*worker.nextCarToRepair*worker.nextCarToRepair;
            
            heap.add(worker);
        }
        
        return time;
    }
    
    private class Worker implements Comparable<Worker>{
        long rank;
        long freq;
        long nextCarToRepair;
        long nextAvailableTime;
        
        Worker(){}
        
        Worker(long rank, long freq, long nextCarToRepair, long nextAvailableTime){
            this.rank = rank;
            this.freq = freq;
            this.nextCarToRepair = nextCarToRepair;
            this.nextAvailableTime = nextAvailableTime;
        }
        
        public int compareTo(Worker other){
            return Long.compare(this.nextAvailableTime, other.nextAvailableTime);
        }
    }
}