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
import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Min-heap comparing nodes by value
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Add the head of each non-null list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // While heap is not empty, pop the smallest node
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            tail.next = curr;
            tail = tail.next;

            // Push the next node of the current node, if it exists
            if (curr.next != null) {
                pq.add(curr.next);
            }
        }

        return dummy.next;
    }
}
