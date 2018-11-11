package HashTable;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs447 {
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;

        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                Integer distance = dx*dx + dy*dy;
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (int val : map.values()) {
                if (val > 1) {
                    ret += val * (val-1);
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {

    }
}
