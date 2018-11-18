package DFS;

import java.util.*;

public class NumberDistinctIslands694 {
    private int[][] seen;
    int row;
    int col;

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        row = grid.length;
        col = grid[0].length;
        seen = new int[row][col];
        Set<String> shapes = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && seen[i][j] == 0) {
                    StringBuilder shape = new StringBuilder();
                    helper(i, j, i, j, grid, shape);
                    System.out.println("new shape");
                    System.out.println(shape);
                    shapes.add(shape.toString());
                }
            }
        }

        return shapes.size();
    }

    private void helper(int i, int j, int i0, int j0, int[][] grid, StringBuilder shape) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0 || seen[i][j] == 1) {
            return;
        }

        seen[i][j] = 1;
        shape.append(i-i0);
        shape.append("_");
        shape.append(j-j0);
        shape.append("||");
        helper(i, j + 1, i0, j0, grid, shape);
        helper(i + 1, j, i0, j0, grid, shape);
        helper(i, j - 1, i0, j0, grid, shape);
        helper(i - 1, j, i0, j0, grid, shape);
    }

    public static void main(String[] args) {
        NumberDistinctIslands694 obj = new NumberDistinctIslands694();
        int[][] grid = new int[][] {
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1},
        };
        System.out.println(obj.numDistinctIslands(grid));
    }
}
