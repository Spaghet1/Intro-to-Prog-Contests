import java.util.Arrays;
import java.util.Stack;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean reverse = true;
        int[] res = new int[rows * cols];
        int resPos = 0;
        for (int curr_x = 0; curr_x < cols; curr_x++) {
            int y = 0;
            int x = curr_x;
            if (!reverse) {
                while (x >= 0 && y < rows) {
                    res[resPos++] = matrix[y++][x--];
                }
            } else {
                Stack<Integer> stack = new Stack<>();
                while (x >= 0 && y < rows) {
                    stack.push(matrix[y++][x--]);
                }
                while (!stack.isEmpty()) {
                    res[resPos++] = stack.pop();
                }
            }
            reverse = !reverse;
        }
        for (int curr_y = 1; curr_y < rows; curr_y++) {
            int y = curr_y;
            int x = cols - 1;
            if (!reverse) {
                while (x >= 0 && y < rows) {
                    res[resPos++] = matrix[y++][x--];
                }
            } else {
                Stack<Integer> stack = new Stack<>();
                while (x >= 0 && y < rows) {
                    stack.push(matrix[y++][x--]);
                }
                while (!stack.isEmpty()) {
                    res[resPos++] = stack.pop();
                }
            }
            reverse = !reverse;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = { {1,2,3}, {4,5,6}, {7,8,9} };
        DiagonalTraverse dt = new DiagonalTraverse();
        System.out.println(Arrays.toString(dt.findDiagonalOrder(matrix)));
    }
}
