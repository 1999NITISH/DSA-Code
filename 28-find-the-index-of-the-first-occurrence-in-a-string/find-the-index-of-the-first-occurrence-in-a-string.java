public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0; // edge case: empty needle
        }
        int n1 = haystack.length();
        int n2 = needle.length();
        for (int i = 0; i <= n1 - n2; i++) { // loop only till haystack length - needle length
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (compare(haystack, needle, i)) {
                    return i;
                }
            }
        }
        return -1;
    }
    private boolean compare(String haystack, String needle, int start) {
        int n2 = needle.length();
        for (int j = 0; j < n2; j++) {
            if (haystack.charAt(start + j) != needle.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}