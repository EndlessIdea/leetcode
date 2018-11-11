package TwoPointer;

public class ContainMostWater11 {
    public int maxArea(int[] height) {
        int ret = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            ret = Math.max(Math.min(height[left], height[right]) * (right - left), ret);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        ContainMostWater11 obj = new ContainMostWater11();
        System.out.println(obj.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
