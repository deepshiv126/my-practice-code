package io.github.deepshiv126.practice.data.structure.queue;

/**
 * Stack Interface defining the basic ops definition.
 */
public interface Queue<E> {
    /**
     * Size of Stack.
     *
     * @return size of the stack.
     */
    int size();

    /**
     * Check whether Stack is empty.
     *
     * @return true if empty.
     */
    boolean isEmpty();

    /**
     * Remove/Pop the element from Top of the Stack.
     *
     * @return
     */
    E dequeue();

    /**
     * Add/Push the element to Top of the Stack
     *
     * @return
     */
    boolean enqueue(final E element);

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    E peekFirst();

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    E peekLast();
}
