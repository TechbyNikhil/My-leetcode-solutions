class Solution {
    Integer[][] dp;
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new Integer[n][n];
        int total = 0;
        for(int x : piles) total += x;
        int Alice = solve(piles,0,n-1);
        int Bob = total - Alice;
        return Alice > Bob;
    }
    private int solve(int[] piles, int i, int j){
        if(i>j) return 0;
        if(i==j) return piles[i];
        if(dp[i][j] != null){
            return dp[i][j];
        }
        int left = piles[i] + Math.min(solve(piles, i+2,j), solve(piles, i+1,j-1));
        int right = piles[j] + Math.min(solve(piles, i+1,j-1), solve(piles, i,j-2));
        return dp[i][j] = Math.max(left, right);
    }
}