public class MinOpsArray {
    public long minOperations(int[][] queries) {
        long ops = 0;
        for (int[] array : queries) {
            long currOps = 0;
            int l = array[0];
            int r = array[1];
            int minExp = log_4(l);
            int maxExp = log_4(r);
            for (int i = minExp + 1; i < maxExp; i++) {
                currOps += (1L << (2 * (i - 1))) * 3 * i;
            }
            currOps += minExp * (Math.min(r + 1, (1L << (2 * minExp))) - l);
            if (minExp != maxExp) {
                currOps += maxExp * (r - (1L << (2 * (maxExp - 1))) + 1);
            }
            ops += (currOps + 1) / 2;
        }
        return ops;
    }
    private int log_4(int x) {
        int count = 0;
        while (x > 0) {
            x = x >> 2;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        MinOpsArray m = new MinOpsArray();
        int[][] queries = {
                //{1,2},
                {1,21}
        };
        System.out.println(m.minOperations(queries));
    }
}
