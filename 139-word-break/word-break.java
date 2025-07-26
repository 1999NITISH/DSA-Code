class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict); // for O(1) lookup
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // base case: empty string is always segmentable

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if (dp[j] && wordSet.contains(sub)) {
                    dp[i] = true;
                    break; // found a valid split, no need to check more
                }
            }
        }

        return dp[n];
    }
}
