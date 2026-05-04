import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        for (int[] curr : intervals) {

            // If no overlap, add interval
            if (result.isEmpty() || result.get(result.size() - 1)[1] < curr[0]) {
                result.add(curr);
            } 
            // If overlap, merge
            else {
                result.get(result.size() - 1)[1] =
                        Math.max(result.get(result.size() - 1)[1], curr[1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}