public class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String best = "";

        int left = 0, ones = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') ones++;

            // Shrink from the left while we have MORE than k ones, or while
            // the leftmost char is '0' and we still have exactly k ones
            // (shrinking past a leading zero only shortens the substring,
            // never removes a needed '1').
            while (ones > k || (ones == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') ones--;
                left++;
            }

            // Window [left, right] now has exactly k ones (if ones == k)
            // and starts with '1' (or the window is invalid if ones < k).
            if (ones == k) {
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);

                if (len < minLen) {
                    minLen = len;
                    best = candidate;
                } else if (len == minLen && candidate.compareTo(best) < 0) {
                    best = candidate;
                }
            }
        }

        return best;
    }
}