package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWaterII407 {
    class Cell {
        int row, col, height;
        public Cell(int row, int col, int h) {
            this.row = row;
            this.col = col;
            this.height = h;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) {
            return 0;
        }
        int m = heightMap.length, n = heightMap[0].length;
        int[] used = new int[m*n];
        PriorityQueue<Cell> cells = new PriorityQueue<>((a, b) -> {return Integer.compare(a.height, b.height);});
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (used[i*n + j] == 0) {
                        used[i*n + j] = 1;
                        cells.offer(new Cell(i, j, heightMap[i][j]));
                    }
                }
            }
        }

        int[][] d = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        int max = 0;
        while (!cells.isEmpty()) {
            Cell cur = cells.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.row + d[i][0];
                int ny = cur.col + d[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || used[nx*n + ny] == 1) {
                    continue;
                }
                if (heightMap[nx][ny] < cur.height) {
                    max += cur.height - heightMap[nx][ny];
                }
                int curH = Math.max(cur.height, heightMap[nx][ny]);
                used[nx*n + ny] = 1;
                cells.offer(new Cell(nx, ny, curH));
            }
        }

        return max;
    }

    public static void main(String[] args) {

    }
}
