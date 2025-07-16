class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] length = new long[n + 1];  // length[i] = result length after processing s[0..i-1]
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                length[i + 1] = length[i] + 1;
            } else if (ch == '*') {
                length[i + 1] = Math.max(0, length[i] - 1);
            } else if (ch == '#') {
                length[i + 1] = Math.min(1_000_000_000_000_000L, length[i] * 2);
            } else if (ch == '%') {
                length[i + 1] = length[i];
            }
        }

        if (k >= length[n]) return '.';

        // Backtrack from the end to find the k-th character
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                if (k == length[i]) return ch;
                // Else move left
            } else if (ch == '*') {
                if (length[i] < length[i + 1]) {
                    k += 1; // * removed a character, so we simulate undoing that
                }
            } else if (ch == '#') {
                if (k >= length[i]) {
                    k -= length[i];  // It came from the second half of duplicated string
                }
                // Else it came from the first half, k remains the same
            } else if (ch == '%') {
                k = length[i] - 1 - k; // reverse: position changes
            }
        }

        return '.'; // fallback, shouldn't reach here
    }
}
