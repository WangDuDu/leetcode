package leetcode.editor.cn;

//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1417 👎 0

public class PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();

        ListNode listNode5 = new ListNode(1);
        ListNode listNode4 = new ListNode(2, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(solution.isPalindrome(listNode1));
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
         * 1、首先将链表用快慢指针分成两部分，偶数个节点的话，前后两部分相等，奇数个节点的话，后半部分链表少一个
         * 2、然后从slow指针的next为head反转后半部分链表
         * 3、前半部分链表和后半部分链表同时遍历，逐个比较，直到后半部分遍历完成为止
         * @param head
         * @return
         */
        public boolean isPalindrome(ListNode head) {
            if (head.next == null) {
                return true;
            }

            ListNode slow = head;
            ListNode fast = head.next;
            int count = 0;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                count++;
            }
            ListNode tail = reverse(slow.next);

            boolean res = true;
            while (tail != null) {
                if (tail.val == head.val) {
                    head = head.next;
                    tail = tail.next;
                    continue;
                }
                res = false;
                break;
            }
            return res;
        }

        private ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode next = head.next;
            ListNode res = reverse(head.next);
            next.next = head;
            head.next = null;
            return res;
        }
    }

}