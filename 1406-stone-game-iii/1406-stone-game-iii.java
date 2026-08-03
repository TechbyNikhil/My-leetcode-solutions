class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] = Maximum score difference (Current Player - Opponent)
        // that the current player can achieve starting from index i.
        int[] dp = new int[n + 1];
        // Base Case: dp[n] = 0 because there are no stones left to take.(Java initializes arrays with 0 by default.)
        // Process from the end towards the beginning.
        for (int i = n - 1; i >= 0; i--) {
            // Initialize with the smallest possible value since we want to maximize the score difference.
            dp[i] = Integer.MIN_VALUE;
            // Running sum of stones taken in the current move.
            int sum = 0;
            // Try taking 1, 2, and 3 stones (if available).
            for (int k = 0; k < 3 && i + k < n; k++) {
                // Add the next stone to the current move.
                sum += stoneValue[i + k];
                // If we take (k + 1) stones:- We immediately gain 'sum' points.
                //   - The opponent starts from index (i + k + 1)  and can achieve dp[i + k + 1] score difference.
                // Therefore, our final score difference becomes:    sum - dp[i + k + 1]
                // Choose the move that gives the maximum difference.
                dp[i] = Math.max(dp[i], sum - dp[i + k + 1]);
            }
        }
        // Positive difference -> Alice scores more.
        if (dp[0] > 0) return "Alice";
        // Negative difference -> Bob scores more.
        if (dp[0] < 0) return "Bob";
        // Zero difference -> Both end with the same score.
        return "Tie";
    }
}