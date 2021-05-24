package io.github.deepshiv126.practice.linear.data.structure.stack;

import io.github.deepshiv126.practice.data.structure.stack.StackInterface;
import io.github.deepshiv126.practice.linear.data.structure.list.arrays.DynamicArray;

import java.util.Stack;

/**
 * Stack - Last in First Out
 * <p>
 * Original java.util.Stack -
 * implements using Vector ( which is Dynamically grows or shrinks + Synchronized.)
 * <p>
 * My implementation use my own DynamicArray ( alot similar to Array List - grows + not synchronized.)
 */
public class StackUsingArray<E> implements StackInterface<E> {

    private DynamicArray<E> dynamicArray;

    public StackUsingArray() {
        this.dynamicArray = new DynamicArray<>();
    }

    /**
     * Size of Stack.
     *
     * @return size of the stack.
     */
    @Override
    public int size() {
        return this.dynamicArray.size();
    }

    /**
     * Check whether Stack is empty.
     *
     * @return true if empty.
     */
    @Override
    public boolean isEmpty() {
        return this.dynamicArray.isEmpty();
    }

    /**
     * Remove/Pop the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E pop() {
        // remove last element from Array.
        return this.dynamicArray.removeAt(this.dynamicArray.size() - 1);
    }

    /**
     * Add/Push the element to Top of the Stack
     *
     * @param element
     * @return
     */
    @Override
    public boolean push(E element) {
        return this.dynamicArray.add(element);
    }

    /**
     * Return the element from Top of the Stack.
     *
     * @return
     */
    @Override
    public E peek() {
        return this.dynamicArray.get(this.dynamicArray.size() - 1);
    }
}
