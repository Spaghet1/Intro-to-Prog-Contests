

public class NumSecret {

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int[][] thoseWhoKnow = new int[n][forget];
        thoseWhoKnow[0][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = delay - 1; j < forget - 1; j++) {
                thoseWhoKnow[i][0] += thoseWhoKnow[i - 1][j];
                thoseWhoKnow[i][0] %= 1000000007;
            }
            for (int j = 1; j < forget; j++) {
                thoseWhoKnow[i][j] = thoseWhoKnow[i - 1][j - 1] % 1000000007;
            }
        }
        int res = 0;
        for (int i = 0; i < forget; i++) {
            res += thoseWhoKnow[n - 1][i];
            res %= 1000000007;
        }
        return res;
    }

    public int peopleAwareOfSecret2(int n, int delay, int forget) {
        int[] thoseWhoKnow = new int[n];
        thoseWhoKnow[0] = 1;
        for (int i = delay; i < n; i++) {
            for (int j = i - forget + 1; j <= i - delay; j++) {
                if (j < 0) {
                    continue;
                }
                thoseWhoKnow[i] += thoseWhoKnow[j];
                thoseWhoKnow[i] %= 1000000007;
            }
        }
        int res = 0;
        for (int i = n - 1;  i > n - 1 - forget; i--) {
            res += thoseWhoKnow[i];
            res %= 1000000007;
        }
        return res;
    }


    public static void main(String[] args) {
        NumSecret numSecret = new NumSecret();
        //System.out.println(numSecret.peopleAwareOfSecret(684,18,496));
        System.out.println(numSecret.peopleAwareOfSecret2(684,18,496));
    }
}
