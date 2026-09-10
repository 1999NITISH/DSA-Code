class Solution {

    int result = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return result;
    }

    // returns {sum, count}
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Get left subtree information
        int[] left = dfs(node.left);

        // Get right subtree information
        int[] right = dfs(node.right);

        // Calculate current subtree
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        // Calculate average
        int average = sum / count;

        // Check whether current node equals average
        if (node.val == average) {
            result++;
        }

        return new int[]{sum, count};
    }
}