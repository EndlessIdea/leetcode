package BIT;

public class BinaryIndexedTree {
    int[] tree;

    public BinaryIndexedTree(int[] nums) {
        tree = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            update(i + 1, nums[i]);
        }

    }

    public void update(int i, int val) {
        while (i < tree.length) {
            tree[i] += val;
            i += i & (-i);
        }
    }

    public void sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);
        }
    }
}
