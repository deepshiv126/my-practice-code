package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;

import java.util.NoSuchElementException;

import io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.CircularSinglyLinkedList;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Ciruclar Singly Linked List Class.
 * I have tried up to 80% code coverage.
 */
public class CircularSinglyLinkedListTest {

    @Test
    public void size() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        Assert.assertEquals(0, singlyLinkedList.size());
    }

    @Test
    public void isEmpty() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        Assert.assertTrue(singlyLinkedList.isEmpty());
    }

    @Test
    public void addFirst() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addFirst(10);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(1, singlyLinkedList.size());

        singlyLinkedList.addFirst(11);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(2, singlyLinkedList.size());

        singlyLinkedList.addFirst(12);
        singlyLinkedList.addFirst(13);
        singlyLinkedList.addFirst(14);
        singlyLinkedList.addFirst(15);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(6, singlyLinkedList.size());

    }

    @Test
    public void addLast() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(1, singlyLinkedList.size());

        singlyLinkedList.addLast(11);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(2, singlyLinkedList.size());

        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);
        singlyLinkedList.addLast(14);
        singlyLinkedList.addLast(15);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(6, singlyLinkedList.size());
    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void removeFirstWithEmptyList() throws Exception {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.removeFirst();
    }

    @Test
    public void removeFirst() throws Exception {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);
        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(10), singlyLinkedList.removeFirst());

        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);
        singlyLinkedList.addLast(14);
        singlyLinkedList.addLast(15);
        //singlyLinkedList.printAllElements();

        Assert.assertEquals(new Integer(11), singlyLinkedList.removeFirst());
        //singlyLinkedList.printAllElements();

    }

    @Test(expectedExceptions = {NoSuchElementException.class})
    public void removeLastWithEmptyList() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.removeLast();
    }

    @Test
    public void removeLast() {
        // if single element list
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);

        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(10), singlyLinkedList.removeLast());

        singlyLinkedList.addLast(10);
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);

        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(13), singlyLinkedList.removeLast());
        //singlyLinkedList.printAllElements();
    }


    @Test(expectedExceptions = {NoSuchElementException.class})
    public void removeWithEmptyList() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.remove(99999);
    }

    @Test
    public void remove() {
        // if single element list
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);

        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(10), singlyLinkedList.remove(10));

        singlyLinkedList.addLast(10);
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);

        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(12), singlyLinkedList.remove(12));
        Assert.assertNull(singlyLinkedList.remove(9999999));
        //singlyLinkedList.printAllElements();
    }


    @Test(expectedExceptions = {NoSuchElementException.class})
    public void containsWithEmptyList() {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.contains(99999);
    }

    @Test
    public void contains() {
        // if single element list
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);

        //singlyLinkedList.printAllElements();
        Assert.assertTrue(singlyLinkedList.contains(10));
        Assert.assertFalse(singlyLinkedList.contains(100000));

        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);

        //singlyLinkedList.printAllElements();
        Assert.assertTrue(singlyLinkedList.contains(12));
        Assert.assertFalse(singlyLinkedList.contains(99999));
        //singlyLinkedList.printAllElements();
    }


    @Test(expectedExceptions = {NoSuchElementException.class})
    public void peekFirstWithEmptyList() throws Exception {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.peekFirst();
    }

    @Test
    public void peekFirst() {
        // if single element list
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);

        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(10), singlyLinkedList.peekFirst());
        //singlyLinkedList.printAllElements();
    }


    @Test(expectedExceptions = {NoSuchElementException.class})
    public void peekLastWithEmptyList() throws Exception {
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.peekLast();
    }

    @Test
    public void peekLast() {
        // if single element list
        CircularSinglyLinkedList<Integer> singlyLinkedList = new CircularSinglyLinkedList<>();
        singlyLinkedList.addLast(10);
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);

        //singlyLinkedList.printAllElements();
        Assert.assertEquals(new Integer(13), singlyLinkedList.peekLast());
        //singlyLinkedList.printAllElements();
    }
}
