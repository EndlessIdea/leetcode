package DFS;

public class TheMaze490 {
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{1, 0, -1, 0};
    int row = 0;
    int col = 0;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return false;
        }

        row = maze.length;
        col = maze[0].length;
        int[][] memory = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                memory[i][j] = 0;
            }
        }

        return dfs(maze, memory, start[0], start[1], destination);
    }

    public boolean dfs(int[][] maze, int[][] memory, int x, int y, int[] destination) {
        if (memory[x][y] == -1) {
            return false;
        }
        if (x == destination[0] && y == destination[1]) {
            return true;
        }

        memory[x][y] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = x, ny = y;
//            for (int tx = nx + dx[i], ty = ny + dy[i]; tx >= 0 && tx < row && ty >= 0 && ty < col && maze[tx][ty] == 0; ) {
            for (int tx = nx + dx[i], ty = ny + dy[i]; tx >= 0 && tx < row && ty >= 0 && ty < col && maze[tx][ty] == 0; ) {
                nx = tx;
                tx = nx + dx[i];
                ny = ty;
                ty = ny + dy[i];
            }
            if (nx == x && ny == y) {
                continue;
            }
            if (dfs(maze, memory, nx, ny, destination)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TheMaze490 obj = new TheMaze490();
        int[][] maze = new int[][] {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start = new int[]{0,4};
        int[] dest = new int[]{3,2};
        boolean ret = obj.hasPath(maze, start, dest);
        System.out.println(ret);
    }
}
