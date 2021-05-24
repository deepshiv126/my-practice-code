package io.github.deepshiv126.practice.linear.data.structure.stack.problems;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidParenthesesTest {

    @Test
    public void isValidParenthesesTest() {
        ValidParentheses validParentheses = new ValidParentheses();
        Assert.assertTrue(validParentheses.isValidApproach1("()"));
        Assert.assertFalse(validParentheses.isValidApproach1("(("));
    }
}
