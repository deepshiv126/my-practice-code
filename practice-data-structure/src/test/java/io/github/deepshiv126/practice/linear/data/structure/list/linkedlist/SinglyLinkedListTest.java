package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Singly Linked List Class.
 * I have tried up to 80% code coverage.
 */
public class SinglyLinkedListTest {

    @Test
    public void size() {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        // empty list
        Assert.assertNotNull(singlyLinkedList.size());
        Assert.assertEquals(0, singlyLinkedList.size());

        // non empty list
        singlyLinkedList.addFirst(10);
        Assert.assertEquals(1, singlyLinkedList.size());
    }

    @Test
    public void isEmpty() {
        //empty list
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        Assert.assertTrue(singlyLinkedList.isEmpty());

        // non empty list.
        singlyLinkedList.addFirst(10);
        Assert.assertFalse(singlyLinkedList.isEmpty());
    }

    @Test
    void addFirst() {
        SinglyLinkedList<Integer> integerSinglyLinkedList = new SinglyLinkedList<>();
        // add a series of item at beginning of the list.
        integerSinglyLinkedList.addFirst(10);
        integerSinglyLinkedList.addFirst(11);
        integerSinglyLinkedList.addFirst(12);

        // asert the size
        Assert.assertEquals(3, integerSinglyLinkedList.size());

        // assert first element in list.
        Assert.assertEquals(new Integer(12), integerSinglyLinkedList.peekFirst());
    }

    @Test
    public void addLast() {
        SinglyLinkedList<Integer> integerSinglyLinkedList = new SinglyLinkedList<>();
        // add a series of item at end of the list.
        integerSinglyLinkedList.addLast(10);
        integerSinglyLinkedList.addLast(11);
        integerSinglyLinkedList.addLast(12);

        // assert the size.
        Assert.assertEquals(3, integerSinglyLinkedList.size());

        // assert the last item
        Assert.assertEquals(new Integer(12), integerSinglyLinkedList.peekLast());
    }

    @Test
    void addLastWithTail() {
        SinglyLinkedList<Integer> integerSinglyLinkedList = new SinglyLinkedList<>();
        // add a series of item at end of the list.
        integerSinglyLinkedList.addLastWithTail(10);
        integerSinglyLinkedList.addLastWithTail(11);
        integerSinglyLinkedList.addLastWithTail(12);

        // assert the size
        Assert.assertEquals(3, integerSinglyLinkedList.size());

        // assert last added element in the list.
        Assert.assertEquals(new Integer(12), integerSinglyLinkedList.peekLastWithTail());
    }

    @Test
    void addAtPosition() {
        SinglyLinkedList<Integer> integerSinglyLinkedList = new SinglyLinkedList<>();
        // add a series of item at end of the list.
        integerSinglyLinkedList.addAtPosition(10, 1);
        integerSinglyLinkedList.addAtPosition(11, 2);
        integerSinglyLinkedList.addAtPosition(12, 3);
        integerSinglyLinkedList.addAtPosition(13, 2);

        // assert the size
        Assert.assertEquals(4, integerSinglyLinkedList.size());

        // assert last added element in the list.
        Assert.assertEquals(new Integer(12), integerSinglyLinkedList.peekLastWithTail());

        integerSinglyLinkedList.printAllElementsInList();
    }


    @Test
    void removeFirst() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.removeFirst());

        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();
        while (integerSinglyLinkedList.size() != 0) {
            // remove one by one element, but assert it.
            Assert.assertEquals(integerSinglyLinkedList.peekFirst(), integerSinglyLinkedList.removeFirst());
        }
    }


    @Test
    void removeLast() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.removeLast());

        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();
        while (integerSinglyLinkedList.size() != 0) {
            // remove one by one element, but assert each one of it.
            Assert.assertEquals(integerSinglyLinkedList.peekLast(), integerSinglyLinkedList.removeLast());
        }
    }


    @Test
    void removeAtPosition() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.removeAtPosition(0));

        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();
        integerSinglyLinkedList.printAllElementsInList();
        while (integerSinglyLinkedList.size() != 0) {
            // remove one by one element, but assert each one of it.
            Assert.assertEquals(integerSinglyLinkedList.peekLast(), integerSinglyLinkedList.removeAtPosition(integerSinglyLinkedList.size()));
            integerSinglyLinkedList.printAllElementsInList();
        }
    }

    @Test
    public void remove() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.remove(10));

        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();

        //remove first item
        Assert.assertEquals(new Integer(10), integerSinglyLinkedList.remove(10));

        //remove middle element
        Assert.assertEquals(new Integer(13), integerSinglyLinkedList.remove(13));

        //remove last element
        Assert.assertEquals(new Integer(14), integerSinglyLinkedList.remove(14));

        // remove non existing element
        Assert.assertEquals(null, integerSinglyLinkedList.remove(1000));
    }

    @Test
    void contains() {
        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();

        Assert.assertTrue(integerSinglyLinkedList.contains(10));
        Assert.assertFalse(integerSinglyLinkedList.contains(100));
    }

    @Test
    public void peekFirst() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.peekFirst());


        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();
        // assert first element.
        Assert.assertEquals(new Integer(10), integerSinglyLinkedList.peekFirst());

        // assert non-first element.
        Assert.assertNotEquals(new Integer(12), integerSinglyLinkedList.peekFirst());
    }

    @Test
    public void peekLast() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.peekLast());

        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();
        //assert last element.
        Assert.assertEquals(new Integer(14), integerSinglyLinkedList.peekLast());

        //assert non-last element
        Assert.assertNotEquals(new Integer(10), integerSinglyLinkedList.peekLast());
    }

    @Test
    public void peekLastWithTail() {
        // empty list
        SinglyLinkedList<Integer> emptyIntegerSinglyLL = new SinglyLinkedList<>();
        Assert.assertNull(emptyIntegerSinglyLL.peekLastWithTail());

        SinglyLinkedList<Integer> integerSinglyLinkedList = getIntegerSinglyLinkedListWithData();

        //assert last element.
        Assert.assertEquals(new Integer(14), integerSinglyLinkedList.peekLastWithTail());

        //assert non-last element
        Assert.assertNotEquals(new Integer(10), integerSinglyLinkedList.peekLastWithTail());
    }

    private SinglyLinkedList<Integer> getIntegerSinglyLinkedListWithData() {
        SinglyLinkedList<Integer> integerSinglyLinkedList = new SinglyLinkedList<>();
        integerSinglyLinkedList.addLastWithTail(10);
        integerSinglyLinkedList.addLastWithTail(11);
        integerSinglyLinkedList.addLastWithTail(12);
        integerSinglyLinkedList.addLastWithTail(13);
        integerSinglyLinkedList.addLastWithTail(14);

        //just testing the size.
        Assert.assertEquals(5, integerSinglyLinkedList.size());

        // printing for visual understanding.
        //integerSinglyLinkedList.printAllElementsInList();

        return integerSinglyLinkedList;
    }

}
