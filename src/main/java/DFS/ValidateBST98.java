package DFS;

public class ValidateBST98 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Ret {
        public int max = Integer.MIN_VALUE;
        public int min = Integer.MAX_VALUE;
        public boolean isValid = true;
    }

    public boolean isValidBST(TreeNode root) {
        return partition(root).isValid;
    }

    private Ret partition(TreeNode root) {
        Ret ret = new Ret();
        if (root == null) {
            return ret;
        }

        Ret left = partition(root.left);
        Ret right = partition(root.right);
        if (!left.isValid || !right.isValid) {
            ret.isValid = false;
            return ret;
        }

        ret.isValid = left.max < root.val && right.min > root.val;
        ret.max = Math.max(root.val, Math.max(left.max, right.max));
        ret.min = Math.min(root.val, Math.min(left.min, right.min));
        return ret;
    }

    public boolean isValidBST1(TreeNode root) {
        return dfs(root, null, null);
    }

    private boolean dfs(TreeNode root, Integer min, Integer max) {
        if (root ==  null) {
            return true;
        }

        if ((min != null && root.val < min) || (max != null && root.val > max)) {
            return false;
        }

//        return dfs(root.left, null, root.val) && dfs(root.right, root.val, null);
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    public static void main(String[] args) {

    }
}
