package io.github.deepshiv126.practice.linear.data.structure.list.linkedlist.problems;


import java.util.Arrays;

/**
 * Reorder Data in Log Files
 *
 * Rules -
 * 1. Log can be 2 type - Letter Log and Digit Log
 * 2. First part of Log - unique identifier and Rest part of log - actual logs
 * 3. Unique Identifiers - can be a-z and 0-9
 * 4. Rules
 *        1. Letter Logs always comes before digit logs -- eg. "let3 leet code" "let2 1 12 13"
 *        2. Letter Logs sorted lexico-graphically and not including id. -- eg. "let3 art zero" "let2 own kit dog"
 *        3. When Letter log are equals, sort by id lexico-graphically eg. "let2 leet code" "let2 leet code"
 *        4. Digit logs should maintain thier order  -- eg. "let3 leet code" "let2 1 12 13" "let1 9 12 10"
 *
 *  Solution
 *  1. Split String by Space and first String is Unique Identifier
 *  2. Second String - very first character says letter log or digit log.
 *
 *  Complexity
 *      Time : O(n*log(n)) - Array Sort using QuickSort
 *      Space : O(log(n))
 *
 */
public class ReOrderDataInLogFiles {

    public String[] reOrderLogData(String[] logs) {
        Arrays.sort(logs, (secondString, firstString) -> {
            // secondString < firstString = -1 eg. (a - c = -1)  == (97 - 98 = -1 )
            // secondString == firstString  = 0 eg. (a - a = 0)  == (97 - 97 = -1 )
            // secondString > firstString = 1 eg. (c - a = 0)  == (98 - 97 = 1 )  // first char is a and second char is c - its good

            // get the index of first space
            int secondStringSpaceIndex = secondString.indexOf(" ");
            // get the string from 0 to space -- this will be first letter.
            String secondStringUniqueIdentifier = secondString.substring(0, secondStringSpaceIndex);
            // get the character as string after space -- this is to identify whether its letterlog or digitlog
            String secondStringLogData = secondString.substring(secondStringSpaceIndex+1);

            int firstStringSpaceIndex = firstString.indexOf(" ");
            String firstStringUniqueIdentifier = firstString.substring(0, firstStringSpaceIndex);
            String firstStringLogData = firstString.substring(firstStringSpaceIndex+1);

            //identify whether its letterlog or digitlog
            boolean isSecondStringADigitLog = Character.isDigit(secondStringLogData.charAt(0));
            boolean isFirstStringADigitLog = Character.isDigit(firstStringLogData.charAt(0));

            // if both are letter log - then
            // check if they are same or not,
            // if same,
            // then check unique identifier
            if(!isSecondStringADigitLog && !isFirstStringADigitLog) {
                // check if both letter log are same or not
                int value = secondStringLogData.compareTo(firstStringLogData);
                // if its same, then compare to unique id
                if(value == 0 ) return secondStringUniqueIdentifier.compareTo(firstStringUniqueIdentifier);
                return value;
            }
            // if either one is digit log or both should be digit log.

            // if second string is digit log,
            // then check first string


            // explaining if conditions
            if(isSecondStringADigitLog) {
                if(isFirstStringADigitLog) {
                    // if first string is digit log - leave it
                    // 0
                }
                else {
                    // else first string is letter log -- still good first is letter and second is digit
                    // 1
                }
            } else {
                // second string is letter log - then switch.
                // -1
            }

            // same as above if condition, but used ternary condition.
            return isSecondStringADigitLog ? (isFirstStringADigitLog ? 0 : 1) : -1;
        });
        return  logs;
    }

    public String[] understanding(String[] logs) {
        int i=0;
        Arrays.sort(logs, (log1, log2) -> {
            int value = log1.compareTo(log2);
            //System.out.println(log1+":"+log2+"="+value);
            return value;
        });
        return logs;
    }
}
