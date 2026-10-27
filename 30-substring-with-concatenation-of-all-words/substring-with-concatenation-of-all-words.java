import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (totalLen > s.length()) {
            return result;
        }

        // Frequency of each word we need
        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Try each possible alignment
        for (int start = 0; start < wordLen; start++) {

            int left = start;
            int count = 0;

            Map<String, Integer> seen = new HashMap<>();

            for (int right = start; right + wordLen <= s.length(); right += wordLen) {

                String word = s.substring(right, right + wordLen);

                // Word is not required
                if (!required.containsKey(word)) {
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                // Add current word
                seen.put(word, seen.getOrDefault(word, 0) + 1);
                count++;

                // Too many copies of this word
                while (seen.get(word) > required.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);

                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }

                // We have exactly all words
                if (count == wordCount) {
                    result.add(left);

                    // Move window forward to look for another answer
                    String leftWord = s.substring(left, left + wordLen);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}