import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxTestPass {

    static class PassTotal {
        int pass;
        int total;
        public PassTotal(int pass, int total) {
            this.pass = pass;
            this.total = total;
        }
    }

    static class MyComparator implements Comparator<PassTotal> {
        @Override
        public int compare(PassTotal o1, PassTotal o2) {
            int pass1 = o1.pass;
            int pass2 = o2.pass;
            int total1 = o1.total;
            int total2 = o2.total;
            double rate1 = (double) (pass1 + 1) / (total1 + 1) - (double) pass1 / total1;
            double rate2 = (double) (pass2 + 1) / (total2 + 1) - (double) pass2 / total2;
            return Double.compare(rate2, rate1);
        }
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<PassTotal> maxHeap = new PriorityQueue<>(extraStudents, new MyComparator());
        int l = classes.length;
        double totalPassRate = 0;
        for (int i = 0; i < l; i++) {
            int pass = classes[i][0];
            int total = classes[i][1];
            maxHeap.add(new PassTotal(pass, total));
            totalPassRate += (double) pass / total;
        }
        while (extraStudents > 0) {
            PassTotal max = maxHeap.poll();
            assert max != null;
            int pass = max.pass;
            int total = max.total;
            totalPassRate -= (double) pass / total;
            pass++;
            total++;
            extraStudents--;
            totalPassRate += (double) pass / total;
            maxHeap.add(new PassTotal(pass, total));
        }
        return totalPassRate / l;
    }

    public static void main(String[] args) {
        int[][] classes = new int[][] {
                {2, 4},
                {3, 9},
                {4, 5},
                {2, 10}
        };
        MaxTestPass mp = new MaxTestPass();
        System.out.println(mp.maxAverageRatio(classes, 4));
    }
}
