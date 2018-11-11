package TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rets = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return rets;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) { //filter repeated
                continue;
            }
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
//                    List<Integer> ret = new ArrayList<Integer>();
//                    ret.add(nums[i]);
//                    ret.add(nums[left]);
//                    ret.add(nums[right]);
                    rets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left > right && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;
                }
            }
        }

        return rets;
    }

    public static void main(String[] args) {
        ThreeSum15 obj = new ThreeSum15();
        List<List<Integer>> rets = obj.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(rets);
    }
}
