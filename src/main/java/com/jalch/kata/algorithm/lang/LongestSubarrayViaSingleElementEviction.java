package com.jalch.kata.algorithm.lang;

public class LongestSubarrayViaSingleElementEviction {

    // =======================================================================
    // Given a binary array nums, you must delete exactly one element from it.
    // Return the maximum length of a contiguous subarray containing only 1s
    // in the resulting mutated array.
    // =======================================================================

    // Time complexity O(n)
    // Space complexity O(1)
    public static int getMaxSubarrayWithPointers(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;
        if (length == 1) // Must delete one, hence no maximum substring.
            return 0;
        int maximumLength = -1;
        int left = 0;
        int potentialEvictionIndex = -1;
        for (int rigth = 0; rigth < length; rigth++) {
            if (nums[rigth] == 0) {
                // If we find another zero, and we already found one before
                // Move the left pointer to the index after the zero.
                if (potentialEvictionIndex >= 0) {
                    left = potentialEvictionIndex + 1;
                }
                potentialEvictionIndex = rigth;
            }
            // The formula (right - left + 1) - 1 simplifies directly down to (right -
            // left).
            // The extra -1 is added because we always need to remove 1 digit.
            maximumLength = Math.max(maximumLength, rigth - left);
        }

        return maximumLength > 0 ? maximumLength : 0;
    }

    // Time complexity O(n)
    // Space complexity O(1)
    public static int getMaxSubarrayWithSlidingWindow(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;
        if (length == 1) // Must delete one, hence no maximum substring.
            return 0;
        int zeros = 0;
        int left = 0;
        int maximumLength = 0;
        for (int rigth = 0; rigth < length; rigth++) {
            if (nums[rigth] == 0)
                zeros++;
            while (zeros > 1) {
                if (nums[left] == 0)
                    zeros--;
                left++;
            }
            maximumLength = Math.max(maximumLength, rigth - left);
        }
        return maximumLength;
    }

}
