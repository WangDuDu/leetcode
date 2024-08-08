package datastructure;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class CacheNode {
        Integer key;
        Integer val;
        CacheNode pre;
        CacheNode next;

        public CacheNode() {
            pre = null;
            next = null;
        }

        public CacheNode(int key, int val, CacheNode pre, CacheNode next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    CacheNode head;
    CacheNode tail;
    int capacity;
    int size;
    Map<Integer, CacheNode> lruMap;

    public LRUCache(int capacity) {
        lruMap = new HashMap<>(capacity);
        head = new CacheNode();
        tail = new CacheNode();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        CacheNode node = lruMap.get(key);
        if (node != null) {
            moveToHead(node);
            return lruMap.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        CacheNode curNode = new CacheNode(key, value, null, null);
        if (lruMap.containsKey(key)) {
            removeNode(lruMap.get(key));
            addNode(curNode);
            return;
        }
        if (size >= capacity) {
            deleteFromTail();
        }
        addNode(curNode);
    }

    private void moveToHead(CacheNode node) {
        CacheNode newNode = removeNode(node);
        addNode(node);
    }

    private void deleteFromTail() {
        CacheNode tailNode = tail.pre;
        removeNode(tailNode);
    }

    private CacheNode removeNode(CacheNode node) {
        CacheNode preNode = node.pre;
        preNode.next = node.next;
        node.next.pre = preNode;
        node.next = null;
        node.pre = null;
        lruMap.remove(node.key);
        size--;
        return node;
    }

    private void addNode(CacheNode node) {
        lruMap.put(node.key, node);
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        size++;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        lruCache.get(2);
        lruCache.put(1, 1);
        lruCache.put(4, 1);
        System.out.println(lruCache.lruMap);
    }
}
