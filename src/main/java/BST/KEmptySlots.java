package BST;

import java.util.TreeSet;

/*
There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input:
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
 */
public class KEmptySlots {
    public static int kEmptySlotsBug(int[] flowers, int k) {
        if (flowers.length < 2 || k < 0) {
            return -1;
        }

        for (int days = 2; days <= flowers.length; days ++) {
            for (int start = 0; start < days; start++) {
                for (int end = start + 1; end < days; end++) {
                    if (Math.abs(flowers[start] - flowers[end]) - 1 == k) {
                        return days;
                    }
                }
            }
        }

        return -1;
    }

    //按天数把每朵花加到有序树中，每当新加入一朵开的花时，查看离他最近的位置与它的距离，
    //如果刚好满足条件，那么也就是第一个满足条件的天数，必然是最小的天数
    public static int KEmptySlotsTreeSet(int[] flowers, int k) {
        if (flowers.length < 2 || k < 0) {
            return -1;
        }

        int ret = -1;
        TreeSet<Integer> positions = new TreeSet();
        for (int i = 0; i < flowers.length; i++) {
            Integer position = flowers[i];
            positions.add(position);
            Integer lower = positions.lower(flowers[i]);
            Integer higher = positions.higher(flowers[i]);
            if ((lower != null && position - lower - 1 == k) ||
                    (higher != null && higher - position - 1 == k)) {
                ret = i + 1;
                break;
            }
        }

        return ret;
    }

    //
    public static int KEmptySlotsSlidingWindow(int[] flowers, int k) {
        if (flowers.length < 2 || k < 0) {
            return -1;
        }

        int[] days = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            days[flowers[i] - 1] = i + 1;//days[i] = j表示位置i的花第j天开
        }

        //在days中找这样一个区间[left,right]，right和left的距离是k，且days区间内所有的值都大于days[left]和days[right]
        //因为left和right表示pos，而right-left=k+1，满足条件中的距离，区间内的值都大于边界，说明区间内的花都在left和right之后开
        //这就表明left和right中较大的那天是符合条件的，在所有这样的区间中，找一个最小的天数
        int left = 0, right = k + 1;
        int ret = Integer.MAX_VALUE;
        while (right < days.length) {
            boolean isFind = true;
            for (int i = left + 1; i < right; i++) {
//                if (days[i] > days[left] && days[i] > days[right]) {//bug 需要所有的都大于两边
                if (days[i] < days[left] || days[i] < days[right]) {
                    isFind = false;
                    left = i;
                    right = i + k + 1;
                    break;
                }
            }
            if (isFind) {
                ret = Math.min(ret, Math.max(days[left], days[right]));
                left = right;
                right = left + k + 1;
            }
            //上面for中如果有不符合条件的位置，则需要立即从那个位置开始寻找，不能到这里再改变left和right
//            left = right;
//            right = left + k + 1;
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    public static void main(String[] args) {
        int[] flowers = {1, 3, 2};
        int ret = KEmptySlotsSlidingWindow(flowers, 1);
        System.out.println(ret);
    }
}
