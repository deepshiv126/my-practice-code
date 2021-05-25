package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Find the "n"th element from end of list.
 * I have tried 80% code coverage.
 */
public class FindNthElementFromEndSinglyLinkedListTest {

    @Test
    public void findNthElementFromEnd() {
        // assert empty list.
        FindNthElementFromEndSinglyLinkedList<Integer> emptySinglyLinkedList = new FindNthElementFromEndSinglyLinkedList<>();
        Assert.assertNull(emptySinglyLinkedList.findNthElementFromEnd(10));

        FindNthElementFromEndSinglyLinkedList<Integer> singlyLinkedList = new FindNthElementFromEndSinglyLinkedList<>();
        singlyLinkedList.addFirst(10);
        // assert single item in the list.
        Assert.assertNull(singlyLinkedList.findNthElementFromEnd(2));

        // add more data
        singlyLinkedList.addFirst(11);
        singlyLinkedList.addFirst(12);
        singlyLinkedList.addFirst(13);
        singlyLinkedList.addFirst(14);

        // used addFirst, so list looks like 14 --> 13 --> 12 --> 11 --> 10 -->  null
        Assert.assertEquals(new Integer(11), singlyLinkedList.findNthElementFromEnd(2));
    }


    @DataProvider(name = "provideInvalidNInput")
    public static Object[][] provideInvalidNInput() {
        return new Object[][]{
                {-1,},
                {0,},
                {100,},
        };
    }

    @Test(dataProvider = "provideInvalidNInput", expectedExceptions = IllegalArgumentException.class)
    public void testNisGreaterThanSizeOfList(int n) {

        FindNthElementFromEndSinglyLinkedList<Integer> singlyLinkedList = new FindNthElementFromEndSinglyLinkedList<>();
        singlyLinkedList.addFirst(10);
        singlyLinkedList.addFirst(11);
        singlyLinkedList.addFirst(12);
        singlyLinkedList.addFirst(13);
        singlyLinkedList.addFirst(14);

        // visual understanding.
        // singlyLinkedList.printAllElements();

        // expected to throw IllegalArgument exceptions.
        singlyLinkedList.findNthElementFromEnd(n);
    }
}
