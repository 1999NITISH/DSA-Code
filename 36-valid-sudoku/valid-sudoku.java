import java.util.HashSet;

class Solution {
    public boolean isValidSudoku(char[][] board) {

        HashSet<String> set = new HashSet<>();

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                char num = board[row][col];

                // Skip empty cells
                if (num == '.') {
                    continue;
                }

                // Create unique keys
                String rowKey = num + " in row " + row;
                String colKey = num + " in col " + col;
                String boxKey = num + " in box " + (row / 3) + "-" + (col / 3);

                // If already exists → invalid
                if (set.contains(rowKey) ||
                    set.contains(colKey) ||
                    set.contains(boxKey)) {

                    return false;
                }

                // Add to set
                set.add(rowKey);
                set.add(colKey);
                set.add(boxKey);
            }
        }

        return true;
    }
}