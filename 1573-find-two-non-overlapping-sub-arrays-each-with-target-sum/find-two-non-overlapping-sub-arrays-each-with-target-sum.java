class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n]; // dp[i] = min length of valid subarray ending at or before i
        Arrays.fill(dp, Integer.MAX_VALUE);

        int left = 0, sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int currLen = right - left + 1;

                // Try combining with the best subarray ending before 'left'
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    answer = Math.min(answer, currLen + dp[left - 1]);
                }

                // Update dp[right]
                if (right > 0) {
                    dp[right] = Math.min(dp[right - 1], currLen);
                } else {
                    dp[right] = currLen;
                }
            } else {
                dp[right] = (right > 0) ? dp[right - 1] : Integer.MAX_VALUE;
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}