class Solution {
    public int reverse(int x) {
        int rev = 0;

        // Use a for loop to simulate the same logic
        for (; x != 0; x /= 10) {
            int digit = x % 10;

            // Check for 32-bit integer overflow
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) return 0;

            rev = rev * 10 + digit;
        }

        return rev;
    }
}
