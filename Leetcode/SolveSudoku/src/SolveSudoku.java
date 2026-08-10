import java.util.*;

public class SolveSudoku {
    char[][] board;
    boolean[][] rows = new boolean[9][9];
    boolean[][] cols = new boolean[9][9];
    boolean[][] boxes = new boolean[9][9];

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    rows[i][num] = cols[j][num] = boxes[boxIndex][num] = true;
                }
            }
        }
        test(0, 0);
    }

    private boolean test(int i, int j) {
        if (i == 9) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (board[x][y] == '.') {
                        return false;
                    }
                }
            }
            return true;
        }
        if (board[i][j] != '.') {
            return test( next(board, i, j)[0], next(board, i, j)[1]);
        }


        for (int digit = 0; digit < 9; digit++) {
            if (rows[i][digit] || cols[j][digit] || boxes[(i / 3) * 3 + (j / 3)][digit]) {
                continue;
            }
            board[i][j] = (char) (digit + '1');
            rows[i][digit] = true;
            cols[j][digit] = true;
            boxes[(i / 3) * 3 + (j / 3)][digit] = true;

            if (!test( next(board, i, j)[0], next(board, i, j)[1])) {
                board[i][j] = '.';
                rows[i][digit] = false;
                cols[j][digit] = false;
                boxes[(i / 3) * 3 + (j / 3)][digit] = false;
            } else {
                return true;
            }
        }
        return false;
    }

    private int[] next(char[][] board, int i, int j) {
        if ((j + 1) / 9 == 1) {
            i++;
        }
        j = (j + 1) % 9;
        return new int[]{i, j};
    }


    public static void main(String[] args) {
        char[][] board = {
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'},
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'},
                {'.','.','.','.','.','.','.','.','6'},
                {'.','.','.','2','7','5','9','.','.'}
        };
        SolveSudoku s = new SolveSudoku();
        s.solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
