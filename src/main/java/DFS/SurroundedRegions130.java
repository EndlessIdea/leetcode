package DFS;

import java.util.Arrays;

public class SurroundedRegions130 {
    int[] dx= new int[]{0,1,0,-1};
    int[] dy= new int[]{1,0,-1,0};
    int row = 0;
    int col = 0;

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        row = board.length;
        col = board.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'R') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (board[r][c] == 'X' || board[r][c] == 'R') {
            return;
        }

        board[r][c] = 'R';
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (nr >= 0 && nr < row && nc >= 0 && nc < col) {
                dfs(board, nr, nc);
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions130 obj = new SurroundedRegions130();
        char[][] board = new char[][] {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        obj.solve(board);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}
