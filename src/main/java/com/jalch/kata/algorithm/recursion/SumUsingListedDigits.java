package com.jalch.kata.algorithm.recursion;
//You are given two non-empty linked lists representing two non-negative integers.
//The most significant digit comes first and each of their nodes contain a single digit.
//Add the two numbers and return it as a linked list.
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//Follow up: What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
//Example: Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7

import java.util.LinkedList;

public class SumUsingListedDigits {

    private int currentCarry = 0;
    private int currentSum = 0;
    private LinkedList<Integer> result = new LinkedList<>();

    synchronized public LinkedList<Integer> sumOf(LinkedList<Integer> first, LinkedList<Integer> second) {
        if (nullOrEmpty(first) && nullOrEmpty(second)) return new LinkedList<>();
        if (!nullOrEmpty(first) && nullOrEmpty(second)) return first;
        if (nullOrEmpty(first)) return second;

        initGlobalVariables();
        int sizeFirst = first.size();
        int sizeSecond = second.size();
        int sizeDifference;
        if (sizeFirst == sizeSecond)
            sumUpEqualSized(first, second);
        else {
            sizeDifference = Math.abs(sizeFirst - sizeSecond);
            if (sizeFirst > sizeSecond) {
                addUpLeadingZeros(second, sizeDifference);
            } else {
                addUpLeadingZeros(first, sizeDifference);
            }
            sumUpEqualSized(first, second);
        }

        if (currentCarry > 0) result.push(currentCarry);

        return result;
    }

    private void initGlobalVariables() {
        result.clear();
        currentSum = 0;
        currentCarry = 0;
    }

    private void sumUpEqualSized(LinkedList<Integer> first, LinkedList<Integer> second) {

        if (nullOrEmpty(first) && nullOrEmpty(second)) return;

        int firstHead = !nullOrEmpty(first) ? first.pollFirst() : 0;
        int secondHead = !nullOrEmpty(second) ? second.pollFirst() : 0;

        sumUpEqualSized(first, second);

        int sum = firstHead + secondHead + currentCarry;
        currentCarry = Math.floorDiv(sum, 10);
        currentSum = sum % 10;

        result.push(currentSum);
    }

    private void addUpLeadingZeros(LinkedList<Integer> list, int zeros) {
        for(int i = 0 ; i< zeros; i++)
            list.push(0);
    }

    private boolean nullOrEmpty(LinkedList<Integer> list) {
        return list == null || list.isEmpty();
    }

}
