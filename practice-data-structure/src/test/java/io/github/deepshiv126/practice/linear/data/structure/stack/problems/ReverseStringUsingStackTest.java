package io.github.deepshiv126.practice.linear.data.structure.stack.problems;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReverseStringUsingStackTest {

    @DataProvider
    public Object[][] reverseStringDataProvider(){
        return new Object[][] {
                {"madam",},
                {"kayak",}
        };

    }

    @Test(dataProvider = "reverseStringDataProvider")
    public void reverseStringTest(final String inputString) {
        ReverseStringUsingStack reverseStringUsingStackTest = new ReverseStringUsingStack();
        Assert.assertTrue(inputString.equals(reverseStringUsingStackTest.reverseString(inputString)));
    }
}
