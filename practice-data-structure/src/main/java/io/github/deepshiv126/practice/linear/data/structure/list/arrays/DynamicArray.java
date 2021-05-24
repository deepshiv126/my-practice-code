package io.github.deepshiv126.practice.linear.data.structure.list.arrays;

import java.util.Iterator;

/**
 * A generic dynamic array - implemented using static array and keeps double the size when it reaches its limit.
 *
 * @author deepak shivanandappa
 */
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int length = 0;
    private int capacity = 0;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(final int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    // size of array O(1) complexity
    public int size() {
        return this.length;
    }

    // empty check O(1) complexity
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // get an element at position - O(1) complexity
    public T get(final int index) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        return this.array[index];
    }

    // set an element at position - O(1) complexity
    public void set(final T element, final int index) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        this.array[index] = element;
    }

    // add an element - O(1) or O(n) complexity
    public boolean add(final T element) {
        // if array is about get full, then double the size
        if (this.length + 1 >= this.capacity) {
            // if array is created with zero capacity.
            if (this.capacity == 0) this.capacity = 1;
            else this.capacity *= 2;
            // create a new array and copy old array to new array
            T[] newArray = (T[]) new Object[this.capacity];
            for (int i = 0; i < this.length; i++) newArray[i] = this.array[i];
            this.array = newArray;
        }
        // append and incremented the size
        this.array[this.length++] = element;
        return false;
    }

    // remove an element at position ( use 2 pointer system ) - O(n) complexity
    public T removeAt(final int index) {
        if (index >= this.length || index < 0) throw new IndexOutOfBoundsException();
        T originalData = this.array[index];
        T[] newArray = (T[]) new Object[this.capacity];
        for (int i = 0, j = 0; i < this.length; i++, j++) {
            // i track original array, j tracks new array
            if (i == index) j--; // j takes one step back at remove index
            else newArray[j] = this.array[i];
            this.array = newArray;
            this.length--;
        }
        return originalData;
    }

    // index of an element - O(n) complexity
    public int indexOf(final T element) {
        if (element != null) {
            for (int i = 0; i < this.length; i++) {
                if (this.array[i].equals(element))
                    return i;
            }
        }
        return -1;
    }

    // remove an element  - O(2n)/O(n) complexity
    public boolean remove(final T element) {
        int index = this.indexOf(element);
        if (index == -1) return false;
        removeAt(index);
        return true;
    }

    // clear array -  - O(n) complexity
    public void clear() {
        for (int i = 0; i < this.length; i++)
            this.array[i] = null;
        this.length = 0;
    }

    // contains an element -  - O(n) complexity
    public boolean contains(final T element) {
        return indexOf(element) != -1;
    }


    // Iterator to loop through the array.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }
}
