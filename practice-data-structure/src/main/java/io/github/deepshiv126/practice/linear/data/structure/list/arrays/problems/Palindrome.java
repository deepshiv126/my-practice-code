package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

public class Palindrome {

    public boolean isPalindrome(char[] input) {

        // move pointers from both end to inward and compare,
        // if any element is different, then its not palindrome.
        int start = 0, end = input.length - 1;
        while (start < end) {
            if (input[start] != input[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
