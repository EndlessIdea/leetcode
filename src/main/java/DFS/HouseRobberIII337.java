package DFS;

public class HouseRobberIII337 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Ret {
        int curMax;
        int subMax;
    }

    public int rob(TreeNode root) {
        return search(root);
    }

    private int search(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sub = 0;
        if (root.left != null) {
            sub += search(root.left.left) + search(root.left.right);
        }
        if (root.right != null) {
            sub += search(root.right.left) + search(root.right.right);
        }
        return Math.max(root.val + sub, search(root.left) + search(root.right));
    }

    public static void main(String[] args) {

    }
}
