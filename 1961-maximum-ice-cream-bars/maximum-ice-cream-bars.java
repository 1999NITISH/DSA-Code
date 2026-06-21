class Solution {
    public int maxIceCream(int[] costs, int coins) {

        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int count = 0;

        for (int cost = 1; cost <= maxCost; cost++) {

            while (freq[cost] > 0 && coins >= cost) {
                coins -= cost;
                count++;
                freq[cost]--;
            }
        }

        return count;
    }
}