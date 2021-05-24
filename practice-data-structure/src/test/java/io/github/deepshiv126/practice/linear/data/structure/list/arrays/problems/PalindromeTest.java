package io.github.deepshiv126.practice.linear.data.structure.list.arrays.problems;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PalindromeTest {

    @Test
    public void palindrome() {
        Palindrome palindrome = new Palindrome();
        Assert.assertTrue(palindrome.isPalindrome("madam".toCharArray()));
    }

    @Test
    public void notPalindrome() {
        Palindrome palindrome = new Palindrome();
        Assert.assertFalse(palindrome.isPalindrome("hello".toCharArray()));
    }
}
