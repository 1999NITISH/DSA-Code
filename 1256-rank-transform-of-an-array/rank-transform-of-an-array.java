import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        // Copy the original array
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // Assign ranks to unique elements
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int num : sorted) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }

        // Replace each element with its rank
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = rankMap.get(arr[i]);
        }

        return ans;
    }
}