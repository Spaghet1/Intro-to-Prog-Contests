import java.util.*;

public class MaxDamage {
    public long maximumTotalDamage(int[] power) {
        Arrays.sort(power);
        LinkedList<long[]> maxSeen = new LinkedList<>();
        long[] dp = new long[power.length];
        dp[0] = power[0];
        maxSeen.add(new long[]{power[0], power[0]});
        for (int i = 1; i < power.length; i++) {
            if (power[i] == power[i - 1]) {
                dp[i] = dp[i - 1] + power[i];
                maxSeen.getLast()[1] = dp[i];
                continue;
            }
            dp[i] = power[i];
            for (long[] visited : maxSeen) {
                if (visited[0] < power[i] - 2) {
                    dp[i] = Math.max(dp[i], power[i] + visited[1]);
                }
            }
            if (dp[i] >= maxSeen.getLast()[1] || power[i] < maxSeen.getLast()[0] + 3) {
                maxSeen.addLast(new long[]{power[i], dp[i]});
            }
            while (maxSeen.size() > 4) {
                maxSeen.removeFirst();
            }
        }
        long trueMax = 0;
        for (long[] visited : maxSeen) {
            trueMax = Math.max(trueMax, visited[1]);
        }
        return trueMax;
    }

    public static long maximumTotalDamage2(int[] power) {
        Map<Integer, Long> total = new HashMap<>();
        for (int p : power)
            total.put(p, total.getOrDefault(p, 0L) + p);

        List<Integer> keys = new ArrayList<>(total.keySet());
        Collections.sort(keys);

        int n = keys.size();
        long[] dp = new long[n];
        dp[0] = total.get(keys.getFirst());

        for (int i = 1; i < n; i++) {
            long take = total.get(keys.get(i));
            // find latest j where gap > 2
            int j = i - 1;
            while (j >= 0 && keys.get(i) - keys.get(j) <= 2) j--;
            if (j >= 0) take += dp[j];
            dp[i] = Math.max(dp[i - 1], take);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        MaxDamage d = new MaxDamage();
        int[] power = new int[] {5,58,45,54,60,6,34,26,3,64,47,58,13,31,41,32,49,10,51,27,12,24,37,15,11,29,6,41,10,61,17,6,23,36,63,58,50,64,55,52,46,13,33,64,27,41,65,27,11,27,59,53,60,37,66,10,28,32,38,26,9,45,55,9,48,22,22,61,62,8,41,14,23,61,40,40,5,42,60,4,55,50,30,3,58,33,27,25,6,32,8,33,16,34,20,14,7,19,22};
        System.out.println(d.maximumTotalDamage(power));
        System.out.println(d.maximumTotalDamage2(power));

    }
}
