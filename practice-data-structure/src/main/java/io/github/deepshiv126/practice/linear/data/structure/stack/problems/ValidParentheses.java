package io.github.deepshiv126.practice.linear.data.structure.stack.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    private Map<Character, Character> mappings;

    public ValidParentheses() {
        this.mappings = new HashMap<>();
        this.mappings.put('{', '}');
        this.mappings.put('(', ')');
        this.mappings.put('[', ']');
    }

    public boolean isValidApproach1(String inputString) {
        Stack<Character> stack = new Stack<>();
        for (char c : inputString.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                if (c == ']' && stack.peek() != '[') return false;
                if (c == '}' && stack.peek() != '{') return false;
                if (c == ')' && stack.peek() != '(') return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public boolean isValidApproach2(final String inputString) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = inputString.toCharArray();

        for (char ch : charArray) {
            if (this.mappings.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.peek() != this.mappings.get(ch)) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
