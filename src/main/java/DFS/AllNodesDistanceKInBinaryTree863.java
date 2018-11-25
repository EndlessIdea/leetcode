package DFS;

import java.util.*;

public class AllNodesDistanceKInBinaryTree863 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Stack<TreeNode> stack = new Stack<>();
        dfs(root, target, stack);

        List<Integer> ret = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();
        int distance = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            find(ret, set, node, K, distance);
            set.add(node);
            distance++;
        }

        return ret;
    }

    private void find(List<Integer> ret, Set<TreeNode> set, TreeNode node, int K, int distance) {
        if (node == null || set.contains(node)) {
            return;
        }
        if (K == distance) {
            ret.add(node.val);
            return;
        }
        find(ret, set, node.left, K, distance + 1);
        find(ret, set, node.right, K, distance + 1);
    }

    private boolean dfs(TreeNode root, TreeNode target, Stack<TreeNode> stack) {
        if (root == null) {
            return false;
        }
        stack.push(root);
        if (root == target) {
            return true;
        }
        if (dfs(root.left, target, stack) || dfs(root.right, target, stack)) {
            return true;
        }
        stack.pop();
        return false;
    };

    public static void main(String[] args) {

    }
}
