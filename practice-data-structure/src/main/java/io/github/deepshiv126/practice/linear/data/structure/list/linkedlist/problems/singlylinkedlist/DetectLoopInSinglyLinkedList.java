package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems.singlylinkedlist;

/**
 * Detect Loop in Singly Linked List.
 * <p>
 * Time Complexity is O(n)
 */
public class DetectLoopInSinglyLinkedList {

    Node head;

    public boolean detectLoop() {
        // idea is have 2 pointers - slowPointer and fastPointer
        Node slowPointer = this.head;
        Node fastPointer = this.head;

        // move slowPointer by 1 step forward and move fastPointer by 2 step forward.
        // idea is fasterPointer never reaches to end and some point fasterPointer meets slowPointer
        // check if slowPointer is equals to fastPointer -
        // here memory location should be compared,not the element data.

        while (fastPointer != null && fastPointer.next != null) {
            // ^^ fastPointer.next null check is must other wise,
            // if there is no loop, then when at last but 1 element and tries move 2 position it will throw NPE.
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            if (slowPointer == fastPointer) {
                //System.out.println("Beginning Element of Loop :" + this.startingElementOfLoop(slowPointer).element);
                //System.out.println("Removed Loop :" + this.removeLoop(slowPointer));

                return true;
            }
        }
        return false;
    }

    // get the starting element of loop
    public Node startingElementOfLoop(Node slowPointer) {
        Node tmp = this.head;
        while (tmp != slowPointer) {
            tmp = tmp.next;
            slowPointer = slowPointer.next;
        }
        return tmp;
    }

    public boolean removeLoop(Node slowerPointer) {
        Node tmp = this.head;
        while (tmp.next != slowerPointer.next) {
            tmp = tmp.next;
            slowerPointer = slowerPointer.next;
        }
        slowerPointer.next = null;

        // print the loop for visual understanding.
        tmp = this.head;
        while (tmp != null) {
            //System.out.print(tmp.element + " -->");
            tmp = tmp.next;
        }
        return true;
    }

    // used to create loop for tests.
    public void createLoop(boolean enableLoop) {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        Node seventh = new Node(7);

        this.head = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;

        // print the list before make it loop.
        Node tmp = this.head;
        while (tmp != null) {
            //  System.out.print(tmp.element + "-->");
            tmp = tmp.next;
        }

        if (enableLoop)
            // this is where loop gets created.
            seventh.next = second;
        else
            // regular list
            seventh.next = null;
    }

    public void createSingleElementList() {
        this.head = new Node(1);
    }

    // Node class.
    private static class Node {
        private int element;
        private Node next;

        public Node(final int element) {
            this.element = element;
            this.next = null;
        }
    }
}
