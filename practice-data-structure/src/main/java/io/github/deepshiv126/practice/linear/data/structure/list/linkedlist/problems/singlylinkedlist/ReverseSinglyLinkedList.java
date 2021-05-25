package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

/**
 * Reverse the given singly linked list.
 * Solution - Maintain 3 pointers, previous, current and next.
 * Start with head as current, break the link from next node
 * and assign it to previous node.
 * <p>
 * Time Complexit is O(n).
 */
public class ReverseSinglyLinkedList<E> {

    private Node<E> head;
    private int size;

    public ReverseSinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean reverse() {
        // if list is empty
        if (this.head == null)
            return false;

        // if list has one or more than one element
        // maintain 3 pointers - previsou, curren t and next
        Node<E> previous = null;
        Node<E> current = this.head;
        Node<E> next = null;

        // loop through the entire list
        while (null != current) {
            //get reference to rest of the list from current node afterwards.
            //so you dont loose the rest of the list.
            next = current.next;
            // point current to previous
            // this is reversing
            current.next = previous;
            //shift your pointer to forward
            //   previous --> current,
            //   current --> next,
            //   next --> next.next or current.next
            previous = current;
            current = next;
        }
        // finally update the head.
        this.head = previous;

        return true;
    }

    public void addFirst(final E element) {
        Node<E> newNode = new Node(element);

        // if list is empty
        if (this.head == null) {
            this.head = newNode;
            return;
        }

        // if list has one ore more elements.
        newNode.next = this.head;
        this.head = newNode;
        size++;
    }


    public void printAllElements() {
        Node<E> tmpNode = this.head;
        if (this.head == null)
            return;

        while (tmpNode != null) {
           //System.out.print(tmpNode.element + " --> ");
            tmpNode = tmpNode.next;
        }
        //System.out.println("null");
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }
}
