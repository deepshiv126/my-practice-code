package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Remove Duplicates from Sorted Singly Linked List.
 */
public class RemoveDuplicatesFromSortedSinglyLinkedListTest {

    @Test
    public void removeDuplicates() {

        // if list is empty
        RemoveDuplicatesFromSortedSinglyLinkedList<Integer> singlyLinkedList = new RemoveDuplicatesFromSortedSinglyLinkedList<>();
        Assert.assertFalse(singlyLinkedList.removeDuplicates());

        // if list has 1 element
        singlyLinkedList.addLast(10);
        Assert.assertFalse(singlyLinkedList.removeDuplicates());

        // if list has more than 1 element
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(11);
        singlyLinkedList.addLast(13);
        singlyLinkedList.addLast(14);

        //for visual understanding
        //singlyLinkedList.printAllElements();

        Assert.assertTrue(singlyLinkedList.removeDuplicates());

        //singlyLinkedList.printAllElements();
    }
}
