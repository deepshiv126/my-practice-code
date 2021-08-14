package io.github.deepshiv126.practice.linear.data.structure.queue;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleQueueUsingSinglyLinkedListTest {

    @Test
    public void queueTest() {
        SimpleQueueUsingSinglyLinkedList<Integer> integerSimpleQueueUsingSinglyLinkedList = new SimpleQueueUsingSinglyLinkedList<>();
        integerSimpleQueueUsingSinglyLinkedList.enqueue(1);
        integerSimpleQueueUsingSinglyLinkedList.enqueue(2);
        integerSimpleQueueUsingSinglyLinkedList.enqueue(3);

        Assert.assertEquals(new Integer(1), integerSimpleQueueUsingSinglyLinkedList.dequeue());
        Assert.assertEquals(new Integer(2), integerSimpleQueueUsingSinglyLinkedList.dequeue());
        Assert.assertEquals(new Integer(3), integerSimpleQueueUsingSinglyLinkedList.dequeue());
    }
}
