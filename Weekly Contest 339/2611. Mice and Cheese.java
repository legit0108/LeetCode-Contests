/*
 Ideas:
  
 -> Let reward1 = [a1, a2, a3, a4], reward2 = [b1, b2, b3, b4]
 
 -> Let's assume sum = b1+a2+a3+b4 and k =2
     = b1+b2+b3+b4 + (a2-b2+a3-b3)
    
    -> The first part is constant (sum of reward2), we need to maximize the second part
    -> We chose k largest elements such that the second part of the equation obtained is maximized,
       which will lead to a maximum overall sum
 
 TC: O(len*log(k) + k*log(k)), can be improved to O(k) using quick select
 SC: O(k)
*/

class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int len = reward1.length;
        int sum = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        
        for(int index=0; index<len; index++){
            int val = reward2[index];
            sum+=val;
            
            minHeap.add(reward1[index]-reward2[index]);
            if(minHeap.size()>k) minHeap.remove();
        }
        
        while(minHeap.size()>0) sum+=minHeap.remove();
        
        return sum;
    }
}