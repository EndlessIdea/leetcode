package TwoPointer;

import java.util.Arrays;

public class CandyCrush723 {
    private static final int MIN_CNT = 3;
    int xlen = 0;
    int ylen = 0;

    public int[][] candyCrush(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }

        xlen = board.length;
        ylen = board[0].length;

        while (crush(board)) {
            restore(board);
        }

        return board;
    }

    private boolean crush(int[][] board) {
        boolean hasCrushed = false;

        for (int x = 0; x < xlen; x++) {
//            if (board.length - x < MIN_CNT) {
//                continue;
//            }
            for (int y = 0; y < ylen; y++) {
//                if (board[0].length - y < MIN_CNT) {
//                    continue;
//                }
                if (board[x][y] == 0) {
                    continue;
                }

                int type = Math.abs(board[x][y]);
                //横向找
                int dy = y + 1;
                for (; dy < ylen; dy++) {
                    System.out.printf("dy is %d, val is %d\n", dy, board[x][dy]);
                    if (Math.abs(board[x][dy]) != type) {
                        break;
                    }
                }
                if (dy - y >= MIN_CNT) {
                    hasCrushed = true;
                    for (int j = y; j < dy; j++) {
                        board[x][j] = -type;
                    }
                }
                //纵向找
                int dx = x + 1;
                for (; dx < xlen; dx++) {
                    System.out.printf("dx is %d, val is %d\n", dx, board[dx][y]);
                    if (Math.abs(board[dx][y]) != type) {
                        break;
                    }
                }
                if (dx - x >= MIN_CNT) {
                    hasCrushed = true;
                    for (int j = x; j < dx; j++) {
                        board[j][y] = -type;
                    }
                }

                System.out.printf("x is %d, y is %d, dx is %d, dy is %d, type is %d, crushed is %b\n", x, y, dx, dy, type, hasCrushed);
            }
        }

        return hasCrushed;
    }

    private void restore(int[][] board) {
        for (int y = 0; y < ylen; y++) {
            restoreCol1(board, y);
        }
    }

    private void restoreCol1(int[][] board, int y) {
        int head = xlen - 1;
        for (int cur = xlen - 1; cur >= 0; cur--) {
            if (board[cur][y] > 0) {
                board[head--][y] = board[cur][y];
            }
        }
        for (int i = head; i >= 0; i--) {
            board[i][y] = 0;
        }
    }

    private void restoreCol(int[][] board, int y) {
        System.out.println(Arrays.toString(board[0]));
        System.out.println(Arrays.toString(board[1]));
        System.out.println(Arrays.toString(board[2]));
        int epos = xlen;
        for (int x = xlen - 1; x > 0; x--) {
            if (board[x][y] == 0) {
                epos = x;
                break;
            }
        }
        int xpos = epos;
        for (int x = epos - 1; x >= 0; x--) {
            if (board[x][y] != 0) {
                xpos = x;
                break;
            }
        }
        int epos2 = xpos;
        for (int x = xpos - 1; x >= 0; x--) {
            if (board[x][y] == 0) {
                epos2 = x;
                break;
            }
        }
        for (int p1 = epos, p2 = xpos; p1 < xpos && p2 < epos2; p1++, p2++) {
            board[p1][y] = board[p2][y];
            board[p2][y] = 0;
        }

        if (xpos != epos) {
            restoreCol(board, y);
        }
    }

    public static void main(String[] args) {
        CandyCrush723 obj = new CandyCrush723();
        int[][] board = new int[][]{
                {1,4,5},
                {1,2,3},
                {1,1,1}
        };
        System.out.println(Arrays.toString(board[0]));
        System.out.println(Arrays.toString(board[1]));
        System.out.println(Arrays.toString(board[2]));
        int[][] newBoard = obj.candyCrush(board);
        System.out.println(Arrays.toString(newBoard[0]));
        System.out.println(Arrays.toString(newBoard[1]));
        System.out.println(Arrays.toString(newBoard[2]));
    }
}
