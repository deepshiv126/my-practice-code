package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import org.testng.annotations.Test;

/**
 * Move all the zeros to end of the array.
 */
public class MoveAllZerosToEndOfAnArrayTest {

    @Test
    public void moveAllZeros() {

        int[] inputArray = {1, 0, 0, 2, 0, 3, 0, 4, 0, 5};

        MoveAllZerosToEndOfAnArray moveAllZerosToEndOfAnArray = new MoveAllZerosToEndOfAnArray();
        //moveAllZerosToEndOfAnArray.printArray(inputArray);
        //moveAllZerosToEndOfAnArray.printArray(moveAllZerosToEndOfAnArray.moveAllZeros(inputArray));
        moveAllZerosToEndOfAnArray.moveAllZeros(inputArray);

    }
}
