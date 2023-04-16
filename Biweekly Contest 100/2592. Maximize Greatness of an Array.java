// Solution-1: Sorting + Binary Search
// Find just bigger element than current element to satisfy perm[i] > nums[i] using Binary Search

// TC: O(nlogn)
// SC: O(1)
// where n = nums.length

class Solution {
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int low = 0;
        int high = len-1;
        int index = 0;
        
        while(index<len){
            int greaterIndex = binarySearch(nums, index, low, high);
            
            if(greaterIndex!=-1){
                low = greaterIndex+1;
                index++;
            }else break;
        }
        
        int maximumGreatness = index;
        return maximumGreatness;
    }
    
    private int binarySearch(int[] nums, int index, int low, int high){
        int greaterIndex = -1;
        int val = nums[index];
        
        while(low<=high){
            int mid = low + (high-low)/2;
            
            if(nums[mid]>val){
                greaterIndex = mid;
                high = mid-1;
            }else low = mid+1;
        }
        
        return greaterIndex;
    }
}


// Solution-2: Sorting + Two Pointers
// Find just bigger element than current element to satisfy perm[i] > nums[i] using Two Pointers

// TC: O(nlogn)
// SC: O(1)
// where n = nums.length

class Solution {
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int index1 = 0;
        int index2 = 0;
        int len = nums.length;
        
        while(index2<len){
            if(nums[index2]>nums[index1]){
                index1++;
            }
            
            index2++;
        }
        
        int maximumGreatness = index1;
        return maximumGreatness;
    }
}


// Solution-3: Using Map
// In our obtained permutation, there will be some elements "left out"
// For these elements we will never be able to find a number to satisfy perm[i]>nums[i]
// The count of these elements will be same as maximum frequency of a number in nums

// TC: O(n)
// SC: O(n)
// where n = nums.length

class Solution {
    public int maximizeGreatness(int[] nums) {
       HashMap<Integer, Integer> map = new HashMap();
       int maxFreq = 0;
       
       for(int num: nums){
           map.put(num, map.getOrDefault(num, 0)+1);
           maxFreq = Math.max(maxFreq, map.get(num));
       } 
        
       return nums.length-maxFreq; // we can always satisfy all elements except maxFreq elements 
    }
}