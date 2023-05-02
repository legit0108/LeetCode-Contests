// Solution-1: Use a map
// If an element is present in both A and B (its frequency is 2), we increment count

// TC: O(len)
// SC: O(len)

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
       int len = A.length;
       int[] C = new int[len];
       HashMap<Integer, Integer> map = new HashMap();
       int count = 0;
        
       for(int index =0; index<len; index++){
            int freq = put(A[index], map);
            if(freq==2) count++;    

            freq = put(B[index], map);
            if(freq==2) count++;

            C[index] = count;
        }
        
        return C;
    }
    
    private int put(int num, HashMap<Integer, Integer> map){
        map.put(num, map.getOrDefault(num, 0)+1);
        return map.get(num);
    }
}


// Solution-2: Bit manipulation 
// Simulate Solution-1 using a bitmask

// TC: O(len)
// SC: O(1) ignoring output space

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
       int len = A.length;
       int[] C = new int[len];
       Pair pair = new Pair();
        
       for(int index =0; index<len; index++){
            put(A[index], pair);
            put(B[index], pair);
           
            C[index] = pair.count;
        }
        
        return C;
    }
    
    private void put(int num, Pair pair){
        long mask = 1l<<num;
        
        pair.map^=mask;
        mask = pair.map&mask;
        
        if(mask==0) pair.count++;
    }
    
    private class Pair{
        long map;
        int count;
    }
}