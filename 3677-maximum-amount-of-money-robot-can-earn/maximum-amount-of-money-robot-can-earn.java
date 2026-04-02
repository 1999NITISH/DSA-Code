class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        int[][][] dp = new int[m][n][3];

        // Initialize with very small values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        // Base case (0,0)
        for (int k = 0; k < 3; k++) {
            if (coins[0][0] >= 0) {
                dp[0][0][k] = coins[0][0];
            } else {
                dp[0][0][k] = coins[0][0]; // take loss
                if (k > 0) dp[0][0][k] = Math.max(dp[0][0][k], 0); // neutralize
            }
        }

        // Fill DP
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) continue;

                for (int k = 0; k < 3; k++) {

                    int bestPrev = Integer.MIN_VALUE;

                    if (i > 0) bestPrev = Math.max(bestPrev, dp[i - 1][j][k]);
                    if (j > 0) bestPrev = Math.max(bestPrev, dp[i][j - 1][k]);

                    if (bestPrev == Integer.MIN_VALUE) continue;

                    if (coins[i][j] >= 0) {
                        dp[i][j][k] = bestPrev + coins[i][j];
                    } else {
                        // Option 1: take loss
                        dp[i][j][k] = bestPrev + coins[i][j];

                        // Option 2: neutralize
                        if (k > 0) {
                            int bestNeutral = Integer.MIN_VALUE;

                            if (i > 0) bestNeutral = Math.max(bestNeutral, dp[i - 1][j][k - 1]);
                            if (j > 0) bestNeutral = Math.max(bestNeutral, dp[i][j - 1][k - 1]);

                            dp[i][j][k] = Math.max(dp[i][j][k], bestNeutral);
                        }
                    }
                }
            }
        }

        return Math.max(dp[m - 1][n - 1][0],
                Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}