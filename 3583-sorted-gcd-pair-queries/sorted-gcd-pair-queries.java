class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] exact = new long[max + 1];
        int[] count = new int[max + 1];

        // Count numbers divisible by each d
        for (int d = 1; d <= max; d++) {
            for (int multiple = d; multiple <= max; multiple += d) {
                count[d] += freq[multiple];
            }
        }

        // Count pairs divisible by d
        for (int d = 1; d <= max; d++) {
            long c = count[d];
            exact[d] = c * (c - 1) / 2;
        }

        // Inclusion-Exclusion
        for (int d = max; d >= 1; d--) {
            for (int multiple = d * 2; multiple <= max; multiple += d) {
                exact[d] -= exact[multiple];
            }
        }

        // Prefix counts
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i];

            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) / 2;
                if (prefix[mid] > k)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}