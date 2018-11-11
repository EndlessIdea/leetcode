package TwoPointer;

public class MaxConsecutiveOnesB487 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ret = 0;
        int cur = 0;
        int right = 0, lastOnePos = -1;
        while (right < nums.length) {
            if (nums[right] == 1) {
                cur++;
                ret = Math.max(ret, cur);
            } else {
                if (lastOnePos == -1) {
                    lastOnePos = right;
                    cur++;
                    ret = Math.max(ret, cur);
                } else {
                    cur = right - lastOnePos;
                    ret = Math.max(ret, cur);
                    lastOnePos = right;
                }
            }

            right++;
        }

        return ret;
    }

    public int findMaxConsecutiveOnes1(int[] nums) {
        int ret = 0;
        int right = 0, left = 0, zeroPos = -1;
        while (right < nums.length) {
            if (nums[right] == 0) {
                left = zeroPos + 1;
                zeroPos = right;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesB487 obj = new MaxConsecutiveOnesB487();
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{1,0,1,1,0}));
    }
}
