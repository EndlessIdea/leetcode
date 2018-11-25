package DFS;

public class BinaryTreeMaxPathSum124 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static int maxSum = Integer.MIN_VALUE;
 
    public int maxPathSum(TreeNode root) {
        findPathSum(root);
        return maxSum;
    }

    private int findPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = findPathSum(node.left);
        int right = findPathSum(node.right);
        int singleMax = Math.max(node.val, Math.max(node.val + left, node.val + right));
        int tmpMax = Math.max(singleMax, node.val + left + right);
        if (tmpMax > maxSum) {
            maxSum = tmpMax;
        }
        return singleMax;
    }

    public int maxPath = Integer.MIN_VALUE;

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = helper(root.left);
        int right = helper(root.right);
        maxPath = Math.max(maxPath, root.val + left + right);
        int curMax = Math.max(left, right) + root.val;
        return curMax > 0 ? curMax : 0;
    }

    public void test() {
        BinaryTreeMaxPathSum124 obj = new BinaryTreeMaxPathSum124();
        TreeNode root = new TreeNode(0);
        int ret = obj.maxPathSum(root);
        System.out.println(ret);
    }

    public static void main(String[] args) {
        BinaryTreeMaxPathSum124 obj = new BinaryTreeMaxPathSum124();
        obj.test();
    }
}
