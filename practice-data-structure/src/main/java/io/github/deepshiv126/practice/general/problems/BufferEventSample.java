package io.github.deepshiv126.practice.general.problems;

import java.util.PriorityQueue;
import java.util.Queue;

public class BufferEventSample {

    public static void main(String args[]) {
        // given inputs
        int windowSize = 5;
        Integer[] inputStreamData = {1, 2, 7, 5, 3, 11, 12, 20, 19};

        // Solution Approach :
        // I need a DataStructure that helps keep the buffer list in sorted,
        // such that when I move the window counter, it should helps me to remove list of all element not belong in that window.

        // Why not these Data Structure ?
        // 1. Array was not right solution for this problem - especially keeping sorted or finding element/remove element.
        // 2. LinkedList (Doubly) -- Adding element to maintain the order would take BigO(n) and n times BigO(n) or BigO(n2)
        //    where is N in inputStreamData size.
        //    Remove would take same amount time  n times BigO(n) or BigO(n2) where  .
        // 3. Stack, Regular Queues, Circular Queue are no help here.

        // Possible Choice of Data Structure
        // 1. Heap - sorting is BigO(nlogn) + Space Complexity is O(n). So, java choices was PriorityQueue.
        // 2. BST with in order traversal

        // Other Java provided solution ( prob
        // 1. Arrays.sort()  --> sort using quick sort and calling this to sort every to check anything outside the window
        // - BigO(nlogn) * n input = Big O(n2)
        // 2. Collections.sort() --> same as above.
        // 3. TreeSet/TreeMap() --> Not needed Key Value, unnessecary use of memory.

        // With all above choices, I choose Priority Queue.
        Queue<Integer> queue = new PriorityQueue<>();

        // running counter to keep the max number encountered.
        int runningMaxCounter = 0;
        for (Integer data : inputStreamData) {
            runningMaxCounter = Math.max(runningMaxCounter, data);
            // add the element to queue, it will sort it based on Integer CompareTo.
            queue.add(data);
            while (true) {
                // check if any elements outside window, then remove from the queue, otherwise break.
                if ((runningMaxCounter - queue.peek()) <= windowSize) {
                    break;
                }
                //System.out.print(queue.peek() + ",");
                queue.remove();
            }
        }

        // poll all left over elements from the queue.
        if (!queue.isEmpty()) {
            //System.out.print(queue.poll() + ",");
            // poll leaves n-1 element
            //System.out.println(queue.peek());
            queue.remove();
        }
    }
}
