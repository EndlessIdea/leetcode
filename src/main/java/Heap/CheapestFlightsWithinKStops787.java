package Heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops787 {
    static int minPrice = Integer.MAX_VALUE;

    class Trip {
        int city;
        int price;
        public Trip(int city, int price) {
            this.city = city;
            this.price = price;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<Trip>> paths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            paths.add(i, new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            paths.get(flights[i][0]).add(new Trip(flights[i][1], flights[i][2]));
        }

        dfs(paths, src, dst, K+1, 0);
        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }

    private void dfs(List<List<Trip>> paths, int cur, int end, int stops, int money) {
//        if (stops == 0) {
//            if (cur == end) {
//                minPrice = Math.min(money, minPrice);
//            }
//            return;
//        }
        if (cur == end) {
            minPrice = Math.min(money, minPrice);
            return;
        }
        if (stops == 0) {
            return;
        }

        for (int i = 0; i < paths.get(cur).size(); i++) {
            Trip t = paths.get(cur).get(i);
            if (money + t.price > minPrice) continue; //prunning
            dfs(paths, t.city, end, stops - 1, money + t.price);
        }
    }

    public int findCheapestPriceBfs(int n, int[][] flights, int src, int dst, int K) {
        int minPrice = Integer.MAX_VALUE;
        List<List<int[]>> paths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            paths.add(i, new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            paths.get(flights[i][0]).add(new int[]{flights[i][1], flights[i][2]});
        }

        Queue<int[]> q = new LinkedList<>();
        int steps = 0;
        q.offer(new int[]{src, 0});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if (cur[0] == dst) {
                    minPrice = Math.min(minPrice, cur[1]);
                    break;
                }
                for (int j = 0; j < paths.get(cur[0]).size(); j++) {
                    int[] next = paths.get(cur[0]).get(j);
                    if (cur[1] + next[1] > minPrice) continue;
                    q.offer(new int[]{next[0], cur[1] + next[1]});
                }
            }
            if (steps++ > K) break;
        }

        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops787 obj = new CheapestFlightsWithinKStops787();
        int[][] flights = new int[][] {
                {0,1,100},{1,2,100},{0,2,500}
        };
        int min = obj.findCheapestPriceBfs(3, flights, 0, 2, 1);
        System.out.println(min);
    }
}
