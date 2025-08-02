import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new TreeMap<>();
        int n = basket1.length;

        // Step 1: Count frequency in both baskets
        for (int val : basket1) freq.put(val, freq.getOrDefault(val, 0) + 1);
        for (int val : basket2) freq.put(val, freq.getOrDefault(val, 0) - 1);

        List<Integer> toSwap = new ArrayList<>();
        int minGlobal = Integer.MAX_VALUE;

        // Step 2: Check if possible and build list of extra items to swap
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int val = entry.getKey();
            int count = entry.getValue();

            // If imbalance is odd → impossible
            if (count % 2 != 0) return -1;

            int absCount = Math.abs(count) / 2;
            for (int i = 0; i < absCount; i++) {
                toSwap.add(val);
            }

            minGlobal = Math.min(minGlobal, val);
        }

        // Step 3: Sort for greedy strategy
        Collections.sort(toSwap);

        long totalCost = 0;
        int m = toSwap.size();

        for (int i = 0; i < m / 2; i++) {
            int a = toSwap.get(i);
            int b = toSwap.get(m - 1 - i);
            // Either direct swap or via 2 * minGlobal
            totalCost += Math.min(a, 2 * minGlobal);
        }

        return totalCost;
    }
}
