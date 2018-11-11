package TwoPointer;

import java.util.*;

//904
public class FruitIntoBaskets {
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }

        int ret = 0;
        for (int left = 0; left < tree.length; left++) {
            Set<Integer> types = new HashSet<Integer>();
            int cur = 0;
            for (int right = left; right < tree.length; right++) {
                types.add(tree[right]);
                if (types.size() < 3) {
                    cur++;
                } else {
//                    ret = Math.max(ret, cur);
                    break;
                }
            }
            ret = Math.max(ret, cur);
        }

        return ret;
    }

    public int totalFruit1(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }

        int ret = 1;
        int left = 0, right = 1;
        Map<Integer, Integer> types = new HashMap<Integer, Integer>();
        types.put(tree[left], 1);
        while (left < right && right < tree.length) {
            types.put(tree[right], types.getOrDefault(tree[right], 0) + 1);
            if (types.size() < 3) {
                right++;
                ret = Math.max(ret, right - left);
            } else {
                while (types.size() > 2 && left < right) {
                    types.put(tree[left], types.get(tree[left]) - 1);
                    if (types.get(tree[left]) == 0) {
                        types.remove(tree[left]);
                    }
                    left++;
                }
                right++;//forget to right++
            }
        }

        return ret;
    }

    public int totalFruit2(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }

        int ret = 1;
        int left = 0, right = 1;
        Map<Integer, Integer> types = new HashMap<Integer, Integer>();
        types.put(tree[left], 1);
        while (left < right && right < tree.length) {
            types.put(tree[right], types.getOrDefault(tree[right], 0) + 1);
            System.out.println(types);
            while (types.size() > 2 && left < right) {
                types.put(tree[left], types.get(tree[left]) - 1);
                if (types.get(tree[left]) == 0) {
                    types.remove(tree[left]);
                }
                left++;
            }
            right++;
            ret = Math.max(ret, right - left);
        }

        return ret;
    }

    public int totalFruit3(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }

        int ret = 1;
        int left = 0;
        Map<Integer, Integer> types = new HashMap<>();
        for (int right = 0; right < tree.length; right++) {
            types.put(tree[right], types.getOrDefault(tree[right], 0) + 1);
            while (types.size() > 2) {
                types.put(tree[left], types.get(tree[left]) - 1);
                if (types.get(tree[left]) == 0) {
                    types.remove(tree[left]);
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
        }

        return ret;
    }

    public int totalFruit4(int[] tree) {
        int lastPos = 0, ret = 0;
        HashMap<Integer, Integer> lastPosOfType = new HashMap<>();
        for (int i = 0; i < tree.length; i++) {
            lastPosOfType.put(tree[i], i);
            if (lastPosOfType.size() > 2) {
                lastPos = Collections.min(lastPosOfType.values()) + 1;
                lastPosOfType.remove(tree[lastPos - 1]); //forget to remove
            }
            System.out.println(lastPosOfType);
            System.out.println(lastPos);
            ret = Math.max(ret, i - lastPos + 1);
        }
        return ret;
    }

    public static void main(String[] args) {
        FruitIntoBaskets obj = new FruitIntoBaskets();
        int[] tree = new int[]{5,9,0,9,6,9,6,9,9,9};
        System.out.println(obj.totalFruit4(tree));
    }
}
