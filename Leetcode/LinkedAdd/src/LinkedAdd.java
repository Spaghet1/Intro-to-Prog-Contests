import java.util.List;

public class LinkedAdd {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int carry = sum / 10;
        sum = sum % 10;
        ListNode res = new ListNode(sum);
        ListNode cur = res;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                sum = l1.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                cur.next = new ListNode(sum);
                cur = cur.next;
                l1 = l1.next;
            }
        } else if (l2 != null) {

            while (l2 != null) {
                sum = l2.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                cur.next = new ListNode(sum);
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return res;
    }

    public static ListNode fillListNode(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            cur.next = node;
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedAdd linkedAdd = new LinkedAdd();
        ListNode a = fillListNode(new int[] {9,9,9,9,9,9,9});
        ListNode b = fillListNode(new int[] {9,9,9,9});
        ListNode c = linkedAdd.addTwoNumbers(a, b);
        while (c != null) {
            System.out.println(c.val);
            c = c.next;
        }
    }
}