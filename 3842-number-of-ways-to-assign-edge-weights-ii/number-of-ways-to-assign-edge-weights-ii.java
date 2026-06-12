class Solution {

    static final int MOD = 1_000_000_007;

    int[][] parent;
    int[] depth;
    List<Integer>[] graph;
    int LOG;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        LOG = 18;
        while ((1 << LOG) <= n)
            LOG++;

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        parent = new int[n + 1][LOG];
        depth = new int[n + 1];

        dfs(1, 0);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                parent[i][j] =
                        parent[parent[i][j - 1]][j - 1];
            }
        }

        long[] pow = new long[n];

        pow[0] = 1;

        for (int i = 1; i < n; i++) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int lca = lca(u, v);

            int dist =
                    depth[u] +
                    depth[v] -
                    2 * depth[lca];

            if (dist == 0)
                ans[i] = 0;
            else
                ans[i] = (int) pow[dist - 1];
        }

        return ans;
    }

    void dfs(int node, int par) {

        parent[node][0] = par;

        for (int next : graph[node]) {

            if (next == par)
                continue;

            depth[next] = depth[node] + 1;

            dfs(next, node);
        }
    }

    int lca(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int i = 0; i < LOG; i++) {
            if ((diff & (1 << i)) != 0)
                u = parent[u][i];
        }

        if (u == v)
            return u;

        for (int i = LOG - 1; i >= 0; i--) {

            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }
}