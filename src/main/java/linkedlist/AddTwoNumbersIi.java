package linkedlist;

//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能翻转该如何解决？ 
// Related Topics 栈 链表 数学 👍 525 👎 0

import java.util.Stack;

public class AddTwoNumbersIi {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbersIi().new Solution();
        ListNode listNode1_4 = new ListNode(3);
        ListNode listNode1_3 = new ListNode(4, listNode1_4);
        ListNode listNode1_2 = new ListNode(2, listNode1_3);
        ListNode listNode1_1 = new ListNode(7, listNode1_2);

        ListNode listNode2_3 = new ListNode(4);
        ListNode listNode2_2 = new ListNode(6, listNode2_3);
        ListNode listNode2_1 = new ListNode(5, listNode2_2);

        ListNode listNode3_3 = new ListNode(8);
        ListNode listNode3_2 = new ListNode(7, listNode3_3);
        ListNode listNode3_1 = new ListNode(2, listNode3_2);

        ListNode listNode4_1 = new ListNode(0);

        System.out.println(solution.addTwoNumbers(listNode1_1, listNode2_1));
        System.out.println(solution.addTwoNumbers(listNode4_1, listNode3_1));
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /**
         * (1)使用栈的数据结构，先进后出
         * (2)每一位的值生成的节点用头插法插入
         * 
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Stack<Integer> stack1 = new Stack<>();
            Stack<Integer> stack2 = new Stack<>();
            while (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }

            while (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }

            int carry = 0;
            int sum = 0;
            int val = 0;
            ListNode newHead = new ListNode(-1);
            while (!stack1.empty() || !stack2.empty() || carry != 0) {
                int x = stack1.empty() ? 0 : stack1.pop();
                int y = stack2.empty() ? 0 : stack2.pop();
                sum = x + y + carry;

                carry = sum / 10;
                val = sum % 10;
                ListNode node = new ListNode(val);
                node.next = newHead.next;
                newHead.next = node;
            }

            return newHead.next;
        }

        /**
         * 最笨的解法，先反转链表，然后再加减，然后再反转
         *
         * @param l1
         * @param l2
         * @return
         */
        public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            ListNode newL1 = reverse(l1);
            ListNode newL2 = reverse(l2);
            int carry = 0;
            int sum = 0;
            int val = 0;
            ListNode prev = new ListNode(-1);
            ListNode newHead = prev;
            while (newL1 != null || newL2 != null) {
                if (newL1 == null) {
                    sum = newL2.val + carry;
                } else if (newL2 == null) {
                    sum = newL1.val + carry;
                } else {
                    sum = newL1.val + newL2.val + carry;
                }
                val = sum % 10;
                carry = sum / 10;
                ListNode node = new ListNode(val);
                prev.next = node;
                prev = prev.next;

                if (newL1 != null) {
                    newL1 = newL1.next;
                }

                if (newL2 != null) {
                    newL2 = newL2.next;
                }
            }

            if (carry == 1) {
                ListNode node = new ListNode(1);
                prev.next = node;
            }
            return reverse(newHead.next);
        }

        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode next = head.next;
            ListNode newHead = reverse(head.next);
            head.next = null;
            next.next = head;
            return newHead;
        }
    }
}