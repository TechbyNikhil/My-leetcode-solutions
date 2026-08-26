class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;
        String ans = "";
        for (int right = 0; right < n; right++) {
            
            if (s.charAt(right) == '1') {
                ones++;
            }
            
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }
        
            if (ones == k) {
                
                while (s.charAt(left) == '0') {
                    left++;
                }
                String current = s.substring(left, right + 1);
                
                if (ans.equals("")|| current.length() < ans.length() || (current.length() == ans.length() && current.compareTo(ans) < 0)) {
                    ans = current;
                }
            }
        }
        return ans;
    }
}