// Sort + Prefix Sum + Binary Search

// TC: O(len*log(len) + queries.length*log(len))
// SC: O(len)

class Solution {
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int len = nums.length;
        long[] prefix = new long[len];
        
        for(int index =0; index<len; index++){
            prefix[index] = nums[index];
            if(index>0) prefix[index]+=prefix[index-1];
        }
        
        long sum = prefix[len-1];
        List<Long> list = new ArrayList();
        
        for(long query: queries){
            int index = binarySearch(nums, query);
            long operations = 0;
            
            if(index==-1){
               operations = sum-len*query;
            }else{
               long prefixSum = prefix[index];
               long count = index+1;
               
               operations = count*query-prefixSum; // to convert elements<=query to query
               operations+=sum-prefixSum-(len-count)*query; // to convert elements>query to query
            }
            
            list.add(operations);
        }
        
        return list;
    }
    
    private int binarySearch(int[] nums, long val){ // return last index<=val
        int low = 0;
        int high = nums.length-1;
        int index = -1;
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(nums[mid]<=val){
                index = mid;
                low = mid+1;
            }else high = mid-1;
        }
        
        return index;
    }
}