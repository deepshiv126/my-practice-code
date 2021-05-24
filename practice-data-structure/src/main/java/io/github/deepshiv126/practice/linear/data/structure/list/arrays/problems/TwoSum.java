package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    // unsorted array
    // loop through entire array,
    // Check if you see a complement in map, if not found, then store current value and index in a map < array-value, index >
    // continue, if you found a compliment, then you have a solution.
    public int[] twoSumUnsortedArray(int[] inputArray, int expectedSum) {
        int[] output = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        //unsorted array
        for (int i = 0; i < inputArray.length; i++) {
            int complement = expectedSum - inputArray[i];
            if (!map.containsKey(complement)) {
                map.put(inputArray[i], i);
            } else {
                output[0] = map.get(complement);
                output[1] = i;
                return output;
            }
        }
        return output;
    }

    //sorted array
    // start from both end of array,
    // if sum is greater than expected sum, decrease from end.
    // if sum is smaller than expected sum, increase from start.
    public int[] twoSumSortedArray(int[] inputArray, int expectedSum) {
        int[] output = new int[2];
        int start = 0, end = inputArray.length - 1;
        //unsorted array
        while (start < end) {
            int sum = inputArray[start] + inputArray[end];
            if (sum == expectedSum) {
                output[0] = start;
                output[1] = end;
                return output;
            }
            if (sum > expectedSum)
                end--;
            if (sum < expectedSum)
                start++;
        }
        return output;
    }
}
