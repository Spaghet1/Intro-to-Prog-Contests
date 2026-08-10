
public class MysticDungeon {
    public int maximumEnergy(int[] energy, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = energy.length - k; i < energy.length; i++) {
            int cumSum = 0;
            for (int j = i; j >= 0; j -= k) {
                cumSum += energy[j];
                max = Math.max(max, cumSum);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        MysticDungeon dungeon = new MysticDungeon();
        int[] energy = new int[] {5,2,-10,-5,1};
        int k = 3;
        System.out.println(dungeon.maximumEnergy(energy, k));
    }
}
