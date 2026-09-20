class Solution {
    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        // Initialize lookup tables based on the given board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[boxIndex][num] = true;
                }
            }
        }

        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (int num = 1; num <= 9; num++) {
                        int boxIndex = (i / 3) * 3 + (j / 3);

                        if (!rows[i][num] && !cols[j][num] && !boxes[boxIndex][num]) {
                            // Place
                            board[i][j] = (char) ('0' + num);
                            rows[i][num] = true;
                            cols[j][num] = true;
                            boxes[boxIndex][num] = true;

                            if (solve(board)) {
                                return true;
                            }

                            // Undo (backtrack)
                            board[i][j] = '.';
                            rows[i][num] = false;
                            cols[j][num] = false;
                            boxes[boxIndex][num] = false;
                        }
                    }
                    return false; // no digit worked for this cell — trigger backtrack
                }
            }
        }
        return true; // no empty cells left — solved
    }
}