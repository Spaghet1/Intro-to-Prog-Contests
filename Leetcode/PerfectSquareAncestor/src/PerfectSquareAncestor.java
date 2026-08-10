import java.util.ArrayList;

public class PerfectSquareAncestor {
    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        return dfs(0, edges, nums, list);
    }

    public int dfs(int node, int[][] edges, int[] nums, ArrayList<Integer> path) {
        int count = 0;
        for (int value : path) {
            long product = (long) nums[node] * value;
            long root = sqrt(product);
            if (root * root == product) {
                count++;
            }
        }
        path.add(nums[node]);
        for (int[] edge : edges) {
            if (edge[0] == node) {
                count += dfs(edge[1], edges, nums, path);
            }
        }
        path.removeLast();
        return count;
    }

    public long sqrt(long n) {
        if (n > 0 && n < 4) return 1;
        if (n >= 4 && n < 9) return 2;
        long l = 1;
        long r = n / 2;
        while (l < r) {
            long mid = (l + r) / 2;
            long square = mid * mid;
            if (square == n) return mid;
            if (square > n) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        PerfectSquareAncestor s = new PerfectSquareAncestor();
        int[][] edges = new int[][]{
                {0,1},
                {1,2}
        };
        int[] nums = new int[]{2,8,2};
        System.out.println(s.sumOfAncestors(3, edges, nums));
        for (int i = 0; i < 401; i++) {
            System.out.println("sqrt(" + i + ") = " + s.sqrt(i));
        }
        System.out.println(s.sqrt(80571L * 80571));
    }
}
