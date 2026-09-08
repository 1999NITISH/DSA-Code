class Solution {
    public int countCommas(int n) {
        int ans = 0;

        int start = 1000;
        int commas = 1;

        while (start <= n) {
            ans += (n - start + 1) * commas;

            start *= 1000;
            commas++;
        }

        return ans;
    }
}