package leetcode.editor.cn;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 👍 2078 👎 0


import java.util.ArrayList;
import java.util.List;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
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
     *
     * 本题的关键点是创建头结点之前的一个节点，这样始终能确保找啊到要删除节点pre节点
     */
    class Solution {
        /**
         * 两次循环
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            int length = 0;
            while (head != null) {
                length++;
                head = head.next;
            }
            head = newHead;
            int m = length - n;
            for (int i = 1; i <= m; i++) {
                head = head.next;
            }
            head.next = head.next.next;
            return newHead.next;
        }

        /**
         * 一次循环，使用字典保存所有节点
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            List<ListNode> listNodes = new ArrayList<>();
            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            listNodes.add(newHead);
            while (head != null) {
                listNodes.add(head);
                head = head.next;
            }
            int length = listNodes.size() - 1;
            int m = length - n;
            ListNode node = listNodes.get(m);
            node.next = node.next.next;
            return listNodes.get(0).next;
        }
    }

}