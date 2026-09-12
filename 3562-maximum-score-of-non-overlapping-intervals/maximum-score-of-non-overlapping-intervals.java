import java.util.*;

class Solution {

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by ending position
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[3], b[3]);
        });

        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            ends[i] = arr[i][1];
        }

        // prev[i] = last interval with end < arr[i][0]
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = binarySearch(ends, arr[i][0], i);
        }

        /*
         * dp[k][i]:
         * Best answer using AT MOST k intervals
         * from first i sorted intervals.
         */
        State[][] dp = new State[5][n + 1];

        // IMPORTANT:
        // Every k has an empty solution with score 0.
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new State(0, new ArrayList<>());
            }
        }

        for (int k = 1; k <= 4; k++) {

            for (int i = 1; i <= n; i++) {

                int current = i - 1;

                // 1. Don't take current interval
                State skip = dp[k][i - 1];

                // 2. Take current interval
                State before = dp[k - 1][prev[current] + 1];

                long takeScore =
                        before.score + (long) arr[current][2];

                List<Integer> takeIndices =
                        new ArrayList<>(before.indices);

                takeIndices.add(arr[current][3]);

                Collections.sort(takeIndices);

                State take = new State(takeScore, takeIndices);

                dp[k][i] = chooseBetter(skip, take);
            }
        }

        List<Integer> answer = dp[4][n].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int binarySearch(int[] ends, int start, int current) {

        int left = 0;
        int right = current - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (ends[mid] < start) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private State chooseBetter(State a, State b) {

        // Higher score is always better
        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        // Same score:
        // lexicographically smaller index array wins
        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compare(List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}