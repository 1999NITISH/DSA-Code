/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstIdx = -1;   // index of the first critical point found
        int prevIdx = -1;    // index of the most recently found critical point
        int minDist = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int idx = 1; // curr's index (0-based, head is index 0)

        while (curr.next != null) {
            boolean isCritical =
                (curr.val > prev.val && curr.val > curr.next.val) || // local maxima
                (curr.val < prev.val && curr.val < curr.next.val);   // local minima

            if (isCritical) {
                if (firstIdx == -1) {
                    firstIdx = idx; // remember the very first critical point
                } else {
                    minDist = Math.min(minDist, idx - prevIdx); // consecutive gap
                }
                prevIdx = idx; // update most recent critical point
            }

            prev = curr;
            curr = curr.next;
            idx++;
        }

        // Fewer than 2 critical points found.
        if (firstIdx == -1 || firstIdx == prevIdx) {
            return new int[]{-1, -1};
        }

        int maxDist = prevIdx - firstIdx; // distance between first and last critical point
        return new int[]{minDist, maxDist};
    }
}