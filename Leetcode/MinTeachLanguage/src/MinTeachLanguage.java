import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MinTeachLanguage {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer>[] needed = new Set[n];
        for (int i = 0; i < n; i++) {
            needed[i] = new HashSet<>();
        }
        for (int[] friendship : friendships) {
            int f1 = friendship[0] - 1;
            int f2 = friendship[1] - 1;
            Set<Integer> known = new HashSet<>();
            boolean canTalk = false;
            for (int lang : languages[f1]) {
                known.add(lang);
            }
            for (int lang : languages[f2]) {
                if (!known.add(lang)) {
                    canTalk = true;
                }
            }
            if (!canTalk) {
                for (int lang : languages[f1]) {
                    needed[lang - 1].add(f1);
                }
                for (int lang : languages[f2]) {
                    needed[lang - 1].add(f2);
                }
            }
        }
        int max = 0;
        for (Set<Integer> need : needed) {
            if (need.size() > max) {
                max = need.size();
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] languages = new int[][]{{4,7,2,14,6},{15,13,6,3,2,7,10,8,12,4,9},{16},{10},{10,3},{4,12,8,1,16,5,15,17,13},{4,13,15,8,17,3,6,14,5,10},{11,4,13,8,3,14,5,7,15,6,9,17,2,16,12},{4,14,6},{16,17,9,3,11,14,10,12,1,8,13,4,5,6},{14},{7,14},{17,15,10,3,2,12,16,14,1,7,9,6,4}};
        int [][] friendships = new int[][]{{4,11},{3,5},{7,10},{10,12},{5,7},{4,5},{3,8},{1,5},{1,6},{7,8},{4,12},{2,4},{8,9},{3,10},{4,7},{5,12},{4,9},{1,4},{2,8},{1,2},{3,4},{5,10},{2,7},{1,7},{1,8},{8,10},{1,9},{1,10},{6,7},{3,7},{8,12},{7,9},{9,11},{2,5},{2,3}};
        MinTeachLanguage minTeachLanguage = new MinTeachLanguage();
        System.out.println(minTeachLanguage.minimumTeachings(17, languages, friendships));
    }
}
