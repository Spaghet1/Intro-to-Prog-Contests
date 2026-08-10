import java.util.*;

public class PacificAtlantic {
    final int[] vert = new int[]{1, -1, 0, 0};
    final int[] horz = new int[]{0, 0, -1, 1};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        Set<Integer> legal = new HashSet<>();
        legal.add(encode(n - 1, 0));
        legal.add(encode(0, m - 1));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                BFS(heights, i, j, legal);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int x : legal) {
            List<Integer> list = decode(x);
            result.add(list);
        }
        return result;
    }

    public void BFS(int[][] heights, int i, int j, Set<Integer> legal) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean atlantic = false;
        boolean pacific = false;
        queue.add(encode(i, j));

        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (legal.contains(x)) {
                legal.add(encode(i, j));
                return;
            }
            visited.add(x);
            List<Integer> coords = decode(x);
            if (coords.getFirst() == 0 || coords.getLast() == 0) {
                pacific = true;
            }
            if (coords.getFirst() == heights.length - 1 || coords.getLast() == heights[0].length - 1) {
                atlantic = true;
            }
            if (pacific && atlantic) {
                legal.add(encode(i,j));
                return;
            }
            for (int k = 0; k < 4; k++) {
                int i2 = coords.getFirst() + vert[k];
                int j2 = coords.getLast() + horz[k];
                if (visited.contains(encode(i2, j2)) || i2 < 0 || i2 >= heights.length || j2 < 0 || j2 >= heights[0].length) continue;
                if (heights[coords.getFirst()][coords.getLast()] < heights[i2][j2]) continue;
                queue.add(encode(i2, j2));
            }
        }
    }

    public int encode(int x, int y) {
        return x * 201 + y;
    }

    public List<Integer> decode(int x) {
        List<Integer> list = new ArrayList<>(2);
        list.add(x / 201);
        list.add(x % 201);
        return list;
    }

    public static void main(String[] args) {
        PacificAtlantic p = new PacificAtlantic();
        int[][] heights = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(p.pacificAtlantic(heights));
    }
}
