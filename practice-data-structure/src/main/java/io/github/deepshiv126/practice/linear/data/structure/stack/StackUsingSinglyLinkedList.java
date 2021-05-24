package io.github.deepshiv126.practice.linear.data.structure.stack;

import io.github.deepshiv126.practice.data.structure.stack.StackInterface;

import java.util.EmptyStackException;

/**
 * Stack - Last In First Out
 *
 *  Best Linear data structure to use to implement Stack
 *         - Array
 *                 - addLast - O(1)
 *                 - removeLast - O(1)
 *         - Singly Linked List
 *                 - addFirst - O(1)
 *                 - removeFirst - O(1)
 *         - Doubly Linked List
 *                 - addLast - O(1)
 *                 - removeLast - O(1)
 *                 or
 *                 - addFirst - O(1)
 *                 - removeFirst - O(1)
 *         - Circular Singly Linked List
 *                 - addLast - O(1)
 *                 - removeLast - O(1)

 */
public class StackUsingSinglyLinkedList<E> implements StackInterface<E> {

    private Node<E> top;
    private int currentSize;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(final E element) {
            this.element = element;
            this.next = null;
        }
    }

    public StackUsingSinglyLinkedList() {
        this.top = null;
        this.currentSize = 0;
    }

    /**
     * Size of Stack.
     *
     * @return size of the stack.
     */
    @Override
    public int size() {
        return this.currentSize;
    }

    /**
     * Check whether Stack is empty.
     *
     * @return true if empty.
     */
    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    /**
     * Remove/Pop the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E pop() {
        if (this.top == null)
            throw new EmptyStackException();

        Node<E> tmpNode = this.top;
        this.top = this.top.next;
        this.currentSize--;

        return tmpNode.element;
    }

    /**
     * Add/Push the element to Top of the Stack
     *
     * @return
     */
    @Override
    public boolean push(final E element) {
        Node<E> newNode = new Node(element);

        //if stack is empty
        if (this.top == null) {
            this.top = newNode;
            this.currentSize++;
            return true;
        }
        //if stack has more than one element
        newNode.next = this.top;
        this.top = newNode;
        this.currentSize++;
        return true;
    }

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E peek() {
        if (this.top == null)
            throw new EmptyStackException();

        return this.top.element;
    }

}
