/*
Solution-1: Sorting

So the intuition behind this algorithm is to split the process of removing smallest elements into multiple passes. What does that mean?
For better explanation, I'll use an example [1,4,5,2,8,7,6,3], where n=size of array=8
We split the process of removing smallest elements into 4 passes:
remove [1,2,3]
remove [4,5,6]
remove [7]
remove [8]

OK, so this is where the magic happens, to remove [1,2,3] from the array, we need to remove 1, move 4 to the back, move 5 to the back, remove 2, move 8 to the back, move 7 to the back, move 6 to the back, remove 3. The first pass uses n=size of array=8 operations, regardless what elements are in the first pass. This is why we initialize res to n.

Now, the remaining of the array is [4,5,8,7,6] after removing [1,2,3]
We want to remove [4,5,6] from the remaining array, we need to remove 4, remove 5, move 8 to the back, move 7 to the back, remove 6. And this pass takes 5 operations, which is equal to the size of the remaining array. This is why res += n - i. (since n - i is the size of the remaining array)

The remaining array is now [8,7]
And its the same process for the third pass of removing [7], move 8 to the back, remove 7, wihch takes 2 operaions, which is equal to the remaining size of the array

The remaining array is now [8]
Lastly, we just remove 8, which takes 1 operation

Now, how do we split this process into passes in code?
Since the array has been sorted, ex: [1,2,3,4,5,6,7,8], and we know the index of each element, if we encounter a number with smaller index than previous number, we can split out a pass.

Note that each pass should end in such a way that order of elements in the original array is preserved
We do not forcefully keep the order preserved, the order is automatically preserved if we simulate operations as asked in the question 

Example: 

5,4,6 -> 4,6,5 -> 6,5 -> 5,6 (first pass requires 3 operations)
5,6 -> 6 -> [] (second pass requires 2 operations)

Alternate explanation:
-> We init operations with len for removing each element and instead count rotations in the loop
-> When we hit our if condition in the loop, not only do we count rotations required for current element,
   we also count rotations required for the future (look into the future)
Example:
    
   5,4,6 -> sort -> 4, 5, 6
   when we reach 5 in our sorted array we hit inner if condition
   So, we count 2 rotations, one for moving 5 to the very back to process 4 in our original array
   and other rotation for moving elements before 5 to the very back when we will process 5 (we require only 1 such rotation since we only have one element before 5 here i.e. 6)


TC: O(nlogn)
SC: O(n)
where n = arr.length
*/

class Solution {
    public long countOperationsToEmptyArray(int[] nums) {
        int len = nums.length;
        
        Integer[] indices = new Integer[len];
        for(int index=0; index<len; index++) indices[index] = index;
        Arrays.sort(indices, (index1, index2)->Integer.compare(nums[index1], nums[index2]));
        
        long operations = len;
        for(int index=1; index<len; index++){
            if(indices[index]<indices[index-1]) operations+=(long)(len-index);
        }
        
        return operations;
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-2: Fenwick Tree
// TC: O(nlogn), SC: O(n)

class Solution {
    public long countOperationsToEmptyArray(int[] nums) {
        int len = nums.length;
        
        Integer[] indices = new Integer[len];
        for(int index=0; index<len; index++) indices[index] = index;
        Arrays.sort(indices, (index1, index2)->Integer.compare(nums[index1], nums[index2]));
        
        long operations = 0;
        FenwickTree tree = new FenwickTree(len);
        int prevIndex = -1;
        
        for(int index=0; index<len; index++){
            int currIndex = indices[index]; 
            
            if(currIndex>prevIndex) operations+=tree.rangeSum(prevIndex+1, currIndex-1); // we just remove elements in [prevIndex+1, currIndex-1]  to get to our current min element
            else operations+=tree.rangeSum(0, currIndex-1)+tree.rangeSum(prevIndex+1, len-1); // when we were processing previous element, we moved [0, currIndex-1] elements to the end, so in total we have these many elements + elements in [prevIndex+1, len-1] present after prevIndex, we remove all these elements to get to our current min element
                 
            operations++; // delete current min element 
            tree.update(currIndex, -1); // delete current min element from fenwick tree
            
            prevIndex = currIndex;
        }
        
        return operations;
    }
    
    private class FenwickTree{
        int[] farr;
        int len;
        
        FenwickTree(int n){
            this.len = n+1;
            farr = new int[len];
            int[] prefixSum = new int[len];
            
            for(int index=1; index<len; index++){ 
                prefixSum[index] = 1+prefixSum[index-1];
            }
            
            for(int index=1; index<len; index++){
                farr[index] = prefixSum[index] - prefixSum[index-getLsb(index)];
            }
        }
        
        int query(int index){
            index++; // fenwick tree is 1-indexed based
            
            int sum = 0;
            while(index>0){
                sum+=farr[index];
                index-=getLsb(index);
            }
            
            return sum;
        }
        
        void update(int index, int val){
            index++;
            
            while(index<len){
                farr[index]+=val;
                index+=getLsb(index);
            }
        }
        
        int rangeSum(int start, int end){
            return query(end)-query(start-1);
        }
        
        int getLsb(int num){
            return num&(-num);
        }
    }
}


// -----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----


// Solution-3: Idea in Solution-2 implemented using a Segment Tree
// TC: O(nlogn), SC: O(n)

class Solution {
    public long countOperationsToEmptyArray(int[] nums) {
        int len = nums.length;
        
        Integer[] indices = new Integer[len];
        for(int index=0; index<len; index++) indices[index] = index;
        Arrays.sort(indices, (index1, index2)->Integer.compare(nums[index1], nums[index2]));
        
        long operations = 0;
        SegmentTree tree = new SegmentTree(len);
        int prevIndex = -1;
        
        for(int index=0; index<len; index++){
            int currIndex = indices[index]; 
            
            if(currIndex>prevIndex) operations+=tree.rangeSum(prevIndex+1, currIndex-1);
            else operations+=tree.rangeSum(0, currIndex-1)+tree.rangeSum(prevIndex+1, len-1);
                
            operations++;
            tree.update(currIndex, -1);
            
            prevIndex = currIndex;
        }
        
        return operations;
    }
    
    private class SegmentTree{
        int[] tree;
        int len;
        
        SegmentTree(int len){
            tree = new int[4*len];
            this.len = len;
            build(1, 0, len-1);
        }
        
        void build(int node, int start, int end){
            if(start==end){
                tree[node] = 1;
                return;
            }else{
                int mid = start+(end-start)/2;
                
                build(node*2, start, mid);
                build(node*2+1, mid+1, end);
                
                tree[node] = tree[node*2] + tree[node*2+1];
            }
        }
        
        void update(int index, int val){
            update(1, 0, len-1, index, val);
        }
        
        void update(int node, int start, int end, int index, int val){
            if(start==end) tree[node]+=val;
            else{
                int mid = start + (end-start)/2;
                
                if(index<=mid){
                    update(node*2, start, mid, index, val);
                }else update(node*2+1, mid+1, end, index, val);
                
                tree[node] = tree[node*2] + tree[node*2+1];
            }
        }
        
        int query(int node, int start, int end, int left, int right){
            if(end<left || start>right) return 0;
            else if(start>=left && end<=right) return tree[node];
            else{
                int mid = start + (end-start)/2;
                
                return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
            }
        }
        
        int rangeSum(int start, int end){
            return query(1, 0, len-1, start, end);
        }
    }
}