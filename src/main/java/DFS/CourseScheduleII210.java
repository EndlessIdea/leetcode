package DFS;

import java.util.*;

public class CourseScheduleII210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //check
        int[] ret = new int[numCourses];
        Arrays.fill(ret, -1);

        //construct graph and conditionNum
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }
        int[] conditionNum = new int[numCourses];
        for (int[] require : prerequisites) {
            int pre = require[1];
            int after = require[0];
            conditionNum[after] += 1;
            graph.get(pre).add(after);
        }

        //use set to store the heads
        Queue<Integer> heads = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (conditionNum[i] == 0) {
                heads.add(i);
            }
        }

        //while loop, find head then dfs
        int index = 0;
        while (!heads.isEmpty()) {
            for (int i = 0; i < heads.size(); i++) {
                int course = heads.poll();
                ret[index++] = course;
                List<Integer> children = graph.get(course);
                for (int j = 0; j < children.size(); j++) {
                    int nextCourse = children.get(j);
                    conditionNum[nextCourse] -= 1;
                    if (conditionNum[nextCourse] == 0) {
                        heads.add(nextCourse);
                    }
                }
            }
        }

        //check whether ret equas to numCourses
        for (int course : ret) {
            if (course == -1) {
                return new int[0];
            }
        }

        return ret;
    }

    private void dfs(List<List<Integer>> graph, int course, int[] conditionNum, Set<Integer> set) {

    }

    public static void main(String[] args) {
        CourseScheduleII210 obj = new CourseScheduleII210();
        int[][] courses = new int[][] {
                {1,0},
                {2,0},
                {3,1},
                {3,2},
        };
        int[] ret = obj.findOrder(4, courses);
        System.out.println(Arrays.toString(ret));
    }
}
