package com.jalch.kata.algorithm.lang;

public class FrameElementSummarizer {

    // Given an array of integers nums, calculate the sum of all elements whose
    // indices are completely divisible by either 3 or 5. If the index is
    // divisible by both, add the element twice to the total running sum.

    public static int sum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if ((i % 3) == 0)
                sum += nums[i];
            if ((i % 5) == 0)
                sum += nums[i];
        }
        return sum;
    }

}
