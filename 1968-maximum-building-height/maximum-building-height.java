class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        int m = restrictions.length;

        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(r);
        }

        boolean hasN = false;

        for (int[] r : restrictions) {
            if (r[0] == n) {
                hasN = true;
                break;
            }
        }

        if (!hasN) {
            list.add(new int[]{n, n - 1});
        }

        list.sort((a, b) -> Integer.compare(a[0], b[0]));

        int size = list.size();

        // Left -> Right
        for (int i = 1; i < size; i++) {
            int d = list.get(i)[0] - list.get(i - 1)[0];

            list.get(i)[1] = Math.min(
                list.get(i)[1],
                list.get(i - 1)[1] + d
            );
        }

        // Right -> Left
        for (int i = size - 2; i >= 0; i--) {
            int d = list.get(i + 1)[0] - list.get(i)[0];

            list.get(i)[1] = Math.min(
                list.get(i)[1],
                list.get(i + 1)[1] + d
            );
        }

        long ans = 0;

        for (int i = 1; i < size; i++) {

            long x1 = list.get(i - 1)[0];
            long h1 = list.get(i - 1)[1];

            long x2 = list.get(i)[0];
            long h2 = list.get(i)[1];

            long d = x2 - x1;

            long peak = (h1 + h2 + d) / 2;

            ans = Math.max(ans, peak);
        }

        return (int) ans;
    }
}