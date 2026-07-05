class Solution {

    int MOD = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                char c = board.get(i).charAt(j);

                if (c == 'X')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                int best = -1;
                int cnt = 0;

                if (i > 0 && score[i - 1][j] != -1) {

                    if (score[i - 1][j] > best) {
                        best = score[i - 1][j];
                        cnt = ways[i - 1][j];
                    } else if (score[i - 1][j] == best) {
                        cnt = (cnt + ways[i - 1][j]) % MOD;
                    }
                }

                if (j > 0 && score[i][j - 1] != -1) {

                    if (score[i][j - 1] > best) {
                        best = score[i][j - 1];
                        cnt = ways[i][j - 1];
                    } else if (score[i][j - 1] == best) {
                        cnt = (cnt + ways[i][j - 1]) % MOD;
                    }
                }

                if (i > 0 && j > 0 && score[i - 1][j - 1] != -1) {

                    if (score[i - 1][j - 1] > best) {
                        best = score[i - 1][j - 1];
                        cnt = ways[i - 1][j - 1];
                    } else if (score[i - 1][j - 1] == best) {
                        cnt = (cnt + ways[i - 1][j - 1]) % MOD;
                    }
                }

                if (best == -1)
                    continue;

                score[i][j] = best;

                if (c != 'S')
                    score[i][j] += c - '0';

                ways[i][j] = cnt;
            }
        }

        if (ways[n - 1][n - 1] == 0)
            return new int[]{0, 0};

        return new int[]{score[n - 1][n - 1], ways[n - 1][n - 1]};
    }
}