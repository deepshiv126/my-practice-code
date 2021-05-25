package io.github.deepshiv126.practice.linear.data.structure.list.arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * A generic dyanmic array - Tests.
 */
public class DynamicArrayTest {


    @DataProvider(name = "createDynamicArrayWithDifferentCapacity")
    public Object[][] createDynamicArrayDataProvider() {
        return new Object[][]{
                {0,},
                {10,}
        };
    }


    @Test(dataProvider = "createDynamicArrayWithDifferentCapacity",
            expectedExceptions = IllegalArgumentException.class)
    public void createDynamicArray(final int capacity) {
        DynamicArray<Integer> array = new DynamicArray<Integer>(capacity);
        Assert.assertTrue(array.size() == 0);

        // catch exception
        DynamicArray<Integer> invalidArray = new DynamicArray<Integer>(-1);

    }

    public DynamicArray<Integer> createArray() {
        DynamicArray<Integer> array = new DynamicArray<Integer>();
        Assert.assertEquals(true, array.isEmpty());
        array.add(1);
        array.add(2);
        array.add(3);
        return array;
    }

    @Test
    public void addElement() {
        final DynamicArray<Integer> array = this.createArray();
        Assert.assertEquals(3, array.size());
        Assert.assertEquals(false, array.isEmpty());
    }

}
