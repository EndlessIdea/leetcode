package DFS;

public class Minesweeper529 {
    int[][] directions = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
    };
    int r = 0, c = 0;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null) {
            return null;
        }
        r = board.length;
        c = board[0].length;
        char[][] ret = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ret[i][j] = board[i][j];
            }
        }

        dfs(ret, click[0], click[1]);
        return ret;
    }

    private void dfs(char[][] ret, int row, int col) {
        if (ret[row][col] == 'M') {
            ret[row][col] = 'X';
            return;
        }
        if (ret[row][col] != 'E') {
            return;
        }
        int bomb = 0;
        for (int i = 0; i < directions.length; i++) {
            int nRow = row + directions[i][0];
            int nCol = col + directions[i][1];
            if (nRow >= 0 && nRow < r && nCol >= 0 && nCol < c && ret[nRow][nCol] == 'M') {
                bomb++;
            }
        }
        if (bomb > 0) {
            ret[row][col] = String.valueOf(bomb).charAt(0);
        } else {
            ret[row][col] = 'B';
            for (int i = 0; i < directions.length; i++) {
                int nRow = row + directions[i][0];
                int nCol = col + directions[i][1];
                if (nRow >= 0 && nRow < r && nCol >= 0 && nCol < c && ret[nRow][nCol] == 'E') {
                    dfs(ret, nRow, nCol);
                }
            }
        }
    }

    public static void main(String[] args) {
        Minesweeper529 obj = new Minesweeper529();
        char[][] board = new char[][] {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
        };
        char[][] ret = obj.updateBoard(board, new int[]{3, 0});
    }
}
