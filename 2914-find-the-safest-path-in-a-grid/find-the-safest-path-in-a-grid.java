class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        // Distance of each cell from the nearest thief
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Multi-source BFS starting from all thieves
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] d : dirs) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n &&
                    dist[nr][nc] == Integer.MAX_VALUE) {

                    dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        int low = 0, high = 2 * n, ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(dist, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int[][] dist, int safe) {
        int n = dist.length;

        if (dist[0][0] < safe) return false;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == n - 1 && cur[1] == n - 1) {
                return true;
            }

            for (int[] d : dirs) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n &&
                    !visited[nr][nc] &&
                    dist[nr][nc] >= safe) {

                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}