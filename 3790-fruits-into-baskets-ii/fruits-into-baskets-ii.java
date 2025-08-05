class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] used = new boolean[n]; // tracks which baskets are already taken
        int unplaced = 0;

        for (int i = 0; i < n; i++) {
            int fruitQty = fruits[i];
            boolean placed = false;
            // find leftmost available basket that fits
            for (int j = 0; j < n; j++) {
                if (!used[j] && baskets[j] >= fruitQty) {
                    used[j] = true;
                    placed = true;
                    break;
                }
            }
            if (!placed) unplaced++;
        }

        return unplaced;
    }
}
