package DFS;

import java.util.ArrayList;
import java.util.List;

public class Game24_679 {

    private double target = 24.0;
    private double limit = 1e-6;

    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length != 4) {
            return false;
        }

        List<Double> input = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            input.add((double)nums[i]);
        }

        //dfs judge
        return dfsBetter(input);
    }

    private boolean dfs(List<Double> input) {
        if (input.size() == 1) {
//            return input.get(0) - target <= limit;
            return Math.abs(input.get(0) - target) <= limit;
        }

        boolean ret = false;
        //bug for uses 4 first, should use size
        int size = input.size();
        for (int m = 0; m < size; m++) {
            for (int n = 0; n < size; n++) {
                if (n == m) {
                    continue;
                }
                List<Double> nextInput = new ArrayList<>();
                for (int k = 0; k < size; k++) {
                    if (k != m && k != n) {
                        nextInput.add(input.get(k));
                    }
                }
                Double first = input.get(m);
                Double second = input.get(n);
                for (int i = 0; i < 6; i++) {
                    switch (i) {
                        case 0:
                            nextInput.add(first + second);
                            ret = dfs(nextInput);
                            break;
                        case 1:
                            nextInput.add(first * second);
                            ret = dfs(nextInput);
                            break;
                        case 2:
                            nextInput.add(first - second);
                            ret = dfs(nextInput);
                            break;
                        case 3:
                            nextInput.add(second - first);
                            ret = dfs(nextInput);
                            break;
                        case 4:
                            if (second - 0 > limit) {
                                nextInput.add(first / second);
                                ret = dfs(nextInput);
                            }
                            break;
                        case 5:
                            if (first - 0 > limit) {
                                nextInput.add(second / first);
                                ret = dfs(nextInput);
                            }
                            break;
                    }
                    if (nextInput.size() > 0) {
                        nextInput.remove(nextInput.size()-1);
                    }
                    if (ret) {
                        return ret;
                    }
                }

                ret = false;
            }
        }

        return ret;
    }

    private boolean dfsBetter(List<Double> input) {
        if (input.size() == 1) {
            return Math.abs(input.get(0) - target) < limit;
        }

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (i == j) {
                    continue;
                }

                Double first = input.get(i);
                Double second = input.get(j);

                List<Double> nextInput = new ArrayList<>();
                for (int k = 0; k < input.size(); k++) {
                    if (k != i && k != j) {
                        nextInput.add(input.get(k));
                    }
                }

                for (int k = 0; k < 4; k++) {
                    switch (k) {
                        case 0:
                            nextInput.add(first + second);
                            break;
                        case 1:
                            nextInput.add(first - second);
                            break;
                        case 2:
                            nextInput.add(first * second);
                            break;
                        case 3:
                            if (Math.abs(second - 0) <= limit) {
                                continue;
                            }
                            nextInput.add(first / second);
                            break;
                    }
                    if (dfsBetter(nextInput)) {
                        return true;
                    }
                    nextInput.remove(nextInput.size() - 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Game24_679 obj = new Game24_679();
        System.out.println(obj.judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(obj.judgePoint24(new int[]{1, 2, 1, 2}));
    }
}
