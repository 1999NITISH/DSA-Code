import java.util.*;

public class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();

        for (String q : queries) {
            for (String d : dictionary) {
                if (isValid(q, d)) {
                    result.add(q);
                    break; // no need to check further
                }
            }
        }

        return result;
    }

    private boolean isValid(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) return false;
            }
        }

        return true;
    }

    // Optional main for testing
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] queries = {"word","note","ants","wood"};
        String[] dictionary = {"wood","joke","moat"};

        System.out.println(sol.twoEditWords(queries, dictionary));
    }
}