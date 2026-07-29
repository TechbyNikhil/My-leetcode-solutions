class Solution {
    long[] fact;  
    static long maxK = 1000001;  
    
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];  
        
    
        for (char c : s.toCharArray()) freq[c - 'a']++;

        String mid = "";  
 
       
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
               mid = String.valueOf((char)(i + 'a'));  
               break;
            }
        }
 
        int[] half = new int[26];  
        int len = 0;  
        
        
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];  
        }

        computeFactorials(len);  

        StringBuilder halfStr = new StringBuilder();  
        
        
        if (!buildKthPalindrome(half, k, halfStr, len)) return "";  
        
        StringBuilder rev = new StringBuilder(halfStr).reverse();
        return halfStr.toString() + mid + rev.toString();
    }

    
    private boolean buildKthPalindrome(int[] freq, long k, StringBuilder sb, int len) {
        if (len == 0) return true;  
        
        
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;  

            freq[i]--;  
            long perms = multinomial(freq);  
            
            if (k <= perms) {
                sb.append((char)(i + 'a'));  
                return buildKthPalindrome(freq, k, sb, len - 1);  
            } else {
                k -= perms;  
                freq[i]++;  
            }
        }
        return false;  
    }

    
    private long multinomial(int[] counts) {
        int tot = 0;
        for (int x : counts) tot += x;  // Total number of characters remaining

        long res = 1;
        // Calculate the multinomial coefficient
        for (int i = 0; i < 26; i++) {
            int cnt = counts[i];
            res = res * binom(tot, cnt);  // Multiply with the binomial coefficient for each character count
        if (res >= maxK) return maxK;  
            tot -= cnt;  
        }
        return res;
    }

    
    private long binom(int n, int k) {
        if (k > n) return 0;  
        if (k > n - k) k = n - k;  
        
        long res = 1;
       
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;  
            if (res >= maxK) return maxK;  
        }
        return res;
    }

    
    private void computeFactorials(int n) {
        fact = new long[n + 1];
        fact[0] = 1;
        // Calculate factorials for numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
    }
}