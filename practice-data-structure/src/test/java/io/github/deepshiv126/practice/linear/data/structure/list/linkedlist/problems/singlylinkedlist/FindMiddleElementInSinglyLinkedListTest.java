package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Find the middle element in the singly linked list
 */
public class FindMiddleElementInSinglyLinkedListTest {

    @Test
    public void findMiddleElement() {

        // check if list is empty
        FindMiddleElementInSinglyLinkedList<Integer> singlyLinkedList = new FindMiddleElementInSinglyLinkedList<>();
        Assert.assertNull(singlyLinkedList.findMiddleElementInSinglyLinkedList());

        // check if list has 1 item
        singlyLinkedList.addLast(10);
        Assert.assertEquals(new Integer(10), singlyLinkedList.findMiddleElementInSinglyLinkedList());

        // check if list has more than 1 item
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(12);
        singlyLinkedList.addLast(13);
        singlyLinkedList.addLast(14);
        Assert.assertEquals(new Integer(12), singlyLinkedList.findMiddleElementInSinglyLinkedList());
        Assert.assertNotEquals(new Integer(14), singlyLinkedList.findMiddleElementInSinglyLinkedList());
    }
}
