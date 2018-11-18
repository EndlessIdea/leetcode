package UnionFind;

import java.util.HashSet;

public class NumberOfIslands200 {
    private int row, col;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        HashSet<Integer> island = new HashSet<>();
        row = grid.length;
        col = grid[0].length;
        int[] parent = new int[row * col];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    int cor = getCor(i, j);
                    parent[cor] = cor;
                    island.add(cor);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (i > 0 && grid[i-1][j] == '1') {
                        union(parent, getCor(i-1, j), getCor(i, j));
                        island.remove(getCor(i, j));
                    }
                    if (j > 0 && grid[i][j-1] == '1') {
                        union(parent, getCor(i, j-1), getCor(i, j));
                        island.remove(getCor(i, j));
                    }
                }
            }
        }

        return island.size();
    }

    private int getCor(int i, int j) {
        return i * col + j;
    }

    private int findParent(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        int root = findParent(parent, parent[i]);
        parent[i] = root;
        return root;
    }

    private void union(int[] parent, int a, int b) {
        int pa = findParent(parent, a);
        int pb = findParent(parent, b);
        parent[pb] = pa;
    }

    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx; rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }


    public static void main(String[] args) {
        char[][] grid = new char[][] {
                {'1', '1', '0', '0' , '0'},
                {'1', '1', '0', '0' , '0'},
                {'0', '0', '1', '0' , '0'},
                {'0', '0', '0', '1' , '1'},
        };

        NumberOfIslands200 obj = new NumberOfIslands200();
        int island = obj.numIslands(grid);
        System.out.println(island);
    }
}
