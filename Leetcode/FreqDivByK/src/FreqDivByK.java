import java.util.HashMap;
import java.util.Map;

public class FreqDivByK {
    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value % k == 0) count += entry.getKey() * value;
        }
        return count;
    }
}
