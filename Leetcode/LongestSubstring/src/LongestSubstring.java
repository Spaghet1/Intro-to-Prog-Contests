import java.util.LinkedList;
import java.util.Queue;

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int l = 0;
        int max = 0;
        Queue<Character> queue = new LinkedList<>();
        queue.add(chars[0]);
        for (int r = 1; r < chars.length; r++) {
            char ch = chars[r];
            if (queue.contains(ch)) {
                char curr = Character.MAX_VALUE;
                while (curr != ch) {
                    curr = queue.poll();
                    l++;
                }
            }
            if (r - l + 1 > max) {
                max = r - l + 1;
            }
            queue.add(chars[r]);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();
        System.out.println(ls.lengthOfLongestSubstring("pwwkew"));
    }
}
