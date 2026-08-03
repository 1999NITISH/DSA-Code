class Solution {
    public boolean isAnagram(String s, String t) {

        int sl = s.length();
        int tl = t.length();

        if (sl != tl) {
            return false;
        }

        int[] arr = new int[26];

        // Count characters in s
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        // Remove characters of t
        for (int i = 0; i < t.length(); i++) {

            arr[t.charAt(i) - 'a']--;

            if (arr[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}