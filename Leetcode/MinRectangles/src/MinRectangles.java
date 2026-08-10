import java.util.Arrays;

public class MinRectangles {
    private static int[][] rotate(int[][] vec) {
        int n = vec.length;
        int m = vec[0].length;
        int[][] ret = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ret[m - j - 1][i] = vec[i][j];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = { {1,2,3}, {4,5,6}, {7,8,9}};
        int[][] matrix_T = rotate(matrix);
        for (int[] row : matrix_T) {
            System.out.println(Arrays.toString(row));
        }
    }
}
