package io.github.deepshiv126.practice.linear.data.structure.stack;


import org.testng.Assert;
import org.testng.annotations.Test;

public class StackUsingSinglyLinkedListTest {

    @Test
    public void testPoP() {
        StackUsingSinglyLinkedList<Integer> stackUsingSinglyLinkedList = new StackUsingSinglyLinkedList();
        Assert.assertTrue(stackUsingSinglyLinkedList.isEmpty());
        Assert.assertEquals(0, stackUsingSinglyLinkedList.size());

        stackUsingSinglyLinkedList.push(1);
        stackUsingSinglyLinkedList.push(2);
        stackUsingSinglyLinkedList.push(3);

        Assert.assertEquals(new Integer(3), stackUsingSinglyLinkedList.pop());
        Assert.assertEquals(new Integer(2), stackUsingSinglyLinkedList.pop());
        Assert.assertEquals(new Integer(1), stackUsingSinglyLinkedList.pop());
    }
}
