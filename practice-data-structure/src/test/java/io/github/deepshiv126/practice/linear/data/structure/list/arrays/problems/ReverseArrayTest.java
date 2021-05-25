package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems.ReverseArray;
import org.testng.annotations.Test;

/**
 * Reverse the given array.
 */
public class ReverseArrayTest {

    @Test
    public void reverse() {
        int[] inputArray = {1, 2, 3, 4, 5, 6};

        ReverseArray charReverseArray = new ReverseArray();

        //charReverseArray.printArray(inputArray);

        inputArray = charReverseArray.reverse(inputArray);

        //charReverseArray.printArray(inputArray);

    }
}
