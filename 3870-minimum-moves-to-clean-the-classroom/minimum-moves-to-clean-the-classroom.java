class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();

        int sr = -1, sc = -1;
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) Arrays.fill(row, -1);
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == 'S') { sr = i; sc = j; }
                else if (c == 'L') { litterIdx[i][j] = litterCount++; }
            }
        }

        int L = litterCount;
        int fullMask = (1 << L) - 1;
        if (L == 0) return 0;

        int E = energy;
        int numEnergyStates = E + 1;
        int numMaskStates = 1 << L;
        int numCells = m * n;
        int maxStates = numCells * numEnergyStates * numMaskStates;

        boolean[] visited = new boolean[maxStates];
        int[] queue = new int[maxStates];
        int head = 0, tail = 0;

        int startId = encode(sr, sc, E, 0, n, numEnergyStates, numMaskStates);
        visited[startId] = true;
        queue[tail++] = startId;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;
        while (head < tail) {
            int levelSize = tail - head;
            for (int i = 0; i < levelSize; i++) {
                int id = queue[head++];

                int tmp = id;
                int mask = tmp % numMaskStates; tmp /= numMaskStates;
                int e = tmp % numEnergyStates; tmp /= numEnergyStates;
                int r = tmp / n, c = tmp % n;

                if (mask == fullMask) return moves;
                if (e == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    if (grid[nr][nc] == 'X') continue;

                    int ne = e - 1;
                    if (grid[nr][nc] == 'R') ne = E;

                    int nmask = mask;
                    if (litterIdx[nr][nc] != -1) {
                        nmask |= (1 << litterIdx[nr][nc]);
                    }

                    int nid = encode(nr, nc, ne, nmask, n, numEnergyStates, numMaskStates);
                    if (!visited[nid]) {
                        visited[nid] = true;
                        queue[tail++] = nid;
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    private int encode(int r, int c, int e, int mask, int n, int numEnergyStates, int numMaskStates) {
        return ((r * n + c) * numEnergyStates + e) * numMaskStates + mask;
    }
}