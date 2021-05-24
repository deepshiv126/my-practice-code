package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Detect Loop in Singly Linked List.
 */
public class DetectLoopInSinglyLinkedListTest {

    @Test
    public void detectLoopEmptyList() {
        // check if list is empty
        DetectLoopInSinglyLinkedList singlyLinkedList = new DetectLoopInSinglyLinkedList();
        Assert.assertNull(singlyLinkedList.head);
    }

    @Test
    public void detectLoopSingleElement() {
        DetectLoopInSinglyLinkedList singlyLinkedList = new DetectLoopInSinglyLinkedList();

        // check if list has single element
        singlyLinkedList.createSingleElementList();
        Assert.assertFalse(singlyLinkedList.detectLoop());
    }

    @Test
    public void detectLoop() {
        DetectLoopInSinglyLinkedList singlyLinkedList = new DetectLoopInSinglyLinkedList();
        // check if list has more than one element.
        // check if it has a loop
        singlyLinkedList.createLoop(true);
        Assert.assertTrue(singlyLinkedList.detectLoop());
    }

    @Test
    public void detectNoLoop() {
        DetectLoopInSinglyLinkedList singlyLinkedList = new DetectLoopInSinglyLinkedList();
        // check if it has No Loop
        singlyLinkedList.createLoop(false);
        Assert.assertFalse(singlyLinkedList.detectLoop());
    }

}
