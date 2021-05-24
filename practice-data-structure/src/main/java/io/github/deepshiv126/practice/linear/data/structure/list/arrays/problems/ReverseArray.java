package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

/**
 * Reverse a given array.
 */
public class ReverseArray {

    /**
     * Idea is very simple - have two pointers,
     * - one start from beginning of array and one starts from end of array,
     * - move one index inward and simulatenously switch the elements between two ends.
     * <p>
     * Time Complexity is n/2, ignore constants and it will O(n)
     *
     * @param inputArray
     * @return
     */
    public int[] reverse(final int[] inputArray) {
        int start = 0;
        int end = inputArray.length - 1;
        while (start < end) {
            // switch the element - this need a tmp
            int tmp = inputArray[start];
            inputArray[start] = inputArray[end];
            inputArray[end] = tmp;
            // move both pointers inwardly by one point.
            start++;
            end--;
        }
        return inputArray;
    }

    public void printArray(final int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
           // System.out.print(inputArray[i] + " --> ");
        }
        //System.out.println(" ::End");
    }
}
