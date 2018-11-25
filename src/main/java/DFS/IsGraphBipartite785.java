package DFS;

import java.util.Arrays;

public class IsGraphBipartite785 {
    public boolean isBipartite(int[][] graph) {
        //check param
        if (graph == null || graph.length == 0) {
            return false;
        }

        //color labels, 0 not painted, 1 red, -1 blue
        int[] colors = new int[graph.length];
        Arrays.fill(colors, 0);

        //dfs ,paint color for each point,
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != 0) {
                continue;
            }
            if (!paint(graph, i, 1, colors)) {
                return false;
            }
        }

        return true;
    }

    private boolean paint(int[][] graph, int point, int color, int[] colors) {
        colors[point] = color;
        int nextColor = 0 - color;
        for (int i = 0; i < graph[point].length; i++) {
            int nextPoint = graph[point][i];
//            if (colors[nextPoint] != 0 && colors[nextPoint] != nextColor) {
//                return false;
//            }
            if (colors[nextPoint] != 0) {
                if (colors[nextPoint] != nextColor) {
                    return false;
                } else {
                    continue;
                }

            }
//            paint(graph, nextPoint, nextColor, colors);
            if (!paint(graph, nextPoint, nextColor, colors)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] graph1 = new int[][] {
                {1,3},
                {0,2},
                {1,3},
                {0,2}
        };
        int[][] graph2 = new int[][] {
                {1,2,3},
                {0,2},
                {0,1,3},
                {0,2}
        };
        IsGraphBipartite785 obj = new IsGraphBipartite785();
        System.out.println(obj.isBipartite(graph1));
        System.out.println(obj.isBipartite(graph2));
    }
}
