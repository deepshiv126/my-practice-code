package io.github.deepshiv126.practice.general.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Question is implement the LRU (Least Recently Used Cache),
 * meaning put (k,v) to fixed size list, remove least used (k,v)
 * and list only contains most used elements only.
 * <p>
 * There are only two operations - get(key) and put(key,value)
 * Expectation is both get and put ops should be Constant time ops.
 * <p>
 * Solution approach:
 * When element add/get need to be kept track that key is most actively used,
 * there are several ways to do that, but simplest way is to always
 * keep it front of list(Constant time for Linked List), when its newly added or accessed.
 * <p>
 * Other thing, when asked to get or update, then finding the (k,v) on a list be Big-O(n)
 * so, keep all key in a hashtable and reference to node, this gives Constant time.
 * <p>
 * When size becomes full, remove last node from list and map - this way list is fixed size.
 * <p>
 * In combinations with DoublyLinkedList and Hash - we get both get, put, remove all at constant time.
 */

class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node> map;
    private final DoublyLinkedList list;

    private int size;

    /**
     * Parameter Constructor
     *
     * @param intialCapacity A intial Capacity.
     */
    public LRUCache(final int intialCapacity) {
        this.capacity = intialCapacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList();
        this.size = 0;
    }

    public V get(final K key) {
        // get the key from the map if exits
        Node node = this.map.get(key);

        // if it doesn't exists, then return null
        if (node == null) {
            return null;
        }

        //key is now access, so it need to be moved to front of list.
        this.list.moveNodeToHead(node);
        return node.getValue();
    }

    public void put(final K key, final V value) {
        Node node = this.map.get(key);

        // if key already exists, then update the value
        // and move node to front of list.
        if (node != null) {
            node.setValue(value);
            this.list.moveNodeToHead(node);
            return;
        }

        // if key doesnt exists, then check size
        // if size reached max capacity,
        // then remove least used node.
        if (this.size == this.capacity) {
            Node tailNode = this.list.tail;
            //then remove least used node from tail.
            this.list.removeNodeFromTail();
            // then remove least used node from map.
            this.map.remove(tailNode.getKey());
            this.size--;
        }

        // otherwise, just a new node
        // to front of list and to map.
        Node newNode = new Node(key, value);
        this.list.addNodeToHead(newNode);
        this.map.put(key, newNode);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    // this is only for understanding.
    public void printAllElementForUnderstanding() {
        this.list.printAllElementForUnderstanding();
    }

    private class Node {
        private K key;
        private V value;

        private Node next, prev;

        Node(final K key, final V value) {
            this.key = key;
            this.value = value;
            this.next = this.prev = null;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    public class DoublyLinkedList {
        private Node head, tail;

        public DoublyLinkedList() {
            this.head = this.tail = null;
        }

        public void addNodeToHead(final Node node) {
            // if list is empty
            if (this.head == null && this.tail == null) {
                this.head = this.tail = node;
                return;
            }
            // otherwise add new node to head.
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }

        public void moveNodeToHead(final Node node) {
            // if node is at front, do nothing just return.
            if (this.head == node)
                return;

            // if node is at tail,
            if (this.tail == node) {
                this.tail = this.tail.prev;
                this.tail.next = null;
            } else {
                //if node is in middle, them extract it out.
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            // add extracted node to head.
            this.head.prev = node;
            node.next = this.head;
            node.prev = null;
            this.head = node;
        }

        public void removeNodeFromTail() {
            // if list is empty, nothing just return.
            if (this.head == null && this.tail == null)
                return;

            // if single element, set head and tail null
            if (this.head == this.tail) {
                this.head = this.tail = null;
                return;
            }

            // otherwise remove last node.
            this.tail = this.tail.prev;
            this.tail.next = null;
        }

        public void printAllElementForUnderstanding() {
            Node tmpNode = this.head;
            while (tmpNode != null) {
                System.out.println("(" + tmpNode.getKey() + "," + tmpNode.getValue() + ")");
                tmpNode = tmpNode.next;
            }
        }
    }
}
