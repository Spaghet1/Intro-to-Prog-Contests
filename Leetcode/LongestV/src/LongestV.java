public class LongestV {

    /*public enum Direction {
        NW,
        NE,
        SE,
        SW;
    }*/

    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = 0;
        int[][][][] dp = new int[n][m][4][2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int north = Math.max(backtrack(grid, i, j, 2, 0, 0, dp), backtrack(grid, i, j, 2, 1, 0, dp));
                    int south = Math.max(backtrack(grid, i, j, 2, 2, 0, dp), backtrack(grid, i, j, 2, 3, 0, dp));
                    int currMax = Math.max(north, south);
                    max = Math.max(max, currMax);
                }
            }
        }
        return max;
    }

    private int backtrack(int[][] grid, int i, int j, int next, int dir, int turned, int[][][][] dp) {
        if (dp[i][j][dir][turned] != 0) return dp[i][j][dir][turned];
        int count = 0;
        switch (dir) {
            case 0:
                if (i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1] == next) {
                    if (next == 0) {
                        count = backtrack(grid, i - 1, j - 1, 2, dir, turned, dp);
                    } else {
                        count = backtrack(grid, i - 1, j - 1, 0, dir, turned, dp);
                    }
                }
                if (turned == 0 && i - 1 >= 0  && j + 1 < grid[0].length && grid[i - 1][j + 1] == next) {
                    if (next == 0) {
                        count = Math.max(count, backtrack(grid, i - 1, j + 1, 2, 1, 1, dp));
                    } else {
                        count = Math.max(count, backtrack(grid, i - 1, j + 1, 0, 1, 1, dp));
                    }
                }
                break;
            case 1:
                if (i - 1 >= 0  && j + 1 < grid[0].length && grid[i - 1][j + 1] == next) {
                    if (next == 0) {
                        count = backtrack(grid, i - 1, j + 1, 2, dir, turned, dp);
                    } else {
                        count = backtrack(grid, i - 1, j + 1, 0, dir, turned, dp);
                    }
                }
                if (turned == 0 && i + 1 < grid.length  && j + 1 < grid[0].length && grid[i + 1][j + 1] == next) {
                    if (next == 0) {
                        count = Math.max(count, backtrack(grid, i + 1, j + 1, 2, 2, 1, dp));
                    } else {
                        count = Math.max(count, backtrack(grid, i + 1, j + 1, 0, 2, 1, dp));
                    }
                }
                break;
            case 2:
                if (i + 1 < grid.length  && j + 1 < grid[0].length && grid[i + 1][j + 1] == next) {
                    if (next == 0) {
                        count = backtrack(grid, i + 1, j + 1, 2, dir, turned, dp);
                    } else {
                        count = backtrack(grid, i + 1, j + 1, 0, dir, turned, dp);
                    }
                }
                if (turned == 0 && i + 1 < grid.length  && j - 1 >= 0 && grid[i + 1][j - 1] == next) {
                    if (next == 0) {
                        count = Math.max(count, backtrack(grid, i + 1, j - 1, 2, 3, 1, dp));
                    } else {
                        count = Math.max(count, backtrack(grid, i + 1, j - 1, 0, 3, 1, dp));
                    }
                }
                break;
            case 3:
                if (i + 1 < grid.length  && j - 1 >= 0 && grid[i + 1][j - 1] == next) {
                    if (next == 0) {
                        count = backtrack(grid, i + 1, j - 1, 2, dir, turned, dp);
                    } else {
                        count = backtrack(grid, i + 1, j - 1, 0, dir, turned, dp);
                    }
                }
                if (turned == 0 && i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1] == next) {
                    if (next == 0) {
                        count = Math.max(count, backtrack(grid, i - 1, j - 1, 2, 0, 1, dp));
                    } else {
                        count = Math.max(count, backtrack(grid, i - 1, j - 1, 0, 0, 1, dp));
                    }
                }
                break;
        }
        dp[i][j][dir][turned] = count + 1;
        return count + 1;
    }

    public  static void main(String[] args) {
        LongestV lv = new LongestV();
        int[][] grid = {
                {1, 1, 2, 1, 0, 1, 1, 0, 0},
                {1, 0, 1, 2, 2, 0, 2, 1, 1},
                {1, 0, 2, 0, 2, 1, 1, 1, 1}
        };
        System.out.println(lv.lenOfVDiagonal(grid));
    }
}
