package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextClosestTime681 {
    int nowMin = 0;
    int maxDiff = Integer.MAX_VALUE;
    String ret = "";

    public String nextClosestTime(String time) {
        nowMin = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }
            set.add(Integer.parseInt(time.substring(i, i+1)));
        }
        if (set.size() == 1) {
            return time;
        }

        List<Integer> list = new ArrayList<Integer>(set);
        dfs("", 0, list);

        return ret;
    }

    public void dfs(String cur, int pos, List<Integer> list) {
        System.out.println(cur);
        if (pos == 4) {
            int curMin = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2, 4));
            int curDiff = curMin > nowMin ? curMin - nowMin : 1440 + curMin - nowMin;
            if (curDiff < maxDiff) {
                maxDiff = curDiff;
                ret = cur.substring(0,2) + ":" + cur.substring(2);
            }
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (pos == 0 && list.get(i) > 2) {
                continue;
            } else if (pos == 1 && Integer.parseInt(cur.substring(0, 1)) * 10 + list.get(i) > 23) {
                continue;
            } else if (pos == 2 && list.get(i) > 5) {
                continue;
            } else if (pos == 3 && Integer.parseInt(cur.substring(2, 3)) * 10 + list.get(i) > 59) {
                continue;
            }

//            cur += String.valueOf(list.get(i));
            dfs(cur + String.valueOf(list.get(i)), pos+1, list);
        }
    }

    public static void main(String[] args) {
        NextClosestTime681 obj = new NextClosestTime681();
        String next = obj.nextClosestTime("21:32");
        System.out.println(next);
    }
}
