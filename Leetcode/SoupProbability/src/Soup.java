import java.util.HashMap;
import java.util.Map;

public class Soup {
    public double soupServings(int n) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        n = (n + 25 - 1) / 25 * 25;
        for (int i = 0; i <= n; i++) {
            HashMap<Integer, Double> b_empty = new HashMap<>();
            map.put(i, b_empty);
        }
        double prob = 0;
        for (int i = 1; i <= 4; i++) {
            prob = prob + soupServingsHelper(n, n, 1, i, map);
        }
        return prob;
    }

    public double soupServingsHelper(int a, int b, int stage, int scenario, Map<Integer, Map<Integer, Double>> map) {
        Map<Integer, Double> b_row = map.get(a);
        if (b_row.containsKey(b)) {
            return b_row.get(b);
        }
        switch (scenario) {
            case 1:
                a = a - 100;
                break;
            case 2:
                a = a - 75;
                b = b - 25;
                break;
            case 3:
                a = a - 50;
                b = b - 50;
                break;
            case 4:
                a = a - 25;
                b = b - 75;
                break;
        }
        if (a < 0) {
            a = 0;
        }
        if (b < 0) {
            b = 0;
        }
        if (a == 0) {
            if (b == 0) {
                double prob = 0.5;
                return prob;
            }
            double prob = 1.0;
            return prob;
        } else if (b == 0) {
            return 0;
        }
        double prob = 0;
        for (int i = 1; i <= 4; i++) {
            prob = prob + soupServingsHelper(a, b, stage + 1, i, map) * Math.pow(0.25, stage);
        }
        map.get(a).put(b, prob);
        return prob;
    }

    public static void main(String[] args) {
        Soup soup = new Soup();
        System.out.println(soup.soupServings(50));
    }
}
