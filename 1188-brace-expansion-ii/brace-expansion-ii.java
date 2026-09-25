import java.util.*;

public class Solution {
    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        this.s = expression;
        this.i = 0;

        Set<String> words = parseExpr();

        List<String> result = new ArrayList<>(words);
        Collections.sort(result);
        return result;
    }

    // expr := term (',' term)*
    // Union of everything separated by top-level commas.
    private Set<String> parseExpr() {
        Set<String> result = parseTerm();

        while (i < s.length() && s.charAt(i) == ',') {
            i++; // skip the comma
            result.addAll(parseTerm());
        }
        return result;
    }

    // term := factor+
    // Concatenation (cartesian product) of consecutive factors.
    // A term ends when we hit ',' (union boundary) or '}' (end of group)
    // or run out of string.
    private Set<String> parseTerm() {
        List<Set<String>> groups = new ArrayList<>();

        while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != '}') {
            groups.add(parseFactor());
        }

        // Combine via cartesian product: start with {""} and extend.
        Set<String> result = new HashSet<>();
        result.add("");

        for (Set<String> g : groups) {
            Set<String> next = new HashSet<>();
            for (String a : result) {
                for (String b : g) {
                    next.add(a + b);
                }
            }
            result = next;
        }
        return result;
    }

    // factor := letter | '{' expr '}'
    private Set<String> parseFactor() {
        if (s.charAt(i) == '{') {
            i++;                       // skip '{'
            Set<String> inner = parseExpr();
            i++;                       // skip '}'
            return inner;
        } else {
            char letter = s.charAt(i);
            i++;
            Set<String> single = new HashSet<>();
            single.add(String.valueOf(letter));
            return single;
        }
    }
}