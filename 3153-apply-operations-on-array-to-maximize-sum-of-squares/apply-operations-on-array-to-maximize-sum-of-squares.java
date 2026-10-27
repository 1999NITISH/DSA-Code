import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;

        int[] count = new int[31];

        // Count set bits at each position
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        long ans = 0;

        // Construct k maximum numbers
        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    x |= (1L << bit);
                    count[bit]--;
                }
            }

            ans = (ans + x * x) % MOD;
        }

        return (int) ans;
    }
}