package DivideConquer;

public class ReversePairs493 {
    int[] helper;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        this.helper = new int[nums.length];
        return partition(nums, 0, nums.length - 1);
    }

    private int partition(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int cnt = 0;
        int mid = left + (right - left) / 2;
        cnt += partition(nums, left, mid);
        cnt += partition(nums, mid + 1, right);
        for (int i = left, j = mid + 1; i <= mid; i++) {

            while (j <= right && nums[i] > 2 * nums[j]) {
                j++;
//                cnt++;
            }
            cnt += j - mid - 1;
        }

        merge(nums, left, mid, right);

        return cnt;
    }

    private void merge(int[] nums, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            this.helper[i] = nums[i];
        }

        for (int k = left, i = left, j = mid + 1; i <= mid || j <= right; ) {
            if (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) {
//                    this.helper[k++] = nums[i++];
                    nums[k++] = this.helper[i++];
                } else {
//                    this.helper[k++] = nums[j++];
                    nums[k++] = this.helper[j++];
                }
            } else if (i <= mid) {
//            if (i <= mid) {
//                this.helper[k++] = nums[i++];
                nums[k++] = this.helper[i++];
            } else {
//                this.helper[k++] = nums[j++];
                nums[k++] = this.helper[j++];
            }
        }
//
//        for (int i = left; i < right; i++) {
//            nums[i] = this.helper[i];
//        }
    }

    public static void main(String[] args) {
        ReversePairs493 obj = new ReversePairs493();
        System.out.println(obj.reversePairs(new int[]{1,3,2,3,1}));
    }
}
