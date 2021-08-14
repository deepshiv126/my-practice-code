package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;

import java.util.NoSuchElementException;

/**
 * Doubly Linked List is a linear data structure used for storing collections of nodes.
 * - It contains sequence of node
 * - A node has data and reference to previous and next node in a list.
 * - First node is the head node and its previous is always null.
 * - Last node has data and points to null.
 * <p>
 * Benefits over Singly Linked List
 * - A node - can navigate forward and backward ( this helps a lot in delete node,
 * as we dont need any previous pointer like we had in Singly Linked List.)
 * - Add and Remove last element will be O(1) Time Complexity.
 */
public class DoublyLinkedList<E> implements List<E> {

    // Doubly link list also maintains both head and tail.
    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    // constructor creating head and tail
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }

    /**
     * Returns the number of elements count in the list.
     * <p>
     * Time Complexity - O(1)
     *
     * @return size of the list.
     */
    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Returns whether this list is empty or not.
     * <p>
     * Time Complexity is O(1)
     *
     * @return true/false.
     */
    @Override
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    /**
     * Add given element at the beginning of the list.
     * <p>
     * Time Complexity is O(1)
     *
     * @param element
     * @return true if added, false if not added.
     */
    @Override
    public boolean addFirst(final E element) {
        Node<E> newNode = new Node<>(element);

        // if list is empty
        if (this.isEmpty()) {
            this.head = this.tail = newNode;
            currentSize++;
            return true;
        }
        this.head.previous = newNode; // this is only change from sll
        newNode.next = this.head;
        this.head = newNode;
        currentSize++;

        return true;
    }

    /**
     * Add given element at the end of the list.
     * <p>
     * Time Complexity is O(1)
     *
     * @param element
     * @return
     */
    @Override
    public boolean addLast(final E element) {
        Node<E> newNode = new Node<>(element);

        //if list is empty
        if (this.isEmpty()) {
            this.head = this.tail = newNode;
            currentSize++;
            return true;
        }

        this.tail.next = newNode;
        newNode.previous = this.tail;
        this.tail = newNode;
        currentSize++;

        return true;
    }

    /**
     * Add given element at the end of the list.
     * This method applicable only to SinglyLinkedList
     *
     * @param element
     * @return
     */
    @Override
    public boolean addLastWithTail(final E element) {
        return false;
    }

    /**
     * Add given element at given position.
     *
     * @param element
     * @param position
     * @return
     */
    @Override
    public boolean addAtPosition(E element, int position) {
        return false;
    }

    /**
     * Remove first element in the list.
     * <p>
     * Time Complexity is O(1)
     *
     * @return
     */
    @Override
    public E removeFirst() {
        // if list is empty
        if (this.head == null && this.tail == null) {
            throw new NoSuchElementException();
        }

        Node<E> tmpNode = this.head;
        //if list has one element
        if (this.head == this.tail) {
            this.head = this.tail = null;
            currentSize--;
            return tmpNode.element;
        }

        // if list has more than one elements.
        this.head.next.previous = null;
        this.head = this.head.next;
        // tmpNode never gets GC if next is not null.
        tmpNode.next = null;
        currentSize--;

        return tmpNode.element;
    }

    /**
     * Remove last element in the list.
     * <p>
     * Time Complexity is O(1)
     *
     * @return
     */
    @Override
    public E removeLast() {
        // if list is empty
        if (this.head == null && this.tail == null) {
            throw new NoSuchElementException();
        }

        Node<E> tmpNode = this.tail;
        // if list has one element
        if (this.head == this.tail) {
            this.head = this.tail = null;
            currentSize--;
            return tmpNode.element;
        }
        // if list has more than one element
        this.tail.previous.next = null;
        this.tail = this.tail.previous;
        tmpNode.previous = null; // need to lose reference, other wise node never get GC.
        currentSize--;

        return tmpNode.element;
    }

    /**
     * Remove element at position.
     *
     * @param position
     * @return
     */
    @Override
    public E removeAtPosition(int position) {
        return null;
    }

    /**
     * Remove given element in the list.
     * <p>
     * Time Complexity is O(n)
     *
     * @param element
     * @return
     */
    @Override
    public E remove(final E element) {
        // if list is empty
        if (this.head == null && this.tail == null) {
            throw new NoSuchElementException();
        }
        // if list has single element
        if (this.head == this.tail) {
            if (this.head.element == element) {
                Node<E> tmpNode = this.head;
                this.head = this.tail = null;
                return tmpNode.element;
            }
            return null;
        }

        // if list has more than one element
        Node<E> tmpNode = this.head;
        while (tmpNode != null) {
            if (tmpNode.element == element) {
                tmpNode.previous.next = tmpNode.next;
                tmpNode.next.previous = tmpNode.previous;
                return tmpNode.element;
            }
            tmpNode = tmpNode.next;
        }
        return null;
    }

    /**
     * Find whether given element contains in the list.
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(final E element) {
        //if list is empty
        if (this.head == null && this.tail == null) {
            throw new NoSuchElementException();
        }
        //if list is single element
        if (this.head == this.tail) {
            if (this.head.element == element)
                return true;
            return false;
        }

        // if list has more than one element
        Node<E> tmpNode = this.head;
        while (tmpNode != null) {
            if (tmpNode.element == element) {
                return true;
            }
            tmpNode = tmpNode.next;
        }
        return false;
    }

    /**
     * Get the first element in the list.
     *
     * @return
     */
    @Override
    public E peekFirst() {
        if (this.head == null && this.tail == null)
            throw new NoSuchElementException();
        return this.head.element;
    }

    /**
     * Get the last element in the list.
     *
     * @return
     */
    @Override
    public E peekLast() {
        if (this.head == null && this.tail == null)
            throw new NoSuchElementException();
        return this.tail.element;
    }

    /**
     * Get the last element in the list.
     * This method applicable only to SinglyLinkedList
     *
     * @return
     */
    @Override
    public E peekLastWithTail() {
        return null;
    }


    /**
     * Only for Visual Understanding
     */
    public void printAllElementsInList() {
        Node<E> tmpNode;

        // printing forward direction.
        //System.out.print("Forward :: ");
        tmpNode = this.head;
        while (tmpNode != null) {
            //System.out.print(tmpNode.element + "  -->  ");
            tmpNode = tmpNode.next;
        }
        //System.out.println("null");

        // printing reverse direction.
        //System.out.print("Backward :: ");
        tmpNode = this.tail;
        while (tmpNode != null) {
            // System.out.print(tmpNode.element + "  -->  ");
            tmpNode = tmpNode.previous;
        }
        //System.out.println("null");
    }

    /**
     * Definition of Doubly Linked List Node.
     */
    private static class Node<E> {
        E element;
        Node<E> previous;
        Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.previous = null;
            this.next = null;
        }
    }

}
