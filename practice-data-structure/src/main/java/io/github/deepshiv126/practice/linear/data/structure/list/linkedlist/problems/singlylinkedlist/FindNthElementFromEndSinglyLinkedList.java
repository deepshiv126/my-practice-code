package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

/**
 * Find the "n"th element from end of list.
 * <p>
 * Solution is maintain 2 pointers -
 * move first pointer my nth position,
 * then move first and second pointers together,
 * till first pointer reach end.
 * <p>
 * Now, second pointer will be at nth position
 * from end of list.
 *
 * <p>
 * Time Complexity is Big O(n)
 */
public class FindNthElementFromEndSinglyLinkedList<E> {

    private Node<E> head;
    private int currentSize;

    public E findNthElementFromEnd(final int n) {
        // if input is incorrect.
        if (n <= 0)
            throw new IllegalArgumentException("Invalid value n : " + n);

        //if list is empty
        if (this.head == null)
            return null;

        //if list has one element
        if (this.head.next == null)
            return null;

        // idea is maintain 2 points - mainPointer and reference Pointer.
        Node<E> mainPointer = this.head;
        Node<E> referencePointer = this.head;

        // move referencePointer forward by nth position
        // (imagine there is nth spot difference between mainPointer and referencePointer)
        int count = 0;
        while (count < n) {
            // if reference point has reached end of the list, before reaching nth position,
            // then "n" is greater than current size of the list.
            if (referencePointer == null)
                throw new IllegalArgumentException(n + "is greater than size of the list.");

            referencePointer = referencePointer.next;
            count++;
        }

        // loop through the list - until referencePointer becomes null
        //  - simultaneously move mainPointer and referencePointer one step forward,
        //    by the time referencePointer meets end of the list, mainPointer
        //    reaches to nth position from end.

        // this is where its get O(n) complexity.
        while (referencePointer != null) {
            referencePointer = referencePointer.next;
            mainPointer = mainPointer.next;
        }

        return mainPointer.element;
    }


    // this is O(1), so I choose this method to add elements.
    public boolean addFirst(final E element) {
        final Node<E> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        currentSize++;
        return true;
    }

    // visual purpose.
    public void printAllElements() {
        Node<E> current = this.head;
        while (current != null) {
            //System.out.print(current.element + " --> ");
            current = current.next;
        }
        //System.out.println(" null ");
    }

    // actual node
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }
}
