class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int reversedAlphabetPos = 26 - (c - 'a'); // 'a'->26, 'b'->25, ..., 'z'->1
            int stringPos = i + 1; // 1-indexed position

            sum += reversedAlphabetPos * stringPos;
        }

        return sum;
    }
}