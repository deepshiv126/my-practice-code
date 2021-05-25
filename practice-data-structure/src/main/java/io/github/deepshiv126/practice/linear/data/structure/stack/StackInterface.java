package io.github.deepshiv126.practice.data.structure.stack;

/**
 * Stack Interface defining the basic ops definition.
 */
public interface StackInterface<E> {
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
    E pop();

    /**
     * Add/Push the element to Top of the Stack
     *
     * @return
     */
    boolean push(final E element);

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    E peek();
}
