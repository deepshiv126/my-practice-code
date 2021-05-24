package io.github.deepshiv126.practice.linear.data.structure.stack.problems;

import io.github.deepshiv126.practice.linear.data.structure.stack.StackUsingSinglyLinkedList;

public class ReverseStringUsingStack {

    public String reverseString(final String input) {

        if (input == null || input.length() == 0)
            throw new IllegalArgumentException();

        char[] chars = input.toCharArray();
        StackUsingSinglyLinkedList<Character> stackUsingSinglyLinkedList = new StackUsingSinglyLinkedList<>();

        for (char c : chars) {
            stackUsingSinglyLinkedList.push(c);
        }

        for (int i = 0; i < input.length(); i++) {
            chars[i] = stackUsingSinglyLinkedList.pop();
        }
        return new String(chars);
    }
}
