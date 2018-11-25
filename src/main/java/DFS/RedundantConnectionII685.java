package DFS;

import java.util.*;

public class RedundantConnectionII685 {
    class OrbitResult {
        int node;
        Set<Integer> seen;
        OrbitResult(int n, Set<Integer> s) {
            node = n;
            seen = s;
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        Map<Integer, Integer> parent = new HashMap();
        List<int[]> candidates = new ArrayList();
        for (int[] edge: edges) {
            if (parent.containsKey(edge[1])) {
                candidates.add(new int[]{parent.get(edge[1]), edge[1]});
                candidates.add(edge);
            } else {
                parent.put(edge[1], edge[0]);
            }
        }

        int root = orbit(1, parent).node;
        if (candidates.isEmpty()) {
            Set<Integer> cycle = orbit(root, parent).seen;
            int[] ans = new int[]{0, 0};
            for (int[] edge: edges) {
                if (cycle.contains(edge[0]) && cycle.contains(edge[1])) {
                    ans = edge;
                }
            }
            return ans;
        }

        Map<Integer, List<Integer>> children = new HashMap();
        for (int v: parent.keySet()) {
            int pv = parent.get(v);
            if (!children.containsKey(pv))
                children.put(pv, new ArrayList<Integer>());
            children.get(pv).add(v);
        }

        boolean[] seen = new boolean[N+1];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.add(root);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!seen[node]) {
                seen[node] = true;
                if (children.containsKey(node)) {
                    for (int c: children.get(node))
                        stack.push(c);
                }
            }
        }
        for (boolean b: seen) if (!b)
            return candidates.get(0);
        return candidates.get(1);
    }

    public OrbitResult orbit(int node, Map<Integer, Integer> parent) {
        Set<Integer> seen = new HashSet();
        while (parent.containsKey(node) && !seen.contains(node)) {
            seen.add(node);
            node = parent.get(node);
        }
        return new OrbitResult(node, seen);
    }

    public int[] findRedundantDirectedConnection1(int[][] edges) {
        int size = edges.length;
        int[] parent = new int[size];
        List<int[]> candidates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int child = edges[i][1];
            int root = edges[i][0];
            if (parent[child] == 0) {
                parent[child] = root;
            } else {
                candidates.add(new int[]{parent[child], child});
                candidates.add(new int[]{edges[i][0], edges[i][1]});
                edges[i][1] = 0; //cut this path
            }
        }

        //union find前，先重置parent
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < size; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1];
            int father = findParent(parent, edges[i][0]);
            if (father == child) {
                if (candidates.size() == 0) {
                    return edges[i];
                } else {
                    return candidates.get(0);
                }
            }
            parent[child] = father;
        }

        return candidates.get(1);
    }

    private int findParent(int[] parent, int child) {
//        if (child == parent[child]) {
//            return child;
//        }
//        parent[child] = findParent(parent, parent[child]);
        while (parent[child] != child) {
            parent[child] = parent[parent[child]];
            child = parent[child];
        }
        return parent[child];
    }

    public static void main(String[] args) {

    }
}
