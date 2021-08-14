package io.github.deepshiv126.practice.general.problems;

import java.util.PriorityQueue;
import java.util.Queue;

public class BufferEventSample {

    public static void main(String args[]) {
        int windowSize = 5;
        final Integer[] inputStreamData = {1, 2, 7, 5, 3, 11, 12, 20, 19};
        final Queue<Integer> queue = new PriorityQueue<>();

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
                queue.remove();
            }
        }

        // poll all left over elements from the queue.
        if (!queue.isEmpty()) {
            // poll leaves n-1 element
            queue.remove();
        }
    }
}
