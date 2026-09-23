import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            // Convert string to character array
            char[] chars = str.toCharArray();

            // Sort characters
            Arrays.sort(chars);

            // Sorted string becomes the key
            String key = new String(chars);

            // Add string to its anagram group
            map.computeIfAbsent(key, k -> new ArrayList<>())
               .add(str);
        }

        return new ArrayList<>(map.values());
    }
}