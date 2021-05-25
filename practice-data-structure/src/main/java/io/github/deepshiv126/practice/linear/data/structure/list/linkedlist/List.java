package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;

/**
 * List Interface defining Basic operations.
 */
public interface List<E> {

    /**
     * Returns the number of elements count in the list.
     *
     * @return size of the list.
     */
    int size();


    /**
     * Returns whether this list is empty or not.
     *
     * @return
     */
    boolean isEmpty();

    /**
     * Add given element at the beginning of the list.
     *
     * @param element
     * @return true if added, false if not added.
     */
    boolean addFirst(E element);

    /**
     * Add given element at the end of the list.
     *
     * @param element
     * @return
     */
    boolean addLast(E element);

    /**
     * Add given element at the end of the list.
     *
     * @param element
     * @return
     */
    boolean addLastWithTail(E element);

    /**
     * Add given element at given position.
     *
     * @param element
     * @param position
     * @return
     */
    boolean addAtPosition(E element, int position);

    /**
     * Remove first element in the list.
     *
     * @return
     */
    E removeFirst();

    /**
     * Remove last element in the list.
     *
     * @return
     */
    E removeLast();

    /**
     * Remove element at position.
     *
     * @param position
     * @return
     */
    E removeAtPosition(int position);
    /**
     * Remove given element in the list.
     *
     * @param element
     * @return
     */
    E remove(E element);

    /**
     * Find whether given element contains in the list.
     *
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * Get the first element in the list.
     *
     * @return
     */
    E peekFirst();

    /**
     * Get the last element in the list.
     *
     * @return
     */
    E peekLast();

    /**
     * Get the last element in the list.
     * @return
     */
    E peekLastWithTail();
}
