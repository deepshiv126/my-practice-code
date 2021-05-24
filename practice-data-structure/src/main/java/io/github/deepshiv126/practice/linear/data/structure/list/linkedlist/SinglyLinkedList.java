package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;

import io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.List;

/**
 * Singly Linked List is a linear data structure used for storing collections of nodes.
 * - It contains sequence of node
 * - A node has data and reference to next node in a list.
 * - First node is the head node.
 * - Last node has data and points to null.
 */
public class SinglyLinkedList<E> implements List<E> {

    /**
     * Node is most basic and primitive component of linked list.
     * Node holds element data and reference to next Node.
     * <p>
     * Node is intentionally made private class,
     * so its not reachable outside or not able to modify.
     *
     * @param <E>
     */
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }

    // Singly Linked List maintains head.
    private Node<E> head;

    // Singly Linked List gets better with tail pointer in addLast Ops.
    private Node<E> tail;

    // Maintain a current size of the list.
    private int currentSize;

    // empty list, sets head to null, size to zero.
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }

    /**
     * Returns the size of the list.
     * <p>
     * Time Complexity - O(n) or O(1)
     *
     * @return size of the list.
     */
    @Override
    public int size() {
        //return sizeWithNComplexity();
        // or
        return currentSize;
    }

    /**
     * Time Complexity is Big O(n).
     *
     * <p>
     * How to make this faster -
     * 1. Maintain size variable and
     * make sure update it promptly.
     *
     * @return
     */
    private int sizeWithNComplexity() {
        int size = 0;
        Node<E> tmpNode = this.head;
        while (null != tmpNode) {
            size++;
            tmpNode = tmpNode.next;
        }
        return size;
    }

    /**
     * Returns whether this list is empty or not.
     * <p>
     * TimeComplexity is O(1)
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.head == null ? true : false;
    }

    /**
     * Add given element at the beginning of the list.
     * <p>
     * TimeComplexity is O(1)
     *
     * @param element
     * @return true if added, false if not added.
     */
    @Override
    public boolean addFirst(final E element) {
        final Node<E> newNode = new Node<>(element);
        // make sure this is the order
        // 1. newNode points to head first
        // 2. then shift head to newNode.
        // otherwise you will break the list and point to itself.
        // 1. head points to newNode
        // 2. newNode which is head and its next point back itself.
        newNode.next = head;
        head = newNode;
        currentSize++;
        return true;
    }

    /**
     * Add given element at the end of the list.
     * <p>
     * Time Complexity is O(n).
     * <p>
     * How to make this faster -
     * 1. Having tail pointer helps only in this case.
     *
     * @param element
     * @return
     */
    @Override
    public boolean addLast(final E element) {
        final Node<E> newNode = new Node<>(element);

        //  if list is empty
        if (this.head == null && this.tail == null) {
            this.head = this.tail = newNode;
            currentSize++;
            return true;
        }

        // this is where complexity becomes Big O(n),
        // because we dont have known reference to the last node.
        Node<E> tmpNode = this.head;
        while (null != tmpNode.next)
            tmpNode = tmpNode.next;

        tmpNode.next = newNode;
        tail = newNode;
        currentSize++;

        return true;
    }

    /**
     * Add given element at the end of the list.
     * <p>
     * Time Complexity is O(1);
     *
     * @param element
     * @return
     */
    @Override
    public boolean addLastWithTail(final E element) {
        Node<E> newNode = new Node<>(element);

        // if list is empty
        if (this.head == null && this.tail == null) {
            this.head = this.tail = newNode;
            currentSize++;
            return true;
        }

        tail.next = newNode;
        tail = newNode;
        currentSize++;

        return true;
    }

    /**
     * Add the given element at given position.
     * <p>
     * Time Complexity is O(n)
     *
     * @param element
     * @param position
     * @return
     */
    @Override
    public boolean addAtPosition(E element, int position) {
        // create a node
        Node<E> newNode = new Node<>(element);

        // check for boundary conditions
        if (this.isEmpty())
            return this.addFirst(element);

        if (position >= this.currentSize)
            return this.addLast(element);

        // loop through until you find the position.
        Node<E> current = this.head, previous = null;
        for (int i = 0; i < position - 1; i++) {
            previous = current;
            current = current.next;
        }

        newNode.next = current;
        previous.next = newNode;
        this.currentSize++;

        return true;
    }


    /**
     * Remove first element in the list.
     * <p>
     * Time Complexity is O(1).
     *
     * @return
     */
    @Override
    public E removeFirst() {
        // if list is empty
        if (this.head == null)
            return null;

        final Node<E> tmp = this.head;

        // if single item in the list.
        if (this.head == this.tail) {
            this.head = this.tail = null;
            currentSize--;
            return tmp.element;
        }

        // just move the head to next node.
        this.head = this.head.next;

        // make sure decrement size.
        currentSize--;
        return tmp.element;
    }

    /**
     * Remove last element in the list.
     * <p>
     * Time Complexity is O(n).
     *
     * <p>
     * How to make this faster -
     * 1. Doubly Linked List is the answer.
     * 2. Having tail pointer will "Not" helps because
     * maintaining a tail pointer will not give
     * previous node reference.
     *
     * @return
     */
    @Override
    public E removeLast() {

        //if list is empty
        if (this.head == null)
            return null;

        //if single item in the list
        if (this.head == this.tail)
            return this.removeFirst();

        Node<E> currentNode = this.head;
        Node<E> previousNode = null;
        // this is where complexity becomes Big O(n).
        while (currentNode.next != null) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        previousNode.next = null;
        //ensure tail pointer is updated.
        tail = previousNode;
        currentSize--;

        return currentNode.element;
    }

    /**
     * Remove element at position.
     *
     * @param position
     * @return
     */
    @Override
    public E removeAtPosition(int position) {
        // check for boundary conditions
        if (this.isEmpty())
            return null;

        if (position <= 0 && position > this.currentSize)
            return null;

        if (this.head.next == null) {
            Node<E> tmp = this.head;
            this.head = null;
            this.currentSize--;
            return tmp.element;
        }

        // loop through until you find the position.
        Node<E> current = this.head, previous = null;
        for (int i = 0; i < position - 1; i++) {
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        this.currentSize--;
        return current.element;
    }

    /**
     * Remove given element in the list.
     * <p>
     * Time Complexity is O(n).
     * <p>
     * How to make this faster -
     * there is no option, but to traverse through the list.
     *
     * @param element
     * @return
     */
    @Override
    public E remove(final E element) {
        // if list is empty
        if (this.head == null)
            return null;

        Node<E> currentNode = this.head;
        Node<E> previousNode = null;

        // this is where complexity becomes Big O(n).
        while (currentNode != null) {
            // first find the element.

            // we used Comparable to compare the data,
            // otherwise if we use == operator then we are comparing just 2 object's memory locations and not its data.
            // Data responsible to implement to CompareTo method.
            if (((Comparable<E>) currentNode.element).compareTo(element) == 0) {

                // if its head node
                if (currentNode == this.head)
                    return this.removeFirst();

                // if its tail node
                if (currentNode == this.tail)
                    return this.removeLast();

                // if its middle of node
                previousNode.next = currentNode.next;
                this.currentSize--;
                return currentNode.element;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    /**
     * Check whether given element contains in the list.
     * <p>
     * Time Complexity is O(n).
     * <p>
     * <p>
     * How to make this faster -
     * 1. You cant make this data structure any faster.
     * 2. Look for any other data structure.
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(final E element) {
        Node<E> tmpNode = this.head;
        // this is where complexity becomes Big O(n).
        while (null != tmpNode) {
            if (((Comparable<E>) tmpNode.element).compareTo(element) == 0)
                return true;
            tmpNode = tmpNode.next;
        }
        return false;
    }

    /**
     * Get the first element in the list.
     * <p>
     * Time Complexity is O(1).
     *
     * @return
     */
    @Override
    public E peekFirst() {
        //if list is empty
        if (this.head == null)
            return null;

        return this.head.element;
    }

    /**
     * Get the last element in the list.
     * <p>
     * Time Complexity is O(n).
     * <p>
     * How to make this faster -
     * - maintain tail pointer to know the last element.
     *
     * @return
     */
    @Override
    public E peekLast() {
        //if list is empty
        if (this.head == null)
            return null;

        //if list has one or more element.
        Node<E> tmpNode = this.head;
        while (tmpNode.next != null)
            tmpNode = tmpNode.next;

        return tmpNode.element;
    }

    /**
     * Get the last element in the list.
     * <p>
     * Time Complexity is O(1).
     *
     * @return
     */
    @Override
    public E peekLastWithTail() {
        //if list is empty
        if (this.head == null)
            return null;
        return this.tail.element;
    }

    /**
     * Print all the elements in the list.
     * <p>
     * Time Complexity - O(n)
     */
    public void printAllElementsInList() {
        Node<E> currentNode = this.head;
        while (currentNode != null) {
          //  System.out.print(currentNode.element + " --> ");
            currentNode = currentNode.next;
        }
        //System.out.print(" null \n");
    }
}
