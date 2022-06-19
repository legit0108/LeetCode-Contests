// Assume the size of the set is n, and the numbers in the set are A1, A2,..., An

// A1 + A2 + ... + An = sum
// All numbers must have k as the unit digit
// So A1 + A2 + ... + An = n*k + 10*(a1 + a2 + .. + an) = sum


// take %10 on both sides
// (n*k)%10 = sum%10;
// where sum>=n*k since all digits have k at unit place

// Which (a1 + a2 + .. + an) can be any number.

// For example:
// sum = 58, k = 9 => we have n*k = 2*9 = 18, and 10*(a1 + a2) = 58 - 18 = 40. So a1 + a2 = 4

// Just find the minimum number satisfying the condition (n*k)%10==sum%10
// Unit place repeats after 10 so iterate only till 10.

// TC : O(1)
// SC : O(1)

class Solution {
    public int minimumNumbers(int num, int k) {
        if(num==0) return 0;
        
        for(int size = 1;size<=10;size++){
            if(((size*k)%10==num%10)&&(num>=size*k)) return size;
        }
        
        return -1;
    }
}