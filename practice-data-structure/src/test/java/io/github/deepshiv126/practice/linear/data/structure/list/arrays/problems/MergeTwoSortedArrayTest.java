package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MergeTwoSortedArrayTest {

    @Test
    public void mergeTwoSortedArray() {
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10};

        MergeTwoSortedArray mergeTwoSortedArray = new MergeTwoSortedArray();
        int[] result = mergeTwoSortedArray.mergeTwoSortedArray(array1, array2);
        Assert.assertEquals(array1.length + array2.length, result.length);
        //mergeTwoSortedArray.printArray(result);
    }
}
