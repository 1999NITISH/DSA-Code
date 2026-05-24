class Solution {

    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        int i = 0;

        while (i < n) {

            int correctIndex = nums[i] - 1;

            // place positive numbers at correct position
            if (nums[i] > 0 &&
                nums[i] <= n &&
                nums[i] != nums[correctIndex]) {

                swap(nums, i, correctIndex);

            } else {
                i++;
            }
        }

        // find first missing positive
        for (i = 0; i < n; i++) {

            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    private void swap(int[] nums, int a, int b) {

        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}