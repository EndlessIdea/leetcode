package DivideConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheSkyline218 {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<>();
        if (buildings.length == 0) {
            return ret;
        }

        return partition(buildings, 0, buildings.length - 1);
    }

    private List<int[]> partition(int[][] buildings, int left, int right) {
        if (left >= right) {
            List<int[]> ret = new ArrayList<>();
            ret.add(new int[]{buildings[left][0], buildings[left][2]});
            ret.add(new int[]{buildings[left][1], 0});
            return ret;
        } else {
            int mid = left + (right - left) / 2;
            return merge(partition(buildings, left, mid), partition(buildings, mid+1, right));
        }
    }

    private List<int[]> merge(List<int[]> l1, List<int[]> l2) {
        List<int[]> ret = new ArrayList<>();
        int h1 = 0, h2 = 0;
        while (l1.size() > 0 && l2.size() > 0) {
            int[] n1 = l1.get(0);
            int[] n2 = l2.get(0);
            int pos1 = n1[0];
            int pos2 = n2[0];
            int x = 0, h = 0;
            if (pos1 < pos2) {
                x = pos1;
                h1 = n1[1];
                l1.remove(0);
            } else if (pos1 > pos2) {
                x = pos2;
                h2 = n2[1];
                l2.remove(0);
            } else {
                x = pos1;
                h1 = n1[1];
                h2 = n2[1];
                l1.remove(0);
                l2.remove(0);
            }
            h = Math.max(h1, h2);
            if (ret.size() == 0 || h != ret.get(ret.size()-1)[1]) {
                ret.add(new int[]{x, h});
            }
        }

        ret.addAll(l1);
        ret.addAll(l2);
        return ret;
    }

    public static void main(String[] args) {
        TheSkyline218 obj = new TheSkyline218();
        int[][] buildings = new int[][]{
                {2,9,10}, {3,7,15}, {5,12, 12}, {15, 20, 10}, {19, 24, 8},
        };
        List<int[]> ret = obj.getSkyline(buildings);
        for (int i = 0; i < ret.size(); i++) {
            System.out.println(Arrays.toString(ret.get(i)));
        }
    }
}
