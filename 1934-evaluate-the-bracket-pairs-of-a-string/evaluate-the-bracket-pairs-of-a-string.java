import java.util.*;

public class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Build key -> value lookup.
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(') {
                // Find the matching ')' and extract the key in between.
                int j = i + 1;
                while (s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);

                // Replace with the known value, or "?" if unknown.
                result.append(map.getOrDefault(key, "?"));

                i = j + 1; // move past the ')'
            } else {
                // Plain character outside any bracket pair, copy as-is.
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }
}