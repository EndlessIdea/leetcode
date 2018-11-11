package DivideConquer;

public class MergeTwoSortedArrays4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int mid1 = (m + n + 1) / 2;
        int mid2 = (m + n + 2) / 2;

        return (findKth(nums1, 0, nums2, 0, mid1) + findKth(nums1, 0, nums2, 0, mid2)) / 2.0;
    }

    public int findKth(int[] nums1, int index1, int[] nums2, int index2, int k) {
        if (index1 >= nums1.length) return nums2[index2 + k - 1];
        if (index2 >= nums2.length) return nums1[index1 + k - 1];
        //bug: miss recursion end
        if (k == 1) return Math.min(nums1[index1], nums2[index2]);

//        int val1 = index1 + k/2 - 1 >= nums1.length ? Integer.MIN_VALUE : nums1[index1 + k/2 - 1];
//        int val2 = index2 + k/2 - 1 >= nums2.length ? Integer.MIN_VALUE : nums2[index2 + k/2 - 1];
        int val1 = index1 + k/2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[index1 + k/2 - 1];
        int val2 = index2 + k/2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[index2 + k/2 - 1];
        if (val1 < val2) {
            return findKth(nums1, index1 + k/2, nums2, index2, k - k/2);
        } else {
            return findKth(nums1, index1, nums2, index2 + k/2, k - k/2);
        }
    }

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
//        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
//    }
//    int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
//        if (i >= nums1.length) return nums2[j + k - 1];
//        if (j >= nums2.length) return nums1[i + k - 1];
//        if (k == 1) return Math.min(nums1[i], nums2[j]);
//        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
//        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
//        if (midVal1 < midVal2) {
//            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
//        } else {
//            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
//        }
//    }

    public static void main(String[] args) {
        MergeTwoSortedArrays4 obj = new MergeTwoSortedArrays4();
        System.out.println(obj.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
//        System.out.println(obj.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}
