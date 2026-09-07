class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        long dp = 1; // empty subsequence

        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long newDp = (2 * dp % MOD - last[index] + MOD) % MOD;

            last[index] = dp;
            dp = newDp;
        }

        // Remove empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}