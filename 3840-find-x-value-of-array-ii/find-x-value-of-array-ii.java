class Solution {

    static class Node {
        int product;
        long[] pref;

        Node(int k) {
            pref = new long[k];
        }
    }

    int n;
    int k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        // Build segment tree
        build(1, 0, n - 1);

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update nums[index]
            nums[index] = value;

            // Update segment tree
            update(1, 0, n - 1, index, value);

            // Query range [start, n - 1]
            Node result = query(
                1,
                0,
                n - 1,
                start,
                n - 1
            );

            // Number of prefixes having product % k == x
            answer[q] = (int) result.pref[x];
        }

        return answer;
    }

    // --------------------------------------------------
    // BUILD
    // --------------------------------------------------

    void build(int node, int left, int right) {

        // Leaf node
        if (left == right) {

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;

            // Single element is one prefix
            tree[node].pref[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // --------------------------------------------------
    // MERGE
    // --------------------------------------------------

    Node merge(Node left, Node right) {

        Node result = new Node(k);

        /*
         * Product of complete segment:
         *
         * left.product * right.product
         */
        result.product =
            (left.product * right.product) % k;

        /*
         * Case 1:
         * Prefix is completely inside left segment.
         *
         * Its remainder doesn't change.
         */
        for (int r = 0; r < k; r++) {
            result.pref[r] += left.pref[r];
        }

        /*
         * Case 2:
         * Prefix contains the entire left segment
         * and then some prefix of right.
         *
         * New remainder:
         *
         * leftProduct * rightPrefixProduct % k
         */
        for (int r = 0; r < k; r++) {

            if (right.pref[r] == 0) {
                continue;
            }

            int newRemainder =
                (left.product * r) % k;

            result.pref[newRemainder] += right.pref[r];
        }

        return result;
    }

    // --------------------------------------------------
    // UPDATE
    // --------------------------------------------------

    void update(
        int node,
        int left,
        int right,
        int index,
        int value
    ) {

        // Leaf
        if (left == right) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].product = rem;
            tree[node].pref[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {

            update(
                node * 2,
                left,
                mid,
                index,
                value
            );

        } else {

            update(
                node * 2 + 1,
                mid + 1,
                right,
                index,
                value
            );
        }

        // Recalculate current node
        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // --------------------------------------------------
    // QUERY
    // --------------------------------------------------

    Node query(
        int node,
        int left,
        int right,
        int queryLeft,
        int queryRight
    ) {

        // Completely inside query range
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        // Completely in right half
        if (queryLeft > mid) {

            return query(
                node * 2 + 1,
                mid + 1,
                right,
                queryLeft,
                queryRight
            );
        }

        // Completely in left half
        if (queryRight <= mid) {

            return query(
                node * 2,
                left,
                mid,
                queryLeft,
                queryRight
            );
        }

        // Query overlaps both halves
        Node leftResult = query(
            node * 2,
            left,
            mid,
            queryLeft,
            queryRight
        );

        Node rightResult = query(
            node * 2 + 1,
            mid + 1,
            right,
            queryLeft,
            queryRight
        );

        return merge(leftResult, rightResult);
    }
}