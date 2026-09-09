package com.jalch.kata.algorithm.lang;

public class MinimumContiguousSubarrayOverridingTarget {

    // =======================================================================
    // Given an array of positive integers nums and a positive integer target,
    // return the minimal length of a contiguous subarray of which the sum is
    // greater than or equal to target. If no such subarray exists, return 0
    // instead.
    // =======================================================================

    // Sliding window problem.
    // Time complexity O(n)
    // Space complexity O(1)
    public static int get(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target == 0)
            return 0;
        int length = nums.length;
        if (length == 1 && nums[0] >= target)
            return 1;
        int minimumLength = length + 1;
        int left = 0;
        int currentSum = 0;
        for (int right = 0; right < length; right++) {
            currentSum += nums[right];
            // As long as the current window is valid, compress it from the left.
            // And update the minimum length.
            while (currentSum >= target) {
                minimumLength = Math.min(minimumLength, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        return minimumLength > length ? 0 : minimumLength;
    }

}
