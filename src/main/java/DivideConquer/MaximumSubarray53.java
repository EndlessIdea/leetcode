package DivideConquer;

public class MaximumSubarray53 {
    class PartitionResult {
        int leftMax; //m
        int rightMax;
        int max;
        int sum;

        public PartitionResult(int l, int r, int max, int sum) {
            this.leftMax = l;
            this.rightMax = r;
            this.max = max;
            this.sum = sum;
        }
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return partition(nums, 0, nums.length - 1).max;
    }

    private PartitionResult partition(int[] nums, int left, int right) {
        if (left >= right) {
            return new PartitionResult(nums[left],nums[left],nums[left],nums[left]);
        }

        PartitionResult ret = new PartitionResult(0, 0, 0, 0);

        //partition and merge
        int mid = left + (right - left) / 2;
        PartitionResult leftRet = partition(nums, left, mid);
        PartitionResult rightRet = partition(nums, mid+1, right);
        ret.sum = leftRet.sum + rightRet.sum;
        ret.leftMax = Math.max(leftRet.leftMax, leftRet.sum + rightRet.leftMax);
        ret.rightMax = Math.max(rightRet.rightMax, leftRet.rightMax + rightRet.sum);
//        ret.max = Math.max(ret.leftMax, ret.rightMax, leftRet.max, rightRet.max);
//        ret.max = Math.max(leftRet.rightMax + rightRet.leftMax, Math.max(ret.leftMax, ret.rightMax));
        ret.max = Math.max(leftRet.rightMax + rightRet.leftMax, Math.max(leftRet.max, rightRet.max));

        return ret;
    }

    public static void main(String[] args) {
        MaximumSubarray53 obj = new MaximumSubarray53();
        System.out.println(obj.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
