package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

public class MergeTwoSortedArray {

    public int[] mergeTwoSortedArray(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        //loop through both array and exit when either one hits end.
        while (i < array1.length && j < array2.length) {
            // if array1 value is smaller than array2, add to result and increment array1+result
            if (array1[i] < array2[j]) {
                result[k] = array1[i];
                i++;
            }
            // if array2 value is smaller than array1, then add to result and increment array2+result
            else {
                result[k] = array2[j];
                j++;
            }
            k++;
        }

        // if array1 have anything left, so fill it up in result.
        while (i < array1.length) {
            result[k] = array1[i];
            i++;
            k++;
        }
        // if array2 have anything left, so fill it up in result.
        while (j < array2.length) {
            result[k] = array2[j];
            j++;
            k++;
        }
        return result;
    }

    public void printArray(int[] result) {
        for (int o = 0; o < result.length; o++) {
            //System.out.print(result[o] + "-->");
        }
    }
}
