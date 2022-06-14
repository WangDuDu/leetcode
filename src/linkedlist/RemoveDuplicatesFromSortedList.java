package linkedlist;

//给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,1,2]
//输出：[1,2]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
// Related Topics 链表 👍 808 👎 0

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();

        ListNode listNode5 = new ListNode(3);
        ListNode listNode4 = new ListNode(3, listNode5);
        ListNode listNode3 = new ListNode(2, listNode4);
        ListNode listNode2 = new ListNode(1, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println(solution.deleteDuplicates1(listNode1));
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
        // 递归解法
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode temp = deleteDuplicates(head.next);
            head.next = head.val == temp.val ? temp.next : temp;
            return head;
        }

        /**
         * 常规解法
         *
         * @param head
         * @return
         */
        public ListNode deleteDuplicates1(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            while (head.next != null) {
                if (head.val == head.next.val) {
                    head.next = head.next.next;
                } else {
                    head = head.next;
                }
            }
            return newHead.next;
        }

    }

}