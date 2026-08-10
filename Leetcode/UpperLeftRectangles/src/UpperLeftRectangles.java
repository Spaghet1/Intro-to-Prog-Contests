import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UpperLeftRectangles {



    static class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1[0] != o2[0]){
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        }
    }

    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, new MyComparator());
        int count = 0;
        for(int i = 0; i < points.length - 1; i++) {
            int orgY = points[i][1];
            int prevY = Integer.MAX_VALUE;
            for (int j = i + 1; j < points.length; j++) {
                int currY = points[j][1];
                if (currY < orgY || currY >= prevY) {
                    continue;
                }
                if (currY == orgY) {
                    count++;
                    break;
                }
                count++;
                prevY = currY;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{0,5},{6,1},{4,5}};
        UpperLeftRectangles upper = new UpperLeftRectangles();
        System.out.println(upper.numberOfPairs(points));
    }
}
