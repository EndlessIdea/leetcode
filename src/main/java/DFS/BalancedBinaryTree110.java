package DFS;

public class BalancedBinaryTree110 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Result {
        boolean isBalanced;
        int height;
        public Result(int h, boolean b) {
            height = h;
            isBalanced = b;
        }
    }
 
    public boolean isBalanced(TreeNode root) {
        return dfs(root).isBalanced;
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, true);
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
//        int height = Math.max(left.height, right.height);
        int height = 1 + Math.max(left.height, right.height);
        return new Result(height, isBalanced);
    }

    public static void main(String[] args) {

    }
}
