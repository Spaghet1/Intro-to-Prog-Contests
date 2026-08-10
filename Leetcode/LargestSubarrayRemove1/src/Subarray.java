import java.util.Stack;

public class Subarray {
    public int longestSubarray(int[] nums) {
        int max = 0;
        int rear = 0;
        int front = 0;
        boolean zeroFound = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (!zeroFound) {
                    zeroFound = true;
                }
                if (i == 0) {
                    continue;
                }
                rear = front;
                front = 0;
            } else {
                front++;
                if (front + rear > max) {
                    max = front + rear;
                }
            }
        }
        if (zeroFound) {
            return max;
        } else {
            return max - 1;
        }
    }

    public int longestSubarray2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        boolean zeroFound = false;
        for (int num : nums) {
            if (num == 0) {
                zeroFound = true;
                stack.push(curr);
                curr = 0;
            } else {
                curr++;
            }
        }
        stack.push(curr);
        int max = 0;
        curr = 0;
        while (!stack.isEmpty()) {
            int prev = curr;
            curr = stack.pop();
            if (prev + curr > max) {
                max = prev + curr;
            }
        }
        if (zeroFound) {
            return max;
        } else {
            return max - 1;
        }
    }


    public static void main(String[] args) {
        Subarray s = new Subarray();
        System.out.println(s.longestSubarray2(new int[]{1,1,1}));
    }
}
