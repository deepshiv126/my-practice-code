package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

/**
 * Move all the zeros to end of the array.
 */
public class MoveAllZerosToEndOfAnArray {

    /**
     * Idea is very simple, have 2 pointers and loop through array
     * - one keep track of zeros and one keep track of non-zeros.
     * - Move both pointer simultaneously, until zero is found.
     * - Then move non-zero following pointer and keep zero pointer fixed until next non-zero element is found.
     * - Then swap the position with zero pointer to non-zero pointer elements.
     *
     * Time Complexity - O(n).
     * @param inputArray
     * @return
     */
    public int[] moveAllZeros(final int[] inputArray) {
        int zeroPointer = 0;
        for (int nonZeroPointer = 0; nonZeroPointer < inputArray.length; nonZeroPointer++) {
            if (inputArray[nonZeroPointer] != 0 && inputArray[zeroPointer] == 0) {
                int tmp = inputArray[zeroPointer];
                inputArray[zeroPointer] = inputArray[nonZeroPointer];
                inputArray[nonZeroPointer] = tmp;
            }
            if (inputArray[zeroPointer] != 0) {
                zeroPointer++;
            }
        }
        return inputArray;
    }

    public void printArray(final int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            //System.out.print(inputArray[i] + " --> ");
        }
        //System.out.println(" ::End");
    }

}
