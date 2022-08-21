/*

-> Train only just enough to beat current opponent (do not overtrain!!)

TC : O(opponents)
SC : O(1)

*/

class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int opponents = energy.length;
        int minHours = 0;
        int currEnergy = initialEnergy;
        int currExp = initialExperience;
        
        for(int opponent=0;opponent<opponents;opponent++){
            if(currEnergy<=energy[opponent]){
                int training = energy[opponent]-currEnergy+1;
                minHours+=training;
                currEnergy+=training;
            }
            
            if(currExp<=experience[opponent]){
                int training = experience[opponent]-currExp+1;
                minHours+=training;
                currExp+=training;
            }
            
            currEnergy-=energy[opponent];
            currExp+=experience[opponent];
        }
        
        return minHours;
    }
}