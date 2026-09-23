class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        int[][] count = new int[2 * n - 1][2 * n - 1];

        int answer = 0;

        // Take every 1 from img1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (img1[i][j] == 0) {
                    continue;
                }

                // Take every 1 from img2
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {

                        if (img2[x][y] == 0) {
                            continue;
                        }

                        // Translation required to move
                        // img1[i][j] to img2[x][y]
                        int rowShift = x - i;
                        int colShift = y - j;

                        // Shift by (-(n-1)) ... +(n-1)
                        // Convert to array index
                        int r = rowShift + n - 1;
                        int c = colShift + n - 1;

                        count[r][c]++;

                        answer = Math.max(
                            answer,
                            count[r][c]
                        );
                    }
                }
            }
        }

        return answer;
    }
}