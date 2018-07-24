package BST;

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
    public int kEmptySlots(int[] flowers, int k) {
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
}
