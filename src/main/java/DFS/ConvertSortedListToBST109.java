package DFS;

import java.util.List;

public class ConvertSortedListToBST109 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedListToBST(ListNode head) {

        return partition(head);
    }

    private ListNode findMedium(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return slow;
    }

    private TreeNode partition(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode medium = findMedium(head);
        TreeNode root = new TreeNode(medium.val);
        TreeNode left = partition(head);
        TreeNode right = partition(medium.next);
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {

    }
}
