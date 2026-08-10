import java.util.ArrayList;
import java.util.Collections;

public class SortMatrixDiag {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        ArrayList<Integer> diag = new ArrayList<>();
        for (int i = n - 2; i >= 0; i--) {
            int y = i;
            int x = 0;
            while (y < n) {
                diag.add(grid[y++][x++]);
            }
            Collections.sort(diag);
            Collections.reverse(diag);
            y = i;
            x = 0;
            while (y < n) {
                grid[y++][x++] = diag.removeFirst();
            }
        }
        for (int j = 1; j < n - 1; j++) {
            int x = j;
            int y = 0;
            while (x < n) {
                diag.add(grid[y++][x++]);
            }
            Collections.sort(diag);
            x = j;
            y = 0;
            while (x < n) {
                grid[y++][x++] = diag.removeFirst();
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        SortMatrixDiag g = new SortMatrixDiag();
        int[][] arr = {
                {1, 7, 3},
                {9, 8, 2},
                {4, 5, 6}
        };
        arr = g.sortMatrix(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
