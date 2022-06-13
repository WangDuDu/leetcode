package linkedlist;

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 👍 2567 👎 0



public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(solution.reverseList(listNode1));
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
        // 常规循环
        public ListNode reverseList1(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode pre = null;
            ListNode next = head.next;
            while (next != null) {
                head.next = pre;
                pre = head;
                head = next;
                next = head.next;
            }
            head.next = pre;
            return head;
        }

        // 递归
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode next = head.next;
            // 传入的next是下一次递归的head
            ListNode newHead = reverseList1(next);
            // 处理当前的head和next的关系，此时的next.next = null
            next.next = head;
            head.next = null;
            return newHead;
        }

        // 头插
        public ListNode reverseList2(ListNode head) {
            // 构造头节点，然后将head链表一次头插入newHead链表
            ListNode newHead = new ListNode(-1);

            while (head != null) {
                ListNode next = head.next;
                head.next = newHead.next;
                newHead.next = head;
                head = next;
            }
            return newHead.next;
        }
    }

}