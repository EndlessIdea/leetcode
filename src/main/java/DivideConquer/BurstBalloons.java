package DivideConquer;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] balloons = new int[n + 2];
        for (int i = 0; i < n; i++) {
            balloons[i+1] = nums[i];
        }
        balloons[0] = 1;
        balloons[n+1] = 1;
        int[][] memory = new int[n+2][n+2];

        return partition(balloons, 1, n, memory);
    }

    private int partition(int[] balloons, int left, int right, int[][] memory) {
        if (memory[left][right] != 0) {
            return memory[left][right];
        }
        //left == right的情况通过下方的循环也可以得出
//        if (left == right) {
//            memory[left][right] = balloons[left-1] * balloons[left] * balloons[left+1];
//            return memory[left][right];
//        }

        int max = 0;
        for (int i = left; i <= right; i++) {
            int sum = 0;
            if (i > left) {
                sum += partition(balloons, left, i - 1, memory);
            }
            if (i < right) {
                sum += partition(balloons, i+1, right, memory);
            }
            sum += balloons[left-1] * balloons[i] * balloons[right+1];
            max = Math.max(max, sum);
        }
        memory[left][right] = max;

        return max;
    }

    public static void main(String[] args) {
        BurstBalloons obj = new BurstBalloons();
        System.out.println(obj.maxCoins(new int[]{3,1,5,8}));
    }
}
