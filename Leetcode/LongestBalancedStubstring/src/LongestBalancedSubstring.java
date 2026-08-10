public class LongestBalancedSubstring {
    public int longestBalanced(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] chars = new int[3];
            for (int j = i; j < s.length(); j++) {
                chars[s.charAt(j) - 'a']++;
                boolean isBalanced = true;
                int prevCount = 0;
                for (int c : chars) {
                    if (c != 0) {
                        if (prevCount == 0) {
                            prevCount = c;
                        }
                        else if (prevCount != c) {
                            isBalanced = false;
                            break;
                        }
                    }
                }
                if (isBalanced) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestBalancedSubstring l = new LongestBalancedSubstring();
        System.out.println(l.longestBalanced("abbac"));
    }
}
