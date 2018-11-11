package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumClosest16 {
    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int ret = 0;
        if (nums == null || nums.length < 3) {
            return min;
        }

        //****先sort****
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) { //filter repeated
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] + nums[i] < target) {
                    if (Math.abs(nums[left] + nums[right] + nums[i] - target) < min) {
                        min = Math.abs(nums[left] + nums[right] + nums[i] - target);
                        ret = nums[left] + nums[right] + nums[i];
                    }
                    left++;
                } else if (nums[left] + nums[right] + nums[i] > target) {
                    if (Math.abs(nums[left] + nums[right] + nums[i] - target) < min) {
                        min = Math.abs(nums[left] + nums[right] + nums[i] - target);
                        ret = nums[left] + nums[right] + nums[i];
                    }
                    right--;
                } else {
                    return nums[left] + nums[right] + nums[i];
                }
            }
        }

        return ret;
    }

    public int threeSumClosestV1(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int ret = 0;
        if (nums == null || nums.length < 3) {
            return min;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { //filter repeated
                continue;
            }
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }

                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    ret = sum;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        ThreeSumClosest16 obj = new ThreeSumClosest16();
        System.out.println(obj.threeSumClosestV1(new int[]{-1, 2, 1, -4}, 1));
    }
}
