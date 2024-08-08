package doublepoint.baseobject;

/**
 * Created by wangshuyang on 2021-3-18.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
