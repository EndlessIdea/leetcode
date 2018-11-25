package DFS;

public class ConvertSortedArrayToBST108 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return partition(nums, 0, nums.length - 1);
    }

    private TreeNode partition(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        int middle = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = partition(nums, start, middle - 1);
        root.right = partition(nums, middle + 1, end);
        return root;
    }

    public static void main(String[] args) {

    }
}
