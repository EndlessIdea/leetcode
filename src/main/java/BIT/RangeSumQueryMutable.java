package BIT;

/*
307. Range Sum Query - Mutable

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
The update(i, val) function modifies nums by updating the element at index i to val.

Example:
Given nums = [1, 3, 5]
sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
*/


public class RangeSumQueryMutable {
    class BinaryIndexedTree {
        int[] nums;
        int[] tree;

        public BinaryIndexedTree(int[] nums) {
            this.nums = new int[nums.length + 1];
            this.tree = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                update(i, nums[i]);
            }

        }

        public void update(int i, int val) {
            int ni = i + 1;
            int diff = val - this.nums[ni];
            this.nums[ni] = val;
            while (ni < tree.length) {
                tree[ni] += diff;
                ni += ni & (-ni);
            }
        }

        public int sum(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & (-i);
            }
            return sum;
        }
    }

    BinaryIndexedTree t;

    public RangeSumQueryMutable(int[] nums) {
        t = new BinaryIndexedTree(nums);
    }

    public void update(int i, int val) {
        t.update(i, val);
    }

    public int sumRange(int i, int j) {
        return t.sum(j+1) - t.sum(i);
    }
}
