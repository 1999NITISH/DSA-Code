import java.util.*;

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        boolean[] seenA = new boolean[n + 1];
        boolean[] seenB = new boolean[n + 1];

        int common = 0;

        for (int i = 0; i < n; i++) {
            int a = A[i];
            int b = B[i];

            // process A[i]
            seenA[a] = true;
            if (seenB[a]) {
                common++;
            }

            // process B[i]
            seenB[b] = true;
            if (seenA[b]) {
                common++;
            }

            result[i] = common;
        }

        return result;
    }
}