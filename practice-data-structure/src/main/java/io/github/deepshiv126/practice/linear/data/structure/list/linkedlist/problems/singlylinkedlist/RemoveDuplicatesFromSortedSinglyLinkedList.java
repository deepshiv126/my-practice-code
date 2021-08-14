package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

/**
 * Remove Duplicates from Sorted Singly Linked List.
 * <p>
 * Time Complexity is O(n)
 */
public class RemoveDuplicatesFromSortedSinglyLinkedList<E> {

    // gobal variables.
    Node<E> head;
    Node<E> tail;
    int currentSize;

    // remove Duplicates.
    public boolean removeDuplicates() {

        // if list is empty
        if (this.head == null)
            return false;

        // if list has signle element
        if (this.head.next == null)
            return false;

        // if list has more than one elements
        boolean foundDuplicates = false;
        Node<E> tmpNode = this.head;
        while (tmpNode != null && tmpNode.next != null) {
            if (tmpNode.element == tmpNode.next.element) {
                foundDuplicates = true;
                // if we are using next.next, in even size list - ensure NPE is taken care.
                tmpNode.next = tmpNode.next.next;
            }
            tmpNode = tmpNode.next;
        }
        return foundDuplicates;
    }

    // add Ops for tests
    public void addLast(final E element) {
        Node<E> newNode = new Node(element);

        if (this.head == null) {
            this.head = this.tail = newNode;
            return;
        }

        this.tail.next = newNode;
        this.tail = newNode;
        return;
    }

    public void printAllElements() {
        Node<E> tmpNode = this.head;
        while (tmpNode != null) {
            //System.out.print(tmpNode.element + "  -->  ");
            tmpNode = tmpNode.next;
        }
        //System.out.println(" null ");
    }

    // definition of a node.
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }
}
