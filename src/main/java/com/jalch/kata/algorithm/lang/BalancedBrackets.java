package com.jalch.kata.algorithm.lang;

import java.util.HashMap;
import java.util.Map;
// import java.util.Stack;

//https://www.hackerrank.com/challenges/balanced-brackets/problem

class BalancedBrackets {

    private static Map<Character, Character> closeOpenBracketsPair;

    static {
        closeOpenBracketsPair = new HashMap<>();
        closeOpenBracketsPair.put('}', '{');
        closeOpenBracketsPair.put(']', '[');
        closeOpenBracketsPair.put(')', '(');
    }

    static boolean isBalanced(String input) {
        if (input == null) return false;
        if (input.isEmpty()) return true;

        CharStack charStack = new CharStack();
        //    Stack<Character> charStack = new Stack<>();
        for (char current : input.toCharArray()) {
            if ("([{".contains(current + "")) charStack.push(current);

            if (closeOpenBracketsPair.get(current) != null) {
                if (charStack.isEmpty() || charStack.peek() != closeOpenBracketsPair.get(current)) return false;
                else charStack.pop();
            }
        }
        return charStack.isEmpty();

    }

    static class CharStack {

        Node top;

        class Node {
            char character;
            Node next;

            Node(char character) {
                this.character = character;
            }
        }

        boolean isEmpty() {
            return top == null;
        }

        void push(char character) {
            Node newNode = new Node(character);
            if (!isEmpty()) newNode.next = top;
            else newNode.next = null;
            top = newNode;
        }

        Node pop() {
            if (isEmpty()) return null;
            else {
                Node toPop = top;
                top = top.next;
                toPop.next = null;
                return toPop;
            }
        }

        Character peek() {
            return isEmpty() ? null : top.character;
        }

    }

}
