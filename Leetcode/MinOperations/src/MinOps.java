public class MinOps {
    public int makeTheIntegerZero(int num1, int num2) {
        if (num2 > num2) {
            return -1;
        }
        int count = 1;
        while (true) {
            long temp1 = num1 - (long) count * num2;
            if (temp1 < count) {
                return -1;
            }
            for (int factorCount = 0; factorCount <= count; factorCount++) {

                int exp = 0;
                if (temp1 == 0) {
                    return count;
                }
                while (temp1 >= 1L << exp) {
                    exp++;
                }
                temp1 -= 1L << (exp - 1);
            }
            count++;
        }
    }

    public static void main(String[] args) {
        MinOps m = new MinOps();
        System.out.println(m.makeTheIntegerZero(85, 42));
    }
}
