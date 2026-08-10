import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesisHelper(list, 0, 0, n, new StringBuilder());
        return list;
    }

    private void generateParenthesisHelper(List<String> list, int count, int closingCount, int n, StringBuilder sb) {
        if (closingCount == 0 && count == n) {
            list.add(sb.toString());
        }
        StringBuilder sb_clone = new StringBuilder(sb);
        if (count < n) {
            sb.append('(');
            generateParenthesisHelper(list, count + 1, closingCount + 1, n, sb);
        }
        if (closingCount != 0) {
            sb_clone.append(')');
            generateParenthesisHelper(list, count, closingCount - 1, n, sb_clone);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        List<String> list = g.generateParenthesis(6);
        System.out.println(list);
    }
}
