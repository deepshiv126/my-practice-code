package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TwoSumTest {

    @Test
    public void testTwoSumonSortedArray() {
        TwoSum twoSum = new TwoSum();
        int[] unsortedArray = {2, 5, 7, 9, 11, 13};
        int[] output = twoSum.twoSumSortedArray(unsortedArray, 14);
        Assert.assertEquals(1, output[0]);
        Assert.assertEquals(3, output[1]);
    }

    @Test
    public void testTwoSumonUnSortedArray() {
        TwoSum twoSum = new TwoSum();
        int[] unsortedArray = {2, 11, 5, 10, 7, 8};
        int[] output = twoSum.twoSumUnsortedArray(unsortedArray, 9);
        Assert.assertEquals(0, output[0]);
        Assert.assertEquals(4, output[1]);
    }
}
