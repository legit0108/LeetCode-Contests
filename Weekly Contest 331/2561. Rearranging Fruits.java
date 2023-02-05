/* 
Idea:

If the total frequency of an element is odd, return -1
If the total frequency of an element is freq, we put freq/2 elements in basket1, basket2
Extra elements that need to be swapped will be equal to the frequency of that element in the basket - freq/2
When swapping elements, we can directly swap them (in this case, we will swap the minimum in basket1 with the maximum in basket2), or we can swap it using the minimum of all elements in basket1, basket2 as an intermediate

Time and space for solutions discussed below: 

TC: O(len1+len2)*log(len1+len2)
SC: O(len1+len2)
 
where len1 = basket1.length, len2 = basket2.length
*/


// Solution-1: Using 1 Map and 2 Lists

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        long min = Long.MAX_VALUE;
        HashMap<Integer, Pair> map = new HashMap();
        
        for(int val: basket1){
            if(!map.containsKey(val)) map.put(val, new Pair());
            
            Pair pair = map.get(val);
            pair.first++;
                
            min = Math.min(min, val);
        }
        
        for(int val: basket2){
            if(!map.containsKey(val)) map.put(val, new Pair());
            
            Pair pair = map.get(val);
            pair.second++;
                
            min = Math.min(min, val);
        }
        
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        
        for(int key: map.keySet()){
            Pair pair = map.get(key);
            
            int freq1 = pair.first;
            int freq2 = pair.second;
            int freq = freq1+freq2;
            
            if(freq%2!=0) return -1;
            
            int count1 = freq1-freq/2;
            while(count1>0){
                list1.add(key);
                count1--;
            }
            
            int count2 = freq2-freq/2;
            while(count2>0){
                list2.add(key);
                count2--;
            }
        }
        
        long minCost = 0;
        Collections.sort(list1);
        Collections.sort(list2, Collections.reverseOrder());
        int size = list1.size();
        
        for(int idx =0; idx<size; idx++){
            long currMin = Math.min(2*min, Math.min(list1.get(idx), list2.get(idx)));
            minCost+=currMin;
        }
        
        return minCost;
    }
    
    private class Pair{
        private int first;
        private int second;
        
        private Pair(){
            
        }
        
        private Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
}


// Solution-2: Using 1 TreeMap and 1 List

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        TreeMap<Integer, Integer> map = new TreeMap();
        
        for(int val: basket1){
            if(!map.containsKey(val)) map.put(val, 0);
            map.put(val, map.get(val)+1);
        }
        
        for(int val: basket2){
            if(!map.containsKey(val)) map.put(val, 0);
            map.put(val, map.get(val)-1);
        }
        
        long min = map.firstKey();
        
        List<Integer> list = new ArrayList();
        
        for(int key: map.keySet()){
            int freq = Math.abs(map.get(key));
            
            if(freq%2!=0) return -1;
            
            freq/=2;
                
            while(freq>0){
                list.add(key);
                freq--;
            }
        }
        
        long minCost = 0;
        int size = list.size();
        
        for(int idx =0; idx<size/2; idx++){
            long cost = Math.min(2*min, list.get(idx));
            minCost+=cost;
        }
        
        return minCost;
    }
}


// Solution-3: Using 1 TreeMap

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        TreeMap<Long, Long> map = new TreeMap();
        
        for(long val: basket1){
            if(!map.containsKey(val)) map.put(val, 0l);
            map.put(val, map.get(val)+1l);
        }
        
        for(long val: basket2){
            if(!map.containsKey(val)) map.put(val, 0l);
            map.put(val, map.get(val)-1l);
        }
        
        long swaps = 0;
        
        for(long key: map.keySet()){
            long freq = map.get(key);
            
            if(freq%2!=0) return -1;
                
            if(freq>0) swaps+=freq/2; // No need to consider freq<0, they get swapped with freq>0
        }
        
        long min = map.firstKey();
        long minCost = 0;
        
        for(long key: map.keySet()){
            long count = Math.min(swaps, Math.abs(map.get(key))/2); // Count how many possible swaps we can perform with current element
            
            minCost+=count*Math.min(2*min, key);
            
            swaps-=count;
        }
        
        return minCost;
    }
}