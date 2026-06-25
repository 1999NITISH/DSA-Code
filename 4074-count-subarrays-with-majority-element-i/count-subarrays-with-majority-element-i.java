class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int ans = 0;

        for (int r = 1; r <= n; r++) {
            for (int l = 0; l < r; l++) {
                if (prefix[r] > prefix[l]) {
                    ans++;
                }
            }
        }

        return ans;
    }
}