
public class FlowerGame {
    public long flowerGame(int n, int m) {
        if (n == 1 && m == 1) {
            return 0;
        }
        int count = 0;
        int i = 1;
        while (i <= n) {
            System.out.println(m / 2 + " " + i);
            count += m / 2;
            i++;
            if (i > n) break;
            System.out.println((m + 1) / 2 + " " + i);
            count += (m + 1) / 2;
            i++;
        }
        return count;
    }
    public static void main(String[] args) {
        FlowerGame g = new FlowerGame();
        long x = g.flowerGame(100000,100000);
    }
}