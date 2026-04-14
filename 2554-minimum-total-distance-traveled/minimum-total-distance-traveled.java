import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        // Expand factories based on limit
        List<Integer> factories = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0], limit = f[1];
            for (int i = 0; i < limit; i++) {
                factories.add(pos);
            }
        }

        int n = robot.size();
        int m = factories.size();

        long[][] dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        // base case
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // skip factory
                dp[i][j] = dp[i][j - 1];

                // use factory
                if (dp[i - 1][j - 1] != Long.MAX_VALUE) {
                    long cost = dp[i - 1][j - 1] +
                            Math.abs(robot.get(i - 1) - factories.get(j - 1));
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[n][m];
    }
}