package leetcode.editor.cn;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ListNode.java
 * @Description TODO
 * @createTime 2022年06月09日 22:32:00
 */
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
