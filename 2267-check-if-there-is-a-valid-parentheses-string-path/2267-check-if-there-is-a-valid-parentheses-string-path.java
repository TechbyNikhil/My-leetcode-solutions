class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        // If the path length is odd, it's impossible to balance parentheses
        if ((m + n - 1) % 2 != 0) return false;
        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        // Max possible open brackets can't exceed (m + n) / 2
        int maxBalance = (m + n) / 2 + 1;
        memo = new Boolean[m][n][maxBalance];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance) {
        // Update balance based on current cell
        balance += (grid[r][c] == '(') ? 1 : -1;

        // Base Case 1: Invalid balance
        if (balance < 0 || balance >= memo[0][0].length) return false;

        // Base Case 2: Reached the bottom-right destination
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Base Case 3: Return memoized result if already calculated
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        // Optimization: Can we actually close all brackets with the remaining moves?
        int remainingMoves = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingMoves) {
            return memo[r][c][balance] = false;
        }

        boolean foundPath = false;

        // Move Down
        if (r + 1 < m) {
            foundPath = dfs(grid, r + 1, c, balance);
        }
        
        // Move Right (only if down didn't already find a path)
        if (!foundPath && c + 1 < n) {
            foundPath = dfs(grid, r, c + 1, balance);
        }

        // Cache and return the result
        return memo[r][c][balance] = foundPath;
    }
}