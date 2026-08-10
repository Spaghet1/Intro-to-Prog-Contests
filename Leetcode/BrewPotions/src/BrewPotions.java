public class BrewPotions {
    public long minTime(int[] skill, int[] mana) {
        long[] times = new long[skill.length + 1];
        build(skill, mana, times, 0, 0);
        for (int i = 1; i < mana.length; i++) {
            long startTime = Integer.MIN_VALUE;
            long cumulativeTime = 0;
            for (int j = 0; j < skill.length; j++) {
                startTime = Math.max(startTime, times[j + 1] - cumulativeTime);
                cumulativeTime += (long) skill[j] * mana[i];
            }
            build(skill, mana, times, startTime, i);
        }
        return times[skill.length];
    }

    public void build(int[] skill, int[] mana, long[] times, long startTime, int potion) {
        times[0] = startTime;
        for (int i = 0; i < skill.length; i++) {
            times[i + 1] = times[i] + (long) skill[i] * mana[potion];
        }
    }

    public static void main(String[] args) {
        BrewPotions brewPotions = new BrewPotions();
        int[] skill = {1,5,2,4};
        int[] mana = {5,1,4,2};
        System.out.println(brewPotions.minTime(skill, mana));
    }
}
