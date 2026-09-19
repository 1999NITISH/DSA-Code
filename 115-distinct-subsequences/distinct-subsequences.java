class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        long[][] dp = new long[m + 1][n + 1]; // use long to avoid overflow during computation

        // Base case: empty t can be formed exactly one way from any prefix of s
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j]; // don't use s[i-1]

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1]; // use s[i-1] to match t[j-1]
                }
            }
        }

        return (int) dp[m][n];
    }
}