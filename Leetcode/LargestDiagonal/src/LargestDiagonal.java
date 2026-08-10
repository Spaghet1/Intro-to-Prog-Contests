public class LargestDiagonal {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxDiag = 0;
        int maxArea = 0;
        for (int i = 0; i < dimensions.length; i++) {
            int l = dimensions[i][0];
            int w = dimensions[i][1];
            int diag = l * l + w * w;
            if (diag > maxDiag) {
                maxDiag = diag;
                maxArea = l * w;
            } else if (diag == maxDiag) {
                int area = l * w;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] array = { {9,3}, {8,6} };
        LargestDiagonal ld = new LargestDiagonal();
        System.out.println(ld.areaOfMaxDiagonal(array));
    }
}
