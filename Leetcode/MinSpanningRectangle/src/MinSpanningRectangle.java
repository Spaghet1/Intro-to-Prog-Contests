public class MinSpanningRectangle {
    public int minimumArea(int[][] grid) {
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        boolean minY_found = false;
        int minY = 0;
        int maxY = 0;
        boolean oneFound = false;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 1) {
                    oneFound = true;
                    if (!minY_found) {
                        minY_found = true;
                        minY = y;
                    }
                    if (y > maxY) {
                        maxY = y;
                    }
                    if (x < minX) {
                        minX = x;
                    }
                    if (x > maxX) {
                        maxX = x;
                    }
                }
            }
        }
        if (oneFound) {
            return ( (maxX + 1) - minX ) * ( (maxY + 1) - minY );
        } else {
            return 0;
        }
    }

    public int minimumArea2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int minX = m;
        int maxX = 0;
        int minY = n;
        int maxY = 0;
        loop: for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == 1) {
                    minX = j;
                    break loop;
                }
            }
        }
        loop: for (int j = m - 1; j > 0; j--) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == 1) {
                    maxX = j;
                    break loop;
                }
            }
        }
        loop: for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    minY = i;
                    break loop;
                }
            }
        }
        loop: for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    maxY = i;
                    break loop;
                }
            }
        }
        return (maxY + 1 - minY) * (maxX + 1 - minX);
    }

    public static void main(String[] args) {
        MinSpanningRectangle r = new MinSpanningRectangle();
        System.out.println(r.minimumArea2(new int[][]{{0,1,0},{1,0,1}}));
    }
}
