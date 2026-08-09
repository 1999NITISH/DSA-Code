class Solution {
    private int[] suffix;
    private int[][] dp;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        // suffix[i] = total stones from i to n-1
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];

        return solve(0, 1);
    }

    private int solve(int index, int M) {

        // No piles left
        if (index >= n) {
            return 0;
        }

        // Can take all remaining piles
        if (index + 2 * M >= n) {
            return suffix[index];
        }

        // Already calculated
        if (dp[index][M] != 0) {
            return dp[index][M];
        }

        int maxStones = 0;

        // Try taking 1 to 2*M piles
        for (int X = 1; X <= 2 * M; X++) {

            int nextM = Math.max(M, X);

            int opponentStones = solve(index + X, nextM);

            int currentPlayerStones =
                    suffix[index] - opponentStones;

            maxStones = Math.max(maxStones, currentPlayerStones);
        }

        return dp[index][M] = maxStones;
    }
}