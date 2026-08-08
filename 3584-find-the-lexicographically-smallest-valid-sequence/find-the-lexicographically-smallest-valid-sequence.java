class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = index in word2 from which we can match
        // word2 as a subsequence using word1[i...]
        int[] suf = new int[n + 1];

        suf[n] = m;

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = j + 1;
        }

        int[] ans = new int[m];

        int j2 = 0;
        boolean usedMismatch = false;

        for (int i = 0; i < n && j2 < m; i++) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j2)) {
                ans[j2] = i;
                j2++;
                continue;
            }

            // Try using mismatch
            if (!usedMismatch) {
                // After using i for word2[j2],
                // word2[j2+1...] must be matched exactly.
                int remaining = m - j2 - 1;

                if (m - suf[i + 1] >= remaining) {
                    ans[j2] = i;
                    j2++;
                    usedMismatch = true;
                }
            }
        }

        if (j2 != m) {
            return new int[0];
        }

        return ans;
    }
}