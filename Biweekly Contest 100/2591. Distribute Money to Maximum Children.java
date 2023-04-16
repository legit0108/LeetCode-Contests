// Case work problem
// TC: O(1)
// SC: O(1)

class Solution {
    public int distMoney(int money, int children) {
        money-=children; // give 1 dollar to everyone
        
        if(money<0) return -1; // not enough 
        
        int div = money/7;
        int rem = money%7;
        
        if(div==children && rem==0) return children; // can distribute 8 dollars to all children
        else if(div==children-1 && rem==3) return children-2; // need to redistribute money among 2 children such that noone gets 4 dollars
        else return Math.min(children-1, div); // cannot give 8 dollars to more than children-1 (also need to check this with maximum money available)
    }
}