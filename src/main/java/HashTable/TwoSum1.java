package HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                ret[0] = map.get(nums[i]);
                ret[1] = i;
                break;
            }
            map.put(target - nums[i], i);
        }

        return ret;
    }

    public static void main(String[] args) {
        TwoSum1 obj = new TwoSum1();
        System.out.println(Arrays.toString(obj.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
