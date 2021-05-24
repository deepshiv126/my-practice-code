package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems.FindMinimumValueInArray;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Find the minimum value in Array.
 */
public class FindMinimumValueInArrayTest {

    @Test
    public void findMinimumValue() {
        int[] inputArray = {25, 20, 15, 10, 5};

        FindMinimumValueInArray findMinimumValueInArray = new FindMinimumValueInArray();
        //findMinimumValueInArray.printArray(inputArray);
        //System.out.println(findMinimumValueInArray.findMinimumValue(inputArray));
        Assert.assertEquals(5, findMinimumValueInArray.findMinimumValue(inputArray));
        Assert.assertNotEquals(25, findMinimumValueInArray.findMinimumValue(inputArray));
    }
}
