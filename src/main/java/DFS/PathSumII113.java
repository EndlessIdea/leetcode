package DFS;

import java.util.ArrayList;
import java.util.List;

public class PathSumII113 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int sum = 0;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.sum = sum;
        List<List<Integer>> ret = new ArrayList<>();
        dfs(root, 0, ret, new ArrayList<Integer>());
        return ret;
    }

    private void dfs(TreeNode root, int curSum, List<List<Integer>> ret, ArrayList<Integer> curList) {
        if (root == null) {
            return;
        }
        curList.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val + curSum == this.sum) {
//                ret.add(new ArrayList<>(curList));
                ret.add(curList);
                curList.remove(curList.size() - 1);
                return;
            }
        }
        dfs(root.left, curSum + root.val, ret, curList);
        dfs(root.right, curSum + root.val, ret, curList);
        curList.remove(curList.size() - 1);
    }

    public static void main(String[] args) {

    }
}
