package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;

import java.util.NoSuchElementException;

/**
 * Circular Singly Linked List is a linear data structure similar to Singly Linked List,
 * with a difference that in Circular Singly Linked List, the last node points to
 * first node instead of null.
 * <p>
 * Circular Singly Linked List keep track of last node, instead of head like in Singly Linked List.
 */
public class CircularSinglyLinkedList<E> implements List<E> {

    // Circular Singly linked list maintains last instead of head.
    private Node<E> last;
    private int currentSize;

    public CircularSinglyLinkedList() {
        this.last = null;
        this.currentSize = 0;
    }

    /**
     * Returns the number of elements count in the list.
     *
     * @return size of the list.
     */
    @Override

    public int size() {
        return this.currentSize;
    }

    /**
     * Returns whether this list is empty or not.
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.last == null;
    }

    /**
     * Add given element at the beginning of the list.
     *
     * @param element
     * @return true if added, false if not added.
     */
    @Override
    public boolean addFirst(final E element) {
        Node<E> newNode = new Node(element);

        // if list is empty
        if (this.last == null) {
            this.last = newNode;
            this.last.next = newNode;
            currentSize++;
            return true;
        }

        // if list has one or more elements
        newNode.next = this.last.next;
        this.last.next = newNode;
        currentSize++;

        return true;
    }

    /**
     * Add given element at the end of the list.
     *
     * @param element
     * @return
     */
    @Override
    public boolean addLast(final E element) {
        Node<E> newNode = new Node(element);

        // if list is empty
        if (this.last == null) {
            this.last = newNode;
            this.last.next = newNode;
            currentSize++;

            return true;
        }

        // if list has one or more elements
        newNode.next = this.last.next;
        this.last.next = newNode;
        this.last = newNode; // moving last pointer is only difference in addFirst and addLast.
        currentSize++;

        return true;
    }

    /**
     * Add given element at the end of the list.
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
     *
     * @return
     */
    @Override
    public E removeFirst() {
        // if list is empty
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        // if list has one element
        Node<E> tmpNode = this.last.next;
        if (this.last.next == this.last) {
            this.last = null;
            currentSize--;
            return tmpNode.element;
        }

        // if list has more than one element
        this.last.next = tmpNode.next;
        tmpNode.next = null;
        currentSize--;

        return tmpNode.element;
    }

    /**
     * Remove last element in the list.
     *
     * @return
     */
    @Override
    public E removeLast() {
        // if list is empty
        if (this.last == null) {
            throw new NoSuchElementException();
        }

        Node<E> tmpNode = this.last;

        // if list has single element
        if (this.last.next == this.last) {
            this.last = null;
            currentSize--;
            return tmpNode.element;
        }

        // if list has more than one element
        // loop until you find last but previous node.
        // this is where it gets O(n) complexity
        while (tmpNode.next != this.last)
            tmpNode = tmpNode.next;

        E output = this.last.element;
        tmpNode.next = this.last.next;
        this.last = tmpNode;

        return output;
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
     *
     * @param element
     * @return
     */
    @Override
    public E remove(final E element) {
        // if list is empty
        if (this.last == null) {
            throw new NoSuchElementException();
        }

        Node<E> tmpNode = this.last.next;
        Node<E> previousNode = null;

        // if list has single element
        if (this.last == this.last.next) {
            if (this.last.element == element) {
                this.last = null;
                currentSize--;
                return tmpNode.element;
            }
        }

        // if list has more than one element
        while (tmpNode != this.last) {
            if (tmpNode.element == element) {
                previousNode.next = tmpNode.next;
                tmpNode.next = null;
                currentSize--;
                return tmpNode.element;
            }
            previousNode = tmpNode;
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
        // if list is empty
        if (this.last == null) {
            throw new NoSuchElementException();
        }

        // if list has one element
        Node<E> tmpNode = this.last.next;
        if (this.last == this.last.next) {
            if (this.last.element == element)
                return true;
            return false;
        }

        // if list has more than one element
        while (tmpNode != this.last) {
            if (tmpNode.element == element)
                return true;
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
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        return this.last.next.element;
    }

    /**
     * Get the last element in the list.
     *
     * @return
     */
    @Override
    public E peekLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
        return this.last.element;
    }

    /**
     * Get the last element in the list.
     *
     * @return
     */
    @Override
    public E peekLastWithTail() {
        return null;
    }

    /**
     * For Visual Understanding.
     */
    public void printAllElements() {
        Node<E> tmpNode = this.last.next;
        if (this.last == null) {
            return;
        }
        while (tmpNode != this.last) {
            //System.out.print(tmpNode.element + "  -->  ");
            tmpNode = tmpNode.next;
        }
        //System.out.println(tmpNode.element + " :: end of list.");
    }

    /**
     * Definition of Node.
     *
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }
}
