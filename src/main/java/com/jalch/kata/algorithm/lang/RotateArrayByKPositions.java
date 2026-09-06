package com.jalch.kata.algorithm.lang;

public class RotateArrayByKPositions {

    // Given an integer array, nums, shift its elements to the right by k positions.
    // In other words, rotate the array to the right by k steps, where k is
    // non-negative.

    // Two pointers problem.
    // Time complexity O(n + k + n - k) -> O(2n) -> O(n)
    // Space complexity O(1)
    public static int[] rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return nums;
        k = k % nums.length;
        if (k == 0)
            return nums;
        reverse(nums, 0, nums.length - 1); // O(n/2) -> O(n)
        reverse(nums, 0, k - 1); // O(k/2) -> O(k)
        reverse(nums, k, nums.length - 1); // O(n - k)
        return nums;
    }

    private static void reverse(int[] nums, int start, int end) {
        if (start < 0 || end < 0 || start >= nums.length || end >= nums.length || start > end)
            return;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
        ;
    }

}
