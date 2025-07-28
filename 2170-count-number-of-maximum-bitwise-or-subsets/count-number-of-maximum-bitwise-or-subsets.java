class Solution {
    int maxOr = 0;
    int count = 0;

    public int countMaxOrSubsets(int[] nums) {
        // Step 1: Find the maximum possible OR
        for (int num : nums) {
            maxOr |= num;
        }

        // Step 2: Start backtracking
        backtrack(nums, 0, 0);
        return count;
    }

    private void backtrack(int[] nums, int index, int currentOr) {
        if (index == nums.length) {
            if (currentOr == maxOr) {
                count++;
            }
            return;
        }

        // Include nums[index]
        backtrack(nums, index + 1, currentOr | nums[index]);

        // Exclude nums[index]
        backtrack(nums, index + 1, currentOr);
    }
}
