class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Find valid intervals
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int ch = s.charAt(i) - 'a';

                // Character occurs before our interval
                if (first[ch] < left) {
                    valid = false;
                    break;
                }

                // Expand interval
                right = Math.max(right, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort intervals by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> result = new ArrayList<>();

        int prevEnd = -1;

        // Greedy selection
        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            if (left > prevEnd) {
                result.add(s.substring(left, right + 1));
                prevEnd = right;
            }
        }

        return result;
    }
}