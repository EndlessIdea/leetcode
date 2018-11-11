package BST;

import java.util.Arrays;
import java.util.List;

public class CountSmallerNumbers315 {

    class BSTNode {
        BSTNode left, right;
        int val, dup, leftSum;

        public BSTNode(int val) {
            this.val = val;
            //bug miss dup init
            this.dup = 1;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
//        int[] ret = new int[nums.length];
        Integer[] ret = new Integer[nums.length];

        BSTNode root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insertBST(root, nums[i], ret, i, 0);
        }

        return Arrays.asList(ret);
    }

    private BSTNode insertBST(BSTNode node, int num, Integer[] ret, int i, int preSum) {
        if (node == null) {
            node = new BSTNode(num);
            ret[i] = preSum;
        } else if (num == node.val) {
            node.dup += 1;
            ret[i] = preSum + node.leftSum;
        } else if (num > node.val) {
//            return insertBST(node.right, num, ret, i, node.smaller + node.dup);
            node.right = insertBST(node.right, num, ret, i, node.leftSum + node.dup + preSum);
        } else {
//            return insertBST(node.left, num, ret, i, node.smaller);
            //miss node.leftSum++
            node.leftSum++;
            node.left = insertBST(node.left, num, ret, i, preSum);
        }
        return node;
    }

    public static void main(String[] args) {
        CountSmallerNumbers315 obj = new CountSmallerNumbers315();
        System.out.println(obj.countSmaller(new int[]{5,2,6,1}).toString());
    }
}
