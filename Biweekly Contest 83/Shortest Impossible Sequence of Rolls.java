// the idea goes like this 
// to form all sequences of length 1
// we should have all numbers from 1 to k present till some index firstIdx 
// to form all sequences of length 2 
// we should have all numbers from 1 to k present after firstIdx till some index secondIdx
// having numbers from 1 to k present after firstIdx 
// ensures all sequences of length 2 are formed
// because all numbers from 1 to k 
// are present after the last number 
// which forms a sequence of length 1
// similarly , to form sequences of length 3
// numbers 1 to k should be present after secondIdx

// TC : O(rolls.length)
// SC : O(k)

class Solution {
    public int shortestSequence(int[] rolls, int k) {
        int shortestSeq = 0;
        HashSet<Integer> set = new HashSet();
        
        for(int num : rolls){
            set.add(num);
            
            if(set.size()==k){
                shortestSeq++;
                set = new HashSet();
            }
        }
        
        return shortestSeq+1;
    }
}