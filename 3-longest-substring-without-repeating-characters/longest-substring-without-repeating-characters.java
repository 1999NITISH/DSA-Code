class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);

            // If character already exists in window, shrink the window from the left
            if (map.containsKey(ch)) {
                left = Math.max(map.get(ch) + 1, left); // update left pointer
            }

            map.put(ch, right); // store/update character's latest index
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
