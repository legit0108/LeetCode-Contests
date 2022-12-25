// TC: O(strs.length*(str.length() + log(mod))
// SC: O(MAX_SIZE)

class Solution {
    static long[] fact;
    static long mod = (long)1e9+7;
    static int MAX_SIZE = (int)1e5+1;
    
    static{
       fact = new long[MAX_SIZE];
       
       fact[0] = 1;
       
       for(int num = 1; num<MAX_SIZE; num++){
           fact[num] = (((fact[num-1]%mod) * (num%mod))+mod)%mod;
       }
    }
    
    public int countAnagrams(String s) {
        String[] strs = s.split(" ");
        long ways = 1;
        
        for(String str : strs){
            HashMap<Character, Integer> map = new HashMap();
            int len = str.length();
            
            for(int idx =0; idx<len; idx++){
                char ch = str.charAt(idx);
                map.put(ch, map.getOrDefault(ch, 0)+1);
            }
            
            long num = fact[len];
            long denom = 1;
            
            for(char ch : map.keySet()){
                int freq = map.get(ch);
                denom = (((denom%mod) * (fact[freq]%mod))+mod)%mod; 
            }
            
            ways = (((ways%mod) * ((((num%mod) * (pow(denom, mod-2)%mod))+mod)%mod))+mod)%mod;
        }
        
        int count = (int)(ways%mod);
        if(count<0) count+=(int)mod;
        return count;
    }
    
    private long pow(long x, long n){
        long ans = 1;
        
        while(n>0){
            if(n%2!=0) ans = (((ans%mod) * (x%mod))+mod)%mod;
            n = n/2;
            x = (((x%mod) * (x%mod)) + mod)%mod;
        }
        
        return ans;
    }
}