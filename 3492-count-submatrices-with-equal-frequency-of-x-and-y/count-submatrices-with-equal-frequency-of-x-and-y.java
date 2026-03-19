class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] countX = new int[n][m];
        int[][] countY = new int[n][m];

        // Build prefix sums
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int x = (grid[i][j] == 'X') ? 1 : 0;
                int y = (grid[i][j] == 'Y') ? 1 : 0;

                countX[i][j] = x;
                countY[i][j] = y;

                if (i > 0) {
                    countX[i][j] += countX[i - 1][j];
                    countY[i][j] += countY[i - 1][j];
                }
                if (j > 0) {
                    countX[i][j] += countX[i][j - 1];
                    countY[i][j] += countY[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    countX[i][j] -= countX[i - 1][j - 1];
                    countY[i][j] -= countY[i - 1][j - 1];
                }
            }
        }

        int result = 0;

        // Check all (0,0) → (i,j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int totalX = countX[i][j];
                int totalY = countY[i][j];

                if (totalX > 0 && totalX == totalY) {
                    result++;
                }
            }
        }

        return result;
    }
}