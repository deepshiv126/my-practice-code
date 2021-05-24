package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Insert a new Node into Sorted Singly Linked List.
 */
public class InsertNodeIntoSortSinglyLinkedListTest {

    @Test
    public void add() {
        InsertNodeIntoSortSinglyLinkedList singlyLinkedList = new InsertNodeIntoSortSinglyLinkedList();
        Assert.assertTrue(singlyLinkedList.add(5));

        singlyLinkedList.add(6);
        singlyLinkedList.add(1);
        singlyLinkedList.add(4);
        singlyLinkedList.add(3);

        singlyLinkedList.add(2);
        singlyLinkedList.add(3);

        singlyLinkedList.printElements();
    }
}
