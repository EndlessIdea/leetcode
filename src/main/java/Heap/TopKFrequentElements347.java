package Heap;

import java.util.*;

public class TopKFrequentElements347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {return b[1] - a[1];});
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int[] tmp = queue.poll();
            ret.add(tmp[0]);
        }
        return ret;
    }

    public static void main(String[] args) {

    }
}
