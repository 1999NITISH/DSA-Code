class Solution {
    
    // Directions mapping for each type
    private static final int[][][] dirs = {
        {},
        {{0,-1},{0,1}},   // 1 → left, right
        {{-1,0},{1,0}},   // 2 → up, down
        {{0,-1},{1,0}},   // 3 → left, down
        {{0,1},{1,0}},    // 4 → right, down
        {{0,-1},{-1,0}},  // 5 → left, up
        {{0,1},{-1,0}}    // 6 → right, up
    };
    
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            
            // Reached destination
            if (r == m - 1 && c == n - 1) return true;
            
            int type = grid[r][c];
            
            // Try all possible directions from current cell
            for (int[] d : dirs[type]) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                // Boundary + visited check
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || visited[nr][nc])
                    continue;
                
                int nextType = grid[nr][nc];
                
                // Check reverse connection
                for (int[] back : dirs[nextType]) {
                    if (nr + back[0] == r && nc + back[1] == c) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }
        
        return false;
    }
}