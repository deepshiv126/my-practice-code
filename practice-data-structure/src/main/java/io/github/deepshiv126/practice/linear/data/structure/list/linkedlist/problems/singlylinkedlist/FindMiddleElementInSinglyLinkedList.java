package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

/**
 * Find the middle element in the singly linked list.
 * <p>
 * idea is maintain two pointer - slowPointer, fastPointer.
 * slowPointer move 1 step forward.
 * fastPointer move 2 step forward.
 * so, when fastPointer reaches to end, slowPointer will be at middle of list.
 * <p>
 * Time Complexity is n/2 or O(n).
 */

public class FindMiddleElementInSinglyLinkedList<E> {
    // define global parameters
    Node<E> head;
    Node<E> tail;
    int currentSize;

    public FindMiddleElementInSinglyLinkedList() {
        this.head = null;
        this.currentSize = 0;
    }

    public E findMiddleElementInSinglyLinkedList() {
        // check if list is empty
        if (this.head == null) {
            return null;
        }

        // check if list has 1 element.
        if (this.head.next == null) {
            return this.head.element;
        }

        // idea is maintain two pointer - slowPointer, fastPointer.
        // slowPointer move 1 step forward.
        // fastPointer move 2 step forward.

        Node<E> slowPointer = this.head;
        Node<E> fastPointer = this.head;

        // so, when fastPointer reaches to end, slowPointer will be at middle of list.
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            // if above while condition fastPointer.next is not checked for null,
            // then at last node next is null, next.next will be NPE.
            fastPointer = fastPointer.next.next;
        }
        return slowPointer.element;
    }


    // add Ops for test the logic
    public void addLast(final E element) {
        Node<E> newNode = new Node(element);

        if (this.head == null) {
            this.head = this.tail = newNode;
            this.currentSize++;
            return;
        }

        this.tail.next = newNode;
        this.tail = newNode;
        this.currentSize++;
        return;
    }


    // define node class;
    private static class Node<E> {
        Node<E> next;
        E element;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }
}
