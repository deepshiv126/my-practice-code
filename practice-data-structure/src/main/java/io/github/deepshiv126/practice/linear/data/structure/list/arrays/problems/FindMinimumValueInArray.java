package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

/**
 * Find the minimum value in Array.
 */
public class FindMinimumValueInArray {

    /**
     * Solution is very simple - loop through the array,
     * start with first element,
     * keep checking if holding elements is less than position element,
     * if yes then switch other move position to next.
     * <p>
     * Time Complexity = O(n).
     *
     * @param inputArray
     * @return
     */
    public int findMinimumValue(final int[] inputArray) {
        int minimumValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (minimumValue > inputArray[i]) {
                minimumValue = inputArray[i];
            }
        }
        return minimumValue;
    }

    public void printArray(final int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            // System.out.print(inputArray[i] + " --> ");
        }
        //System.out.println(" ::End");
    }

}
