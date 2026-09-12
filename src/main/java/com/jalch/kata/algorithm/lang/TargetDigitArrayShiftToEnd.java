package com.jalch.kata.algorithm.lang;

public class TargetDigitArrayShiftToEnd {

    // Given an array of integers nums and a target value k, shift all occurrences
    // of k to the end of the array while maintaining the relative order of all
    // other non-target elements. The mutation must happen with minimal writes.

    // Two pointers problem.
    // Time complexity: Worst case 0(2N)=O(N)
    // Space complexity: O(1) - minimal extra variables and data structures.
    public static void shift(int[] nums, int target) {
        if (nums == null || nums.length <= 1)
            return;
        int length = nums.length;
        int writeIndex = 0;
        for (int readIndex = 0; readIndex < length; readIndex++) {
            if (nums[readIndex] != target) {
                if (writeIndex != readIndex) {
                    nums[writeIndex] = nums[readIndex];
                }
                writeIndex++;
            }
        }
        while (writeIndex < length) {
            nums[writeIndex] = target;
            writeIndex++;
        }
    }

}
