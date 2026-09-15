class Solution {
    boolean[][] isPalindrome;

    
    public int solve(String s, int k, int i, int j, int[][] t) {
        int n = s.length();
        if (i >= n || j >= n)
            return 0;

        if (t[i][j] != -1)
            return t[i][j];

        if (isPalindrome[i][j]) { 
            int growWindow  = solve(s, k, i, j + 1, t);
            int takeIt      = 1 + solve(s, k, j + 1, j + k, t);
            int slideWindow = solve(s, k, i + 1, j + 1, t);

            return t[i][j] = Math.max(growWindow, Math.max(takeIt, slideWindow));
        }

        int slideWindow = solve(s, k, i + 1, j + 1, t);
        int growWindow  = solve(s, k, i, j + 1, t);

        return t[i][j] = Math.max(slideWindow, growWindow);
    }

    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (k == 1)
            return n; 

        isPalindrome = new boolean[n + 1][n + 1];
        
        for (int L = 1; L <= n; L++) {
            for (int i = 0; i + L <= n; i++) {
                int j = i + L - 1;

                if (i == j) {
                    isPalindrome[i][i] = true; 
                } else if (i + 1 == j) {
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)); 
                } else {
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
                }
            }
        }

        int[][] t = new int[n][n];
        for (int[] row : t) Arrays.fill(row, -1);

        return solve(s, k, 0, k - 1, t);
    }
}