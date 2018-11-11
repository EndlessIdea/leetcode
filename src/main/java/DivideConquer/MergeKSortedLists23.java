package DivideConquer;

import java.util.List;

public class MergeKSortedLists23 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return partion(lists, 0, lists.length - 1);
    }

    public ListNode partion(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

//        ListNode l1 = partion(lists, 0, lists.length / 2);
//        ListNode l2 = partion(lists, lists.length / 2 + 1, lists.length - 1);
//        ListNode l1 = partion(lists, start, lists.length / 2);
//        ListNode l2 = partion(lists, lists.length / 2 + 1, end);
        ListNode l1 = partion(lists, start, start + (end-start)/2);
        ListNode l2 = partion(lists, start + (end-start)/2 + 1, end);

        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    public void test() {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(3);
        ListNode h2 = new ListNode(2);
        MergeKSortedLists23 obj = new MergeKSortedLists23();
        ListNode[] lists = new ListNode[]{h1, h2};
        ListNode ret = obj.mergeKLists(lists);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
    }
 
    public static void main(String[] args) {
        MergeKSortedLists23 t = new MergeKSortedLists23();
        t.test();
    }
}
