class Solution {
    public int numTilePossibilities(String tiles) {
        int[] freq = new int[26];
        for (char c : tiles.toCharArray()) {
            freq[c - 'A']++;
        }
        return backtrack(freq);
    }

    private int backtrack(int[] freq) {
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            // Choose the letter
            count++;
            freq[i]--;

            // Recurse with the letter chosen
            count += backtrack(freq);

            // Backtrack
            freq[i]++;
        }
        return count;
    }
}
