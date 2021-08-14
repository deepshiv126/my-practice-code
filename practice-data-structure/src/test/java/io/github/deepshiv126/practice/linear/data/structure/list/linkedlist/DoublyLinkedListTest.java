package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

/**
 * Test Doubly Linked List Class.
 * I have tried up to 80% code coverage.
 */
public class DoublyLinkedListTest {
    @Test
    public void size() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        Assert.assertEquals(0, integerDoublyLinkedList.size());
    }

    @Test
    public void isEmpty() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        Assert.assertTrue(integerDoublyLinkedList.isEmpty());

        integerDoublyLinkedList.addFirst(10);
        Assert.assertFalse(integerDoublyLinkedList.isEmpty());
    }


    @Test
    public void addFirst() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        Assert.assertTrue(integerDoublyLinkedList.addFirst(10));
        integerDoublyLinkedList.addFirst(11);
        integerDoublyLinkedList.addFirst(12);
        Assert.assertEquals(new Integer(12), integerDoublyLinkedList.peekFirst());

        // for visual understanding
        //integerDoublyLinkedList.printAllElementsInList();
    }

    @Test
    public void addLast() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        Assert.assertTrue(integerDoublyLinkedList.addLast(10));
        integerDoublyLinkedList.addLast(11);
        integerDoublyLinkedList.addLast(12);

        Assert.assertEquals(new Integer(12), integerDoublyLinkedList.peekLast());

        // for visual understanding
        //integerDoublyLinkedList.printAllElementsInList();
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void removeFirstWithEmpytList() throws Exception {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        // it will throw exceptions
        integerDoublyLinkedList.removeFirst();
    }

    @Test
    public void removeFirst() throws Exception {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.addFirst(10);
        Assert.assertEquals(new Integer(10), integerDoublyLinkedList.removeFirst());

        integerDoublyLinkedList.addFirst(10);
        integerDoublyLinkedList.addFirst(11);
        integerDoublyLinkedList.addFirst(12);

        //integerDoublyLinkedList.printAllElementsInList();
        Assert.assertEquals(new Integer(12), integerDoublyLinkedList.removeFirst());
        //integerDoublyLinkedList.printAllElementsInList();
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void removeLastWithEmptyList() throws Exception {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.removeLast();
    }

    @Test
    public void removeLast() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.addLast(10);
        Assert.assertEquals(new Integer(10), integerDoublyLinkedList.removeLast());


        integerDoublyLinkedList.addLast(10);
        integerDoublyLinkedList.addLast(11);
        integerDoublyLinkedList.addLast(12);
        integerDoublyLinkedList.addLast(13);

        //integerDoublyLinkedList.printAllElementsInList();
        Assert.assertEquals(new Integer(13), integerDoublyLinkedList.removeLast());
        //integerDoublyLinkedList.printAllElementsInList();
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void removeWithEmptyList() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.remove(99999);
    }

    @Test
    public void remove() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.addLast(10);
        Assert.assertEquals(new Integer(10), integerDoublyLinkedList.remove(10));

        integerDoublyLinkedList.addLast(10);
        integerDoublyLinkedList.addLast(11);
        integerDoublyLinkedList.addLast(12);
        integerDoublyLinkedList.addLast(13);

        //integerDoublyLinkedList.printAllElementsInList();
        Assert.assertEquals(new Integer(12), integerDoublyLinkedList.remove(12));
        Assert.assertNull(integerDoublyLinkedList.remove(99999));
        //integerDoublyLinkedList.printAllElementsInList();
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void containsWithEmptyList() throws Exception {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.contains(10);
    }

    @Test
    public void contains() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.addLast(10);
        Assert.assertTrue(integerDoublyLinkedList.contains(10));
        Assert.assertFalse(integerDoublyLinkedList.contains(11));

        integerDoublyLinkedList.addLast(11);
        integerDoublyLinkedList.addLast(12);
        integerDoublyLinkedList.addLast(13);
        integerDoublyLinkedList.addLast(14);
        Assert.assertTrue(integerDoublyLinkedList.contains(13));
        Assert.assertFalse(integerDoublyLinkedList.contains(100000));

    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void peekFirstWitheEmptyList() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.peekFirst();
    }

    @Test
    public void peekFirst() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.addLast(10);
        Assert.assertEquals(new Integer(10), integerDoublyLinkedList.peekFirst());

        integerDoublyLinkedList.addLast(11);
        integerDoublyLinkedList.addLast(12);
        integerDoublyLinkedList.addLast(13);
        integerDoublyLinkedList.addLast(14);

        Assert.assertEquals(new Integer(10), integerDoublyLinkedList.peekFirst());
        Assert.assertNotEquals(new Integer(14), integerDoublyLinkedList.peekFirst());
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void peekLastWitheEmptyList() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.peekLast();
    }

    @Test
    public void peekLast() {
        DoublyLinkedList<Integer> integerDoublyLinkedList = new DoublyLinkedList<>();
        integerDoublyLinkedList.addLast(10);
        Assert.assertEquals(new Integer(10), integerDoublyLinkedList.peekLast());

        integerDoublyLinkedList.addLast(11);
        integerDoublyLinkedList.addLast(12);
        integerDoublyLinkedList.addLast(13);
        integerDoublyLinkedList.addLast(14);

        Assert.assertEquals(new Integer(14), integerDoublyLinkedList.peekLast());
        Assert.assertNotEquals(new Integer(10), integerDoublyLinkedList.peekLast());
    }


}
