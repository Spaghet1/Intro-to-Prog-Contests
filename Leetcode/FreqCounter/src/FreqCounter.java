public class FreqCounter {
    public static int[] count(String s) {
        int[] freqs = new int[13];
        s = s.replace("(", "");
        s = s.replace(")", "");
        String[] split = s.split(",");
        for (String str : split) {
            int dig = Integer.valueOf(str);
            freqs[dig]++;
        }
        return freqs;
    }
    public static void main(String[] args) {
        String s = "(6,7),(10,1),(3,9),(12,6),(9,7),(7,7),(5,12),(7,12),(3,5),(3,10),(1,7),(6,1)";
        int[] freqs = count(s);
        for (int i = 0; i < freqs.length; i++) {
            System.out.println(i + ": " + freqs[i]);
        }
    }
}
