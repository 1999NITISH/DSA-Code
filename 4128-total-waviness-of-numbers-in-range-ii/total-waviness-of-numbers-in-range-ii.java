class Solution {

    class Pair {
        long count; // number of ways
        long sum;   // total waviness

        Pair(long c, long s) {
            count = c;
            sum = s;
        }
    }

    private String s;
    private Pair[][][][] memo;
    private boolean[][][][] vis;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        s = String.valueOf(n);
        int len = s.length();

        memo = new Pair[len][11][11][2];
        vis = new boolean[len][11][11][2];

        return dfs(0, 10, 10, 1, true).sum;
    }

    private Pair dfs(int pos, int prev1, int prev2, int started, boolean tight) {

        if (pos == s.length()) {
            return new Pair(1, 0);
        }

        if (!tight && vis[pos][prev1][prev2][started]) {
            return memo[pos][prev1][prev2][started];
        }

        int limit = tight ? s.charAt(pos) - '0' : 9;

        long totalCount = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            // still leading zeros
            if (started == 1 && d == 0) {
                Pair nxt = dfs(pos + 1, 10, 10, 1, nextTight);

                totalCount += nxt.count;
                totalSum += nxt.sum;
            } else {

                int add = 0;

                // if we already have two previous digits,
                // then prev1 is middle digit
                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                int newPrev2;
                int newPrev1;

                if (prev1 == 10) {
                    newPrev2 = 10;
                    newPrev1 = d;
                } else {
                    newPrev2 = prev1;
                    newPrev1 = d;
                }

                Pair nxt = dfs(pos + 1, newPrev1, newPrev2, 0, nextTight);

                totalCount += nxt.count;
                totalSum += nxt.sum + (long) add * nxt.count;
            }
        }

        Pair ans = new Pair(totalCount, totalSum);

        if (!tight) {
            vis[pos][prev1][prev2][started] = true;
            memo[pos][prev1][prev2][started] = ans;
        }

        return ans;
    }
}