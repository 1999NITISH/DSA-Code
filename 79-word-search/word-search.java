class Solution {
    private int rows, cols;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int k) {
        // Out of bounds or character mismatch
        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != word.charAt(k)) {
            return false;
        }

        // All characters matched
        if (k == word.length() - 1) {
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '#'; // mark as visited (sentinel — can't be a valid letter)

        boolean found = dfs(board, word, i + 1, j, k + 1)
                      || dfs(board, word, i - 1, j, k + 1)
                      || dfs(board, word, i, j + 1, k + 1)
                      || dfs(board, word, i, j - 1, k + 1);

        board[i][j] = temp; // restore (backtrack)

        return found;
    }
}