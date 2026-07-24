class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] dp1 = new boolean[MAX];
        boolean[] dp2 = new boolean[MAX];
        boolean[] dp3 = new boolean[MAX];

        for (int v : nums) {
            boolean[] n1 = dp1.clone();
            boolean[] n2 = dp2.clone();
            boolean[] n3 = dp3.clone();

            // Take current value once
            n1[v] = true;
            for (int x = 0; x < MAX; x++) {
                if (dp1[x]) n2[x ^ v] = true;
                if (dp2[x]) n3[x ^ v] = true;
            }

            // Take current value twice (v ^ v = 0)
            n2[0] = true;
            for (int x = 0; x < MAX; x++) {
                if (dp1[x]) n3[x] = true;
            }

            // Take current value three times (v ^ v ^ v = v)
            n3[v] = true;

            dp1 = n1;
            dp2 = n2;
            dp3 = n3;
        }

        int ans = 0;
        for (boolean b : dp3) {
            if (b) ans++;
        }
        return ans;
    }
}