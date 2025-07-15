import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges, int k) {
        int left = 0, right = 1_000_000, answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canFormKComponents(n, edges, k, mid)) {
                answer = mid;  // possible, try lower
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    // Check if we can build a forest with <= k components
    private boolean canFormKComponents(int n, int[][] edges, int k, int maxAllowedWeight) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (edge[2] <= maxAllowedWeight) {
                uf.union(edge[0], edge[1]);
            }
        }

        int components = uf.countComponents();
        return components <= k;
    }

    class UnionFind {
        int[] parent;
        int count;

        UnionFind(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;
            parent[py] = px;
            count--;
            return true;
        }

        int countComponents() {
            return count;
        }
    }
}
