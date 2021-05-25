package io.github.deepshiv126.practice.linear.data.structure.stack;


import org.testng.Assert;
import org.testng.annotations.Test;

public class StackUsingArrayTest {

    @Test
    public void testPoP(){
        StackUsingArray<Integer> stackUsingSinglyLinkedList = new StackUsingArray();
        Assert.assertTrue(stackUsingSinglyLinkedList.isEmpty());
        Assert.assertEquals(0, stackUsingSinglyLinkedList.size());

        stackUsingSinglyLinkedList.push(1);
        stackUsingSinglyLinkedList.push(2);
        stackUsingSinglyLinkedList.push(3);

        Assert.assertEquals(new Integer(3), stackUsingSinglyLinkedList.pop());
        Assert.assertEquals(new Integer(1), stackUsingSinglyLinkedList.pop());
    }
}
