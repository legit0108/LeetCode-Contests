// Solution-1: Using queue

// TC: O(1)
// SC: O(k)

class DataStream {
    private Queue<Integer> queue;
    private int k;
    private int value;
    private int count;
    
    public DataStream(int value, int k) {
        queue = new ArrayDeque();
        this.k = k;
        this.value = value;
    }
    
    public boolean consec(int num) {
        queue.add(num);
        if(num == value) count++;
        
        if(queue.size()<k) return false;
        else{
            if(queue.size()>k){
                int front = queue.remove();
                if(front == value) count--;
            }
            
            return count == k;
        }
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */


// Solution-2: Constant space

// TC: O(1)
// SC: O(1)

class DataStream {
    private int k;
    private int value;
    private int count;
    
    public DataStream(int value, int k) {
        this.k = k;
        this.value = value;
    }
    
    public boolean consec(int num) {
        if(num == value) count++;
        else count = 0;
        
        return count >= k;
    }
}