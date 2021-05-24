package io.github.deepshiv126.practice.linear.data.structure.string.problems;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Check whether given String is a Palindrome.
 */
public class CheckStringPalindromeTest {

    @Test
    public void isStringPalindrome() {
        String palindromeString = "madam";
        String nonPalindromeString = "deepak";

        CheckStringPalindrome checkStringPalindrome = new CheckStringPalindrome();
        Assert.assertTrue(checkStringPalindrome.isStringPalindrome(palindromeString));
        Assert.assertFalse(checkStringPalindrome.isStringPalindrome(nonPalindromeString));
    }
}
