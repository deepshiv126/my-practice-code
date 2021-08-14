package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;


import org.testng.Assert;
import org.testng.annotations.Test;

public class ReverseSinglyLinkedListTest {

    @Test
    public void reverse() {

        ReverseSinglyLinkedList<Integer> reverseSinglyLinkedList = new ReverseSinglyLinkedList();
        // if list is empty;
        Assert.assertFalse(reverseSinglyLinkedList.reverse());

        // if list has one item ;
        reverseSinglyLinkedList.addFirst(10);
        Assert.assertTrue(reverseSinglyLinkedList.reverse());

        // if list has more than one item ;
        reverseSinglyLinkedList.addFirst(11);
        reverseSinglyLinkedList.addFirst(12);
        reverseSinglyLinkedList.addFirst(13);
        reverseSinglyLinkedList.addFirst(14);

        // visual understanding - print full list.
        //reverseSinglyLinkedList.printAllElements();

        Assert.assertTrue(reverseSinglyLinkedList.reverse());

        // visual understanding - print reverse list.
        //reverseSinglyLinkedList.printAllElements();

    }
}
