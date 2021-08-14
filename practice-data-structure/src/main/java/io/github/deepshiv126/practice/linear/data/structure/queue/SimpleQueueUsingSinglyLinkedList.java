package io.github.deepshiv126.practice.linear.data.structure.queue;

import io.github.deepshiv126.practice.data.structure.queue.Queue;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Queue -
 */
public class SimpleQueueUsingSinglyLinkedList<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    public SimpleQueueUsingSinglyLinkedList() {
        this.head = null;
        this.tail = null;
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
        return this.head == null && this.tail == null;
    }

    /**
     * Remove/Pop the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        //if queue has one element
        Node<E> tmpNode = this.head;
        if (this.head == this.tail) {
            this.head = this.tail = null;
            this.currentSize--;
            return tmpNode.element;
        }
        this.head = this.head.next;
        this.currentSize--;

        return tmpNode.element;
    }

    /**
     * Add/Push the element to Top of the Stack
     *
     * @return
     */
    @Override
    public boolean enqueue(final E element) {
        Node<E> newNode = new Node(element);
        //if stack is empty
        if (isEmpty()) {
            this.head = this.tail = newNode;
            this.currentSize++;
            return true;
        }

        //if stack has more than one element
        this.tail.next = newNode;
        this.tail = newNode;
        this.currentSize++;

        return true;
    }

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E peekFirst() {
        if (this.head == null)
            throw new EmptyStackException();

        return this.head.element;
    }

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E peekLast() {
        return null;
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
