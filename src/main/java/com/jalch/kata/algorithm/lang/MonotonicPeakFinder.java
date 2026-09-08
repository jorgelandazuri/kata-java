package com.jalch.kata.algorithm.lang;

public class MonotonicPeakFinder {
    // Given an array of integers arr, return true if and only if it is a valid
    // mountain array.
    // It is a valid mountain array if:
    // - arr.length >= 3
    // - There exists some i with 0 < i < arr.length - 1 such that:
    // - arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
    // - arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

    // Time complexity: Best case O(n), worst case O(n) : Up to 2 full passes ((2N - 1) iterations total)
    // Space complexity O(1)
    // Less efficient in general than the other solution.
    public static boolean isMountainArrayFindingMax(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        int length = nums.length;
        // find max
        int maxIndex = 0;
        int currentMax = nums[0]; // Start with the first element as the baseline
        for (int i = 1; i < length; i++) {
            int current = nums[i];
            if (current >= currentMax) {
                currentMax = current;
                maxIndex = i;
            }
        }
        // peak can't be the first index or the last.
        if (maxIndex == 0 || maxIndex == (length - 1))
            return false;

        for (int left = maxIndex; left > 0; left--) {
            if (nums[left - 1] >= nums[left])
                return false;
        }

        for (int right = maxIndex; right < length - 1; right++) {
            if (nums[right] <= nums[right + 1])
                return false;
        }

        return true;
    }

    // Time complexity: Best case O(1), worst case O(n) : Only 1 pass.
    // Space complexity O(1)
    // More efficient in general than the other solution.
    public static boolean isMountainArrayOnePass(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        int length = nums.length;
        int i = 0;
        // 1. Climb up strictly
        while (i + 1 < length && nums[i] < nums[i + 1]) {
            i++;
        }
        // 2. Validate peak: It cannot be the first or last element
        if (i == 0 || i == length - 1) {
            return false;
        }
        // 3. Climb down strictly
        while (i + 1 < length && nums[i] > nums[i + 1]) {
            i++;
        }
        // 4. Valid if we cleanly reached the very end of the array
        return i == length - 1;
    }

}
