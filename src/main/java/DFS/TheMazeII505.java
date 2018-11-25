package DFS;

public class TheMazeII505 {
    int minDistance = Integer.MAX_VALUE;
    int row, col;
    int[] dx = new int[]{0,1,0,-1};
    int[] dy = new int[]{1,0,-1,0};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) {
            return -1;
        }

        row = maze.length;
        col = maze[0].length;

        int[][] visited = new int[row][col];

        dfs(maze, start, destination, visited, 0);

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private void dfs(int[][] maze, int[] start, int[] destination, int[][] visited, int path) {
        if (visited[start[0]][start[1]] == 1) {
            return;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            minDistance = Math.min(minDistance, path);
            return;
        }

        visited[start[0]][start[1]] = 1;
        for (int i = 0; i < 4; i++) {
            int cnt = 0;
            int nx = start[0], ny = start[1];
            for (int tx = nx + dx[i], ty = ny + dy[i]; tx >= 0 && tx < row && ty >= 0 && ty < col && maze[tx][ty] == 0; ) {
                nx = tx;
                tx = nx + dx[i];
                ny = ty;
                ty = ny + dy[i];
                cnt += 1;
            }
            dfs(maze, new int[]{nx, ny}, destination, visited, path + cnt);
        }
        visited[start[0]][start[1]] = 0;
    }

    public static void main(String[] args) {
        TheMazeII505 obj = new TheMazeII505();
        int[][] maze = new int[][] {
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0},
        };
        int[] start = new int[]{0,4};
        int[] dest = new int[]{4,4};
        int ret = obj.shortestDistance(maze, start, dest);
        System.out.println(ret);
    }
}
