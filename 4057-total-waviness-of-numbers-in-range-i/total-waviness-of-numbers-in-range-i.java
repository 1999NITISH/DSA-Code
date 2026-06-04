class Solution {

    public int totalWaviness(int num1, int num2) {

        int total = 0;

        for (int num = num1; num <= num2; num++) {
            total += getWaviness(num);
        }

        return total;
    }

    private int getWaviness(int num) {

        String s = String.valueOf(num);

        // Less than 3 digits => no peaks/valleys
        if (s.length() < 3) {
            return 0;
        }

        int waviness = 0;

        // Check middle digits only
        for (int i = 1; i < s.length() - 1; i++) {

            int prev = s.charAt(i - 1) - '0';
            int curr = s.charAt(i) - '0';
            int next = s.charAt(i + 1) - '0';

            // Peak
            if (curr > prev && curr > next) {
                waviness++;
            }

            // Valley
            else if (curr < prev && curr < next) {
                waviness++;
            }
        }

        return waviness;
    }
}