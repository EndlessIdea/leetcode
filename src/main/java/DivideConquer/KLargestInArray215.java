package DivideConquer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KLargestInArray215 {
    public int findKthLargest(int[] nums, int k) {
        int kth = nums.length - k;

//        int i = partion(nums, 0, nums.length - 1);
//        while (i != k) {
//            if (i < k) {
//                i = partion(nums, i + 1, nums.length - 1);
//            } else {
//                i = partion(nums, 0, i - 1);
//            }
//        }
//
//        return nums[i];

        return findKth(nums, 0, nums.length - 1, kth);
    }

    public int partion(int[] nums, int start, int end) {
        if (start >= end) {
            return start;
        }

        int pivot = nums[end];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[left] <= pivot) left++;
            while (left < right && nums[right] >= pivot) right--;
            swap(nums, left, right);
        }
        swap(nums, left, end);
        return left;
    }

    public int findKth(int[] nums, int start, int end, int kth) {
        if (start >= end) {
            return nums[start];
        }

        int pivot = nums[end];
        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[left] <= pivot) left++;
            while (left < right && nums[right] >= pivot) right--;
            swap(nums, left, right);
        }
        swap(nums, left, end);

        if (left == kth) {
            return nums[kth];
        } else if (left < kth) {
            return findKth(nums, left + 1, end, kth);
        } else {
            return findKth(nums, start, left - 1, kth);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        KLargestInArray215 obj = new KLargestInArray215();
        System.out.println(obj.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }
}
