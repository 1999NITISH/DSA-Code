class Solution {
    public String getPermutation(int n, int k) {
        int[] fact = new int[n + 1];
        List<Integer> numbers = new ArrayList<>();
        
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            numbers.add(i);
        }

        k--; // convert to 0-indexed

        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            int idx = k / fact[i - 1];
            k %= fact[i - 1];
            sb.append(numbers.get(idx));
            numbers.remove(idx);
        }

        return sb.toString();
    }
}