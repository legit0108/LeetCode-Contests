/* 
Ideas: 

 -> For the sum of each subarray of length k to be equal, every kth element must be equal
 -> Example: 
     [a1, a2, a3, a4, a5] k =3
     a1+a2+a3 = a2+a3+a4
     or
     a1 = a4
     a4 is 'k' steps ahead of a1
 -> Alternatively, since the array is circular, we can say that every gcd(n, k)th element should be equal
 
    Proof: 
    According to Bezout's identity, ax+by=d has an integral solution for x and y if d%gcd(a,b) = 0
    Let gcd(a,b) = gcd
    Since the array is circular, any index i, i+n, i+2*n, ...., i+A*n refers to the same element (where n = arr.length)
    Let's say index j can be reached from i, i.e. it is obtained after adding some multiple (steps) of k to i
    
    i + A*n + steps*k = j + B*n
    or
    j-i = (A - B)*n + steps*k (which is of the form ax+by=d where a = n, b = k)
    
    Apply Bezout's identity: 
    
    (j-i) % gcd = 0 -> (1)
    Let i = V*gcd + W
    Let j = X*gcd + Y
    
    j-i = (Y-W) + gcd*(X-V)
    
    Using (1), 
    ((Y-W) + gcd*(X-V)) % gcd = 0
    
    The second term cancels out and we are left with the following:
    Y-W = 0
    or
    Y = W
    
    This implies that the remainder of i with gcd (W) = remainder of j with gcd (Y)
    which in turn implies every gcd(n, k)th element should be equal (since they have the same remainder)
    
 TC: O(len), considering median-find to be linear
 SC: O(len)
*/

class Solution {
    public long makeSubKSumEqual(int[] arr, int k) {
        int len = arr.length;
        boolean[] visited = new boolean[len];
        long minOperations = 0;
        
        for(int index=0; index<len; index++){
            if(!visited[index]){
                int currIndex = index;
                List<Integer> list = new ArrayList();
                
                while(!visited[currIndex]){
                    list.add(arr[currIndex]);
                    visited[currIndex] = true;
                    currIndex = (currIndex+k)%len;
                }
                
                int median = findMedian(list);
                
                for(int val: list){
                    minOperations+=Math.abs(val-median);
                }
            }
        }
        
        return minOperations;
    }
    
    private int findMedian(List<Integer> list){
        // Uncomment below line to run findMedian in linear time on average
        // return quickSelect(list, (list.size()+1)/2-1);
        
        Collections.sort(list);
        return list.get(list.size()/2);
    }
    
    private int quickSelect(List<Integer> list, int index){
        int len = list.size();
        int low = 0;
        int high = len-1;
        
        while(low<=high){
            int randomIndex = low + new Random().nextInt(high-low+1); // pivot on random index to avoid worst case O(len^2)
            int partitionIndex = partition(low, high, randomIndex, list, len); 
            
            if(partitionIndex<index) low = partitionIndex+1;
            else if(partitionIndex>index) high = partitionIndex-1;
            else return list.get(partitionIndex);
        }
        
        return -1;
    }
    
    private int partition(int low, int high, int pivotIndex, List<Integer> list, int len){
        // swap to ensure that pivot is the last element encountered
        int pivot = list.get(pivotIndex);
        swap(list, pivotIndex, high);
        
        int partitionIndex = low-1;
        
        for(int index=low; index<=high; index++){
            if(((int)list.get(index))<=pivot){
                partitionIndex++;
                swap(list, partitionIndex, index);
            }
        }
        
        return partitionIndex;
    }
    
    private void swap(List<Integer> list, int index1, int index2){
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}