// TC: O(turns)
// SC: O(1)

class Solution {
    public int isWinner(int[] player1, int[] player2) {
        int score1 = 0;
        int score2 = 0;
        int turns = player1.length;
        
        for(int index=0; index<turns; index++){
            score1+=getScore(player1, index);
            score2+=getScore(player2, index);
        }
        
        if(score1>score2) return 1;
        else if(score2>score1) return 2;
        else return 0;
    }
    
    private int getScore(int[] player, int index){
        int score = 0;
        int pins = player[index];
        
        if(strike(player, index-1) || strike(player, index-2)) score+=2*pins;
        else score+=pins;
        
        return score;
    }
    
    private boolean strike(int[] player, int index){
        if(index<0) return false;
        else return player[index]==10;
    }
}