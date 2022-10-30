// stack1 -> indices of all elements who have not found first greater element
// stack2 -> indices of all elements who have found first greater element but not found second greater element
// temp -> temporary stack used to maintain monotonicity of stack2, used to transfer elements from stack1 to stack2

// TC: O(len)
// SC: O(len)

class Solution {
    public int[] secondGreaterElement(int[] nums) {
        int len = nums.length;
        int answer[] = new int[len];
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        Stack<Integer> temp = new Stack();
        
        for(int idx=0; idx<len; idx++){
            int val = nums[idx];
            
            while(stack2.size()>0 && nums[stack2.peek()]<val){
                answer[stack2.pop()] = val;
            }
            
            while(stack1.size()>0 && nums[stack1.peek()]<val){
                temp.push(stack1.pop());
            }
            
            while(temp.size()>0){
                stack2.push(temp.pop());
            }
            
            stack1.push(idx);
        }
        
        return answer;
    }
}