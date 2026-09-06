class Solution {

    int[][] memo;

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }

        return solve(0, 0, s, t);
    }

    private int solve(int i, int j, String s, String t) {

        
        if (j == t.length()) {
            return 1;
        }

        
        if (i == s.length()) {
            return 0;
        }

        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        
        if (s.charAt(i) != t.charAt(j)) {
            return memo[i][j] = solve(i + 1, j, s, t);
        }

        
        return memo[i][j] =
                solve(i + 1, j + 1, s, t)
                + solve(i + 1, j, s, t);
    }
}
