import java.util.*;

public class ReplaceNonCoprime {

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public List<Integer> replaceNonCoprimes(int[] nums) {
        if (nums.length == 1) {
            return Collections.singletonList(nums[0]);
        }

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> res = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            stack.add(nums[i]);
        }

        while (stack.size() > 1) {
            int left = stack.pop();
            int right = stack.peek();
            int g = gcd(left, right);
            if (g == 1) {
                res.add(left);
            } else {
                stack.pop();
                stack.push(left / g * right);
                if (!res.isEmpty()) {
                    stack.push(res.pop());
                }
            }
        }
        res.push(stack.pop());
        return res;
    }

    public static void main(String[] args) {
        ReplaceNonCoprime re = new ReplaceNonCoprime();
        int[] nums = new int[] {6,4,3,2,7,6,2};
        System.out.println(re.replaceNonCoprimes(nums));
        //int a = 2342;
        //int b = 323;
        //System.out.println(a * b / re.gcd(a, b));
        System.out.println(re.gcd(31,97561));
    }
}
