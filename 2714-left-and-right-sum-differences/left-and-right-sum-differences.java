class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        int totalSum = 0;

        // Calculate total sum
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < n; i++) {

            // Right sum = totalSum - leftSum - current element
            int rightSum = totalSum - leftSum - nums[i];

            // Store absolute difference
            answer[i] = Math.abs(leftSum - rightSum);

            // Update leftSum
            leftSum += nums[i];
        }

        return answer;
    }
}