package DFS;

import java.util.ArrayList;
import java.util.List;

public class GraphValidTree261 {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adjacentList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacentList.add(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacentList.get(edge[0]).add(edge[1]);
            adjacentList.get(edge[1]).add(edge[0]);
        }

        boolean[] seen = new boolean[n];
        if (!dfs(adjacentList, seen, 0, -1)) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> adjacentList, boolean[] seen, int cur, int pre) {
        if (seen[cur]) {
            return false;
        }

        seen[cur] = true;
        List<Integer> adjacent = adjacentList.get(cur);
        for (int i = 0; i < adjacent.size(); i++) {
            if (adjacent.get(i) == pre) {
                continue;
            }
            if (!dfs(adjacentList, seen, adjacent.get(i), cur)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
