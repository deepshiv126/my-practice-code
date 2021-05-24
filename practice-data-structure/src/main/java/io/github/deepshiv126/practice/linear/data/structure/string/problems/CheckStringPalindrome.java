package io.github.deepshiv126.practice.linear.data.structure.string.problems;

/**
 * Check whether given String is a Palindrome.
 */
public class CheckStringPalindrome {

    /**
     * Idea is same as Reverse Array  - have 2 pointers
     * - one start from beginning of array and one starts from end of array,
     * - move one index inward and simultaneously check elements should be same otherwise its not palindrome.
     *
     * @param inputString
     * @return
     */
    public boolean isStringPalindrome(final String inputString) {
        char[] inputStringAsCharArray = inputString.toCharArray();
        int startPointer = 0;
        int endPointer = inputStringAsCharArray.length - 1;
        while (startPointer < endPointer) {
            if (inputStringAsCharArray[startPointer] != inputStringAsCharArray[endPointer]) {
                return false;
            }
            startPointer++;
            endPointer--;
        }
        return true;
    }
}
