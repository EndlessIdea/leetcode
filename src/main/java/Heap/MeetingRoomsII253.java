package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII253 {
    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    class Point {
        int time;
        int mark;
        public Point(int t, int m) {
            time = t;
            mark = m;
        }
    }
 
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if (a.time == b.time) {
                    return a.mark - b.mark;
                }
                return a.time - b.time;
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            queue.offer(new Point(intervals[i].start, 1));
            queue.offer(new Point(intervals[i].end, -1));
        }

        int max = 0;
        int cur = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            cur += p.mark == 1 ? 1 : -1;
            max = Math.max(max, cur);
        }

        return max;
    }
    
    public static void main(String[] args) {

    }
}
