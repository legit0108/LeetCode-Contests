// Simple if-else

// TC: O(1)
// SC: O(1)

class Solution {
    public String categorizeBox(int length, int width, int height, int mass) {
       long volume = (long)length*(long)width*(long)height;
       int max = Math.max(Math.max(Math.max(length, width), height), mass);
        
       boolean bulky = max>=(int)1e4 || volume>=(long)1e9;
       boolean heavy = mass>=100;
       
       if(bulky && heavy) return "Both";
       else if(bulky) return "Bulky";
       else if(heavy) return "Heavy";
       else return "Neither";
    }
}