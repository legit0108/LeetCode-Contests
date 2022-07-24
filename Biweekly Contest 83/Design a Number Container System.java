// to get the smallest index we maintain sorted list using treeset 
// we also map every index to a number 
// to deal with updates

// TC : 
// constructor O(1)
// change O(logn)
// find O(1)

// SC : O(n)

// where n is numbers encountered so far

class NumberContainers {
    HashMap<Integer,TreeSet<Integer>> numMap;
    HashMap<Integer,Integer> idxMap;
    
    public NumberContainers() {
       numMap = new HashMap();
       idxMap = new HashMap();
    }
    
    public void change(int index, int number) {
       if(idxMap.containsKey(index)){
           int num = idxMap.get(index);
           numMap.get(num).remove(index);
           if(numMap.get(num).size()==0) numMap.remove(num);
       } 
        
       idxMap.put(index,number); 
       if(numMap.containsKey(number)==false) numMap.put(number,new TreeSet());
       numMap.get(number).add(index);
    }
    
    public int find(int number) {
       if(numMap.containsKey(number)) return numMap.get(number).first();
       return -1;
    }
}