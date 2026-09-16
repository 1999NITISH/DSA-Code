class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;
        long[][] dp = new long[n][k + 1];

        // 0 segments = exactly 1 way, for any prefix of points
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= k; j++) {
            dp[0][j] = 0; // can't form a segment using only point 0

            // prefix = running sum of dp[p][j-1] for p = 0 .. i-1
            long prefix = dp[0][j - 1]; // seed with p = 0 before the loop starts

            for (int i = 1; i < n; i++) {
                dp[i][j] = (dp[i - 1][j] + prefix) % MOD;
                prefix = (prefix + dp[i][j - 1]) % MOD; // now include p = i for next iteration
            }
        }

        return (int) dp[n - 1][k];
    }
}