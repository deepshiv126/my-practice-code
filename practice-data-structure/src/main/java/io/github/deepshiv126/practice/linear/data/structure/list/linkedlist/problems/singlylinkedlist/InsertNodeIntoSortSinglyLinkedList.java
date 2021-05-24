package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

/**
 * Insert a new Node into Sorted Singly Linked List.
 * <p>
 * Time Complexity is O(n).
 */
public class InsertNodeIntoSortSinglyLinkedList {

    // global parameters
    Node head;
    int currentSize;

    public InsertNodeIntoSortSinglyLinkedList() {
        this.head = null;
        this.currentSize = 0;
    }

    // definition of node.
    private static class Node {
        int element;
        Node next;

        public Node(final int element) {
            this.element = element;
            this.next = null;
        }
    }

    // addNode in Sorted List
    public boolean add(final int element) {
        Node newNode = new Node(element);
        // check boundary conditions

        // check if empty
        if (this.head == null) {
            this.head = newNode;
            this.currentSize++;
            return true;
        }

        // check more than one element
        Node previous = null, current = this.head;
        // this is where it gets O(n) time complexity.
        while (current != null && current.element < element) {
            previous = current;
            current = current.next;
        }

        newNode.next = current;
        // if its single element
        if (previous == null)
            this.head = newNode;
        else
            previous.next = newNode;
        this.currentSize++;
        return true;
    }


    // visual understaning
    public void printElements() {
        Node tmpNode = this.head;
        while (tmpNode != null) {
            //System.out.print(tmpNode.element + " --> ");
            tmpNode = tmpNode.next;
        }
    }
}
