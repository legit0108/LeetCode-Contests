// if all values of suits are same then its a flush
// else check frequency of each ranks[idx]

// TC : O(1)
// SC : O(1)

class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        if(suits[0]==suits[1]&&suits[1]==suits[2]&&suits[2]==suits[3]&&suits[3]==suits[4]){
            return "Flush";
        }
        
        int map[] = new int[14];
        int maxRankFreq = 0;
        
        for(int idx =0;idx<5;idx++){
            map[ranks[idx]]++;
            maxRankFreq = Math.max(maxRankFreq,map[ranks[idx]]);
        }
        
        if(maxRankFreq>=3){
            return "Three of a Kind";
        }else if(maxRankFreq>=2){
            return "Pair";
        }else return "High Card";
    }
}