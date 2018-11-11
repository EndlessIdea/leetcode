package TwoPointer;

public class TrappingRainWater42 {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int ret = 0;
        int left = 0, leftMax = height[left], right = height.length - 1, rightMax = height[right];
        while (left + 1 < right) {
//            while (leftMax <= rightMax) {
            while (leftMax <= rightMax && left + 1 < right) {
                left++;
                if (leftMax <= height[left]) {
                    leftMax = height[left];
                } else {
                    ret += leftMax - height[left];
                }
            }
//            while (rightMax <= leftMax) {
            while (rightMax <= leftMax && left + 1 < right) {
                right--;
                if (rightMax <= height[right]) {
                    rightMax = height[right];
                } else {
                    ret += rightMax - height[right];
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        TrappingRainWater42 obj = new TrappingRainWater42();
        System.out.println(obj.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
