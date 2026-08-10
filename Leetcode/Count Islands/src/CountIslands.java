public class CountIslands {

    char[][] grid;
    boolean[][] visited;

    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count += dfs(i,j);
                }
            }
        }
        return count;
    }

    public int dfs(int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] == '0') {
            return 0;
        }
        if (visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        dfs(i - 1,j);
        dfs(i + 1,j);
        dfs(i, j - 1);
        dfs(i, j + 1);
        return 1;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(new CountIslands().numIslands(grid));
    }
}
