package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeafSimilarTrees872 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = search(root1);
        List<Integer> l2 = search(root2);
        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) != l2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> search(TreeNode root) {
        List<Integer> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }
        if (root.left == null && root.right == null) {
            ret.add(root.val);
            return ret;
        }
        List<Integer> left = search(root.left);
        List<Integer> right = search(root.right);
        left.addAll(right);
        return left;
    }

    public static void main(String[] args) {

    }
}
